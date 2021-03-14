package com.example.sevensenders.assignment.utils;

import com.example.sevensenders.assignment.dto.WebComicsResult;
import com.example.sevensenders.assignment.dto.pdl.Item;
import com.example.sevensenders.assignment.dto.pdl.RSS;
import com.example.sevensenders.assignment.dto.xkcd.xml.Entry;
import com.example.sevensenders.assignment.dto.xkcd.xml.Feed;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public final class XmlParserUtils {

    private XmlParserUtils() {

    }

    public static List<WebComicsResult> parseXkcdXMLToEntities(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Feed.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Feed feed = (Feed) unmarshaller.unmarshal(new StringReader(removeNameSpace(xml)));
            return feed.getEntry().stream().map(XmlParserUtils::transformXkcdEntryToWebComicasResult).collect(Collectors.toList());
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
        webComicsResult.setPictureUrl(entry.getLink());
        webComicsResult.setTitle(entry.getTitle());
        return webComicsResult;
    }

    private static WebComicsResult transformXkcdEntryToWebComicasResult(Entry entry) {
        WebComicsResult webComicsResult = new WebComicsResult();
        webComicsResult.setWebUrl(entry.getLink().getHref());
        webComicsResult.setTitle(entry.getTitle());
        try {
            webComicsResult.setPublishDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse(entry.getUpdated()));
        } catch (Exception ignored) {
        }
        webComicsResult.setPictureUrl(entry.getSummary());
        return webComicsResult;
    }


    private static String removeNameSpace(String xml) {
        return xml.replaceAll("xmlns.*?(\"|\').*?(\"|\')", "");
    }
}
