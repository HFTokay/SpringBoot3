package com.example.demo.util.springaop;

import com.example.demo.util.springaop.annotation.RequiredLog;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DefaultSearchService implements  SearchService{

    @RequiredLog
    @Override
    public Object search(String key) {
        System.out.println("DefaultSearchService.search(): "+key);
        return null;
    }
}
