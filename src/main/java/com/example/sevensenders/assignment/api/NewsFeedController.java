package com.example.sevensenders.assignment.api;

import com.example.sevensenders.assignment.dto.WebComicsResult;
import com.example.sevensenders.assignment.service.WebComicsRetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/newsFeed")
public class NewsFeedController {

    @Autowired
    private WebComicsRetrieveService connector;

    @GetMapping
    public List<WebComicsResult> getFeed() {
      return  connector.getSortedResultFromWebsites();
    }


}
