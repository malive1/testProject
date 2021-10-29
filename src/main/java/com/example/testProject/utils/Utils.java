package com.example.testProject.utils;

import com.example.testProject.entity.ResultsRequests;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pavel
 * Util functional class
 */
@Service
public class Utils {
    public String listAggInfo(LinkedList<ResultsRequests> inList){
        String rez = "";
        Iterable<String> listRez = Iterables.transform(inList,
                new Function<ResultsRequests, String>() {
                    public String apply(ResultsRequests from) {
                        return from.getResultInformation();
                    }
                });

        List<String> actualList = Lists.newArrayList(listRez);
        rez  = actualList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("-", "{", "}"));
        return rez;
    }
}
