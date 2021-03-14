package com.example.sevensenders.assignment.utils;

import com.example.sevensenders.assignment.dto.WebComicsResult;
import com.example.sevensenders.assignment.dto.xkcd.json.WebComicsXkcdDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class BeanParserUtils {
    private BeanParserUtils() {
    }

    public static WebComicsResult parseXkcdJsonBean(WebComicsXkcdDTO bean) {
        WebComicsResult webComicsResult = new WebComicsResult();
        webComicsResult.setWebUrl(bean.getLink());
        webComicsResult.setTitle(bean.getTitle());
        webComicsResult.setPictureUrl(bean.getImg());
        try {
            webComicsResult.setPublishDate(new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH).parse(bean.getDay()+bean.getMonth()+bean.getYear()));
        } catch (Exception ignored) {
        }
        return webComicsResult;
    }
}
