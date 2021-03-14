package com.example.sevensenders.assignment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WebComicsResult {
    private String pictureUrl;
    private String title;
    private String webUrl;
    private Date publishDate;
}
