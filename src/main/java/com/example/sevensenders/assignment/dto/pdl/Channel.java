package com.example.sevensenders.assignment.dto.pdl;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "channel")
@Data
public class Channel {

    List<Item> item;

}
