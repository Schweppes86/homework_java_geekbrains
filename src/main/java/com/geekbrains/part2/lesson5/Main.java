package com.geekbrains.part2.lesson5;

public class Main {
    public static void main(String[] args) {
        AppData appData = new AppData();
        appData.readFile("test.csv");
        appData.writeToCSV("new.csv");
    }
}
