package com.example.sevensenders.assignment.dto.xkcd.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Data
public class Link {
    @XmlAttribute
    private String href;
    @XmlAttribute
    private String rel;

}
