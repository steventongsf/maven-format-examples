package com.stong.jdk8;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestFilterMap {
    Map<Integer, String> hosting = new HashMap<>();

    @Before
    public void before() {
        hosting.put(1, "digitalocean.com");
        hosting.put(2, "aws.amazon.com");
        hosting.put(3, "azure.com");
        hosting.put(4, "heroku.com");
    }

    @Test
    public void filterMap() {
        String result = "";
        for (Map.Entry<Integer, String> entry : hosting.entrySet()) {
            if ("aws.amazon.com".equals(entry.getValue())) {
                result = entry.getValue();
            }
        }
        System.out.println("Before JDK8: " + result);
        result = hosting.entrySet().stream()
                .filter(map -> "aws.amazon.com".equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());
        System.out.println("With JDK8: " + result);
        result = hosting.entrySet().stream()
                .filter(x -> {
                    if (!x.getValue().contains("amazon") && !x.getValue().contains("digital")) {
                        return true;
                    }
                    return false;
                })
                .map(map -> map.getValue())
                .collect(Collectors.joining(","));
        System.out.println("With JDK8: " + result);
    }

    @Test
    public void filterMapByKey() {
        String result = "";
        Map<Integer, String> collect = hosting.entrySet().stream()
                .filter(map -> map.getKey() == 2)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        System.out.println(collect);
        Map<Integer, String> collect2 = hosting.entrySet().stream()
                .filter(map -> map.getKey() <= 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(collect2); //output : {1=linode.com, 2=heroku.com, 3=digitalocean.com}
    }
    @Test
    public void streamToMap() {
        String result = "";
        Map<Integer, String> collect = hosting.entrySet().stream()
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        System.out.println(collect);
    }
    <K,V> Map<K,V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet()
                .stream()
                .filter(x -> predicate.test(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }
    @Test
    public void predicateFilter1() {
        Map<Integer, String> filteredMap = filterByValue(hosting, x -> x.contains("heroku.com"));
        System.out.println(filteredMap);
    }
    @Test
    public void predicateFilter2() {
        Map<Integer, String> filteredMap =
                filterByValue(hosting, x -> x.contains("heroku") || x.contains("aws"));
        System.out.println(filteredMap);
    }
}
