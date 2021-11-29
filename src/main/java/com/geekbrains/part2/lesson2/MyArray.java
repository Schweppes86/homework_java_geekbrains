package com.geekbrains.part2.lesson2;

public class MyArray {

    public static void main(String[] args) {
        String [][] firstArray = {{"5","7","3","17"}, {"7","0","1","12"}, {"8","1","2","3"}};
        String [][] secondArray = {{"1","1","1","1"}, {"1","1","s","1"}, {"1","1","1","1"}, {"1","1","1","1"}};
        String [][] thirdArray = {{"1","1","1","1"}, {"1","1","1","1"}, {"1","1","1","1"}, {"1","1","1","1"}};

        //wrong size
        // new ArraySum().sumAllElements(firstArray);

        // one invalid element
        // new ArraySum().sumAllElements(secondArray);

        //normal array
        new ArraySum().sumAllElements(thirdArray);
    }
}
