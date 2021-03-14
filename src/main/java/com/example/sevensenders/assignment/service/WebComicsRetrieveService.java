package com.example.sevensenders.assignment.service;

import com.example.sevensenders.assignment.client.ComicsWebRequestClient;
import com.example.sevensenders.assignment.dto.WebComicsResult;
import com.example.sevensenders.assignment.dto.xkcd.json.WebComicsXkcdDTO;
import com.example.sevensenders.assignment.utils.BeanParserUtils;
import com.example.sevensenders.assignment.utils.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebComicsRetrieveService {

    @Value("${xkcd.xml.comics.url}")
    private String xkcdXmlUrl;

    @Value("${xkcd.json.comics.url}")
    private String xkcdJsonUrl;

    @Value("${pdl.xml.comics.url}")
    private String pdlXmlUrl;
    @Autowired
    private ComicsWebRequestClient client;


    public List<WebComicsResult> getSortedResultFromWebsites() {
        List<WebComicsResult> resultList = new ArrayList<>();
        resultList.addAll(getComicsFromXKCDXml().stream().limit(10).collect(Collectors.toList()));
        resultList.addAll(getComicsFromPDL().stream().limit(10).collect(Collectors.toList()));
        getCurrentComicFromXKCD();
        return resultList.stream()
                .sorted((x, y) -> {
                    return x.getPublishDate().after(y.getPublishDate()) ? -1 : 1;
                })
                .collect(Collectors.toList());
    }

    private List<WebComicsResult> getComicsFromXKCDXml() {
        return XmlParserUtils.parseXkcdXMLToEntities(client.getResultFromUrl(xkcdXmlUrl, String.class));
    }

    private List<WebComicsResult> getComicsFromPDL() {
        return XmlParserUtils.parsePdlXMLToEntries(client.getResultFromUrl(pdlXmlUrl, String.class));
    }

    private List<WebComicsResult> getCurrentComicFromXKCD() {
        List<WebComicsResult> webComicsResults = new ArrayList<>();
        webComicsResults.add(BeanParserUtils.parseXkcdJsonBean(client.getResultFromUrl(xkcdJsonUrl, WebComicsXkcdDTO.class)));
        return webComicsResults;
    }
}
