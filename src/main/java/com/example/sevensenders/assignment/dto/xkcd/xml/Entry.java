package com.example.sevensenders.assignment.dto.xkcd.xml;


import lombok.Data;

@Data
public class Entry {
    String title;
    Link link;
    String updated;
    String id;
    String summary;
}
