package com.stong.jdk8.nio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class TestReadFile {
    String getFileName() {
        return "lines.txt";
    }
    @Test
    public void readFile() {
        String fileName = "lines.txt";
        try (Stream<String> stream = Files.lines(Paths.get(this.getFileName()))) {
            stream.forEach(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }
    @Test
    public void readFilteredFile() {
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(this.getFileName()))) {

            //1. filter line 3
            //2. convert all content to upper case
            //3. convert it into a List
            list = stream
                    .filter(line -> !line.startsWith("line3"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);

    }
    @Test
    public void readFileWithLines() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(getFileName()))) {
            list = br.lines().collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        list.forEach(System.out::println);
    }
    @Test
    public void readFileWithBufferedReader() {
        try(BufferedReader br = new BufferedReader(new FileReader(getFileName()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    @Test
    public void readFileWithScanner() {
        try (Scanner scanner = new Scanner(new File(getFileName()))) {

            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
