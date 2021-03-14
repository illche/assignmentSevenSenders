package com.example.sevensenders.assignment.dto.xkcd.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "feed")
public class Feed {
    private String title;
    private Link link;
    private String id;
    private String updated;


    private List<Entry> entry;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated() {
        return updated;
    }


    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
