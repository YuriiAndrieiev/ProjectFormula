package org.example;

import java.util.List;


public class App {
    public static void main(String[] args) {
        OutputListConsoleVersion outputListConsoleVersion = new OutputListConsoleVersion();
        List<String> outputLinesList = outputListConsoleVersion.createOutputListConsoleVersion();
        outputLinesList.forEach(line -> System.out.println(line));
    }
}
