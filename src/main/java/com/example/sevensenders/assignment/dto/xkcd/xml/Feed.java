package com.example.sevensenders.assignment.dto.xkcd.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "feed")
@Data
public class Feed {
    private String title;
    private Link link;
    private String id;
    private String updated;
    private List<Entry> entry;

}
