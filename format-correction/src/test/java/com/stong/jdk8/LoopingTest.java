package com.stong.jdk8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoopingTest {
    List<Employee> employees = new ArrayList<>();
    List<String> alpha = Arrays.asList("a","b","c","d");
    int age = 50;
    @Before
    public void before() {
        employees.add(new Employee("John Doe", 63));
        employees.add(new Employee("Sally Smith", 29));
        employees.add(new Employee("Bob Jone", 36));
        employees.add(new Employee("Margaret Foster", 53));
    }
    @Test
    public void preJdk8For()
    {
        for (Employee emp: employees)
            if (emp.getAge() < age)
                System.out.println(emp);
    }
    @Test
    public void jdk8For()
    {
        employees.stream()
                .filter(emp -> emp.getAge() < age)
                .forEach(emp -> System.out.println(emp));
    }
    @Test
    public void mapVariations() {
        alpha.stream().map(String::toUpperCase)
                .forEach(val -> System.out.println(val));
        List<String> collect = alpha.stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(collect);
        employees.stream().map(x -> x.getName())
                .collect(Collectors.toList());
        System.out.println(collect);

    }
}
class Employee {
    private String name;

    public String getName() {
        return name;
    }

    private int age;
    Employee(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    int getAge()
    {
        return age;
    }
    @Override
    public String toString()
    {
        return name + ": " + age;
    }

}