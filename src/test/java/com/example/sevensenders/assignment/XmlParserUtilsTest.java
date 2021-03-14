package com.example.sevensenders.assignment;


import com.example.sevensenders.assignment.dto.WebComicsResult;
import com.example.sevensenders.assignment.utils.XmlParserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class XmlParserUtilsTest {

    private String readXmlFromFile(String path) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        return Files.lines(Paths.get(resource.getPath().substring(1))).collect(Collectors.joining("\n"));
    }

    @Test
    public void shouldReturnValidXkcdBeanWhenPassedValidXml() throws Exception {
        WebComicsResult expectedResult = new WebComicsResult();
        expectedResult.setTitle("Circles");
        expectedResult.setPublishDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse("2021-03-12T00:00:00Z"));
        expectedResult.setWebUrl("https://xkcd.com/2436/");
        expectedResult.setPictureUrl("https://imgs.xkcd.com/comics/circles.png");
        List<WebComicsResult> webComicsResultList = XmlParserUtils.parseXkcdXMLToEntities(readXmlFromFile("xkcd.xml"));
        Assertions.assertNotNull(webComicsResultList);
        Assertions.assertNotNull(webComicsResultList.get(0));
        Assertions.assertEquals(webComicsResultList.get(0), expectedResult);
    }

    @Test
    public void shouldReturnValidPBLBeanWhenPassedValidXml() throws Exception {
        WebComicsResult expectedResult = new WebComicsResult();
        expectedResult.setTitle("Here For");
        expectedResult.setPublishDate(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse("Fri, 12 Mar 2021 18:01:48 +0000"));
        expectedResult.setPictureUrl("https://poorlydrawnlines.com/wp-content/uploads/2021/03/here_for.png");
        expectedResult.setWebUrl("https://poorlydrawnlines.com/?p=7963");
        List<WebComicsResult> webComicsResultList = XmlParserUtils.parsePdlXMLToEntries(readXmlFromFile("pdl.xml"));
        Assertions.assertNotNull(webComicsResultList);
        Assertions.assertNotNull(webComicsResultList.get(0));
        Assertions.assertEquals(webComicsResultList.get(0), expectedResult);
    }
}
