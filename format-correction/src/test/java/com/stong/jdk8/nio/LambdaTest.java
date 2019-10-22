package com.stong.jdk8.nio;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
    @Test
    public void sortListByAge1() {
        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

        System.out.println("After Sort");

        //lambda here!
        listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());

        //java 8 only, lambda also, to print the List
        listDevs.forEach((developer)->System.out.println(developer));
    }
    public void sortListByAge2() {
        List<Developer> listDevs = getDevelopers();
        //sort by age
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        //lambda
        listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());

        //lambda, valid, parameter type is optional
        listDevs.sort((o1, o2)->o1.getAge()-o2.getAge());
    }
    @Test
    public void sortByName() {
        List<Developer> listDevs = getDevelopers();
        Collections.sort(listDevs, new Comparator<Developer>() {
           @Override
           public int compare(Developer o1, Developer o2) {
               return o1.getName().compareTo(o2.getName());
           }
        });
        listDevs.sort((Developer o1, Developer o2) -> o1.getName().compareTo(o2.getName()));
        listDevs.sort((o1,o2)-> o1.getName().compareTo(o2.getName()));
    }
    private static List<Developer> getDevelopers() {

        List<Developer> result = new ArrayList<Developer>();

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));

        return result;

    }

}
class Developer {
    public String getName() {
        return name;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public int getAge() {
        return age;
    }

    String name;
    BigDecimal number;
    int age;
    public Developer(String name, BigDecimal number, int age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }
    public String toString() {
        return name+","+age;
    }
}