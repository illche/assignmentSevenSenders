package com.example.sevensenders.assignment.dto.pdl;

import lombok.Data;

import javax.xml.bind.annotation.*;



@Data
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    private String title;
    private String link;
    private String pubDate;
    private String guid;
    @XmlElement(namespace = "http://purl.org/rss/1.0/modules/content/")
    private String encoded;

}
