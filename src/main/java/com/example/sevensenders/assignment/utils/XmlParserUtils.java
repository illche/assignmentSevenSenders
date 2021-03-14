package com.example.sevensenders.assignment.utils;

import com.example.sevensenders.assignment.dto.WebComicsResult;
import com.example.sevensenders.assignment.dto.pdl.xml.Item;
import com.example.sevensenders.assignment.dto.pdl.xml.RSS;
import com.example.sevensenders.assignment.dto.xkcd.xml.Entry;
import com.example.sevensenders.assignment.dto.xkcd.xml.Feed;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class XmlParserUtils {

    private XmlParserUtils() {

    }

    public static List<WebComicsResult> parseXkcdXMLToEntities(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Feed.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Feed feed = (Feed) unmarshaller.unmarshal(new StringReader(removeNameSpace(xml)));
            return feed.getEntry().stream().map(XmlParserUtils::transformXkcdEntryToWebComicsResult).collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return null;
    }

    public static List<WebComicsResult> parsePdlXMLToEntries(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(RSS.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RSS rss = (RSS) unmarshaller.unmarshal(new StringReader(xml));
            return rss.getChannel().getItem().stream().map(XmlParserUtils::transformPdlEntryToWebComicsResult).collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return null;
    }

    private static WebComicsResult transformPdlEntryToWebComicsResult(Item entry) {
        WebComicsResult webComicsResult = new WebComicsResult();
        webComicsResult.setWebUrl(entry.getGuid());
        try {
            webComicsResult.setPublishDate(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(entry.getPubDate()));
        } catch (Exception ignored) {
        }
        Pattern pattern = Pattern.compile("<a href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(entry.getEncoded());
        if(matcher.find()) {
            webComicsResult.setPictureUrl(matcher.group(1));
        }
        webComicsResult.setTitle(entry.getTitle());
        return webComicsResult;
    }

    private static WebComicsResult transformXkcdEntryToWebComicsResult(Entry entry) {
        WebComicsResult webComicsResult = new WebComicsResult();
        webComicsResult.setWebUrl(entry.getLink().getHref());
        webComicsResult.setTitle(entry.getTitle());
        try {
            webComicsResult.setPublishDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse(entry.getUpdated()));
        } catch (Exception ignored) {
        }
        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(entry.getSummary());
        if(matcher.find()) {
            webComicsResult.setPictureUrl(matcher.group(1));
        }
        return webComicsResult;
    }


    private static String removeNameSpace(String xml) {
        return xml.replaceAll("xmlns.*?(\"|\').*?(\"|\')", "");
    }
}
