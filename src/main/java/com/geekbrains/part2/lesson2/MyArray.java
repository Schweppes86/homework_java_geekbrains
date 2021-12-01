package com.geekbrains.part2.lesson2;

public class MyArray {

    public static void main(String[] args) {
        String [][] firstArray = {{"5","7","3","17"}, {"7","0","1","12"}, {"8","1","2","3"}};
        String [][] secondArray = {{"1","1","1","1"}, {"1","1","s","1"}, {"1","1","1","1"}, {"1","1","1","1"}};
        String [][] thirdArray = {{"1","1","1","1"}, {"1","1","1","1"}, {"1","1","1","1"}, {"1","1","1","1"}};
        ArrayOperation array = new ArrayOperation();

        try {
            //wrong size
            //array.sumAllElements(firstArray);

            // one invalid element
             array.sumAllElements(secondArray);

            //normal
            // array.sumAllElements(thirdArray);
        }

        catch (MyArraySizeException e) {
            e.printStackTrace();
        }

        catch (MyArrayDataException e) {
            System.out.println("Строка " + array.getStr() + ", столбец " + array.getCol() + " - элемент не удалось распарсить!");
            e.printStackTrace();
        }
    }
}
