package com.example.sevensenders.assignment.dto.pdl;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rss")
@Data
public class RSS {
    private Channel channel;

}
