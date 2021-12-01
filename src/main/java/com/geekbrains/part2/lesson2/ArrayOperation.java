package com.geekbrains.part2.lesson2;

import static java.lang.Integer.parseInt;

public class ArrayOperation {
    private int col = 0;
    private int str = 0;

    public void sumAllElements(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        arraySizeChecker(array);
        sumAllElements(array, sum);
    }

    private void sumAllElements(String[][] array, int sum) throws MyArrayDataException{

        for (int i = 0; i < array.length; i++) {
            str = i;
            for (int j = 0; j < array[i].length; j++) {
                col = j;
                sum += parseSymbol(array[i][j]);
            }
        }

        System.out.println("Сумма элементов массива = " + sum);
    }

    private void arraySizeChecker (String[][] array) throws MyArraySizeException {
            if (array[0].length != 4) {
                throw new MyArraySizeException("Wrong size");
            }

            if (array.length != 4) {
                throw new MyArraySizeException("Wrong size");
            }
    }

    public int parseSymbol(String element) {
        try {
            return parseInt(element);
        }
        catch (NumberFormatException e) {
            throw new MyArrayDataException("Wrong symbol");
        }
    }

    public int getCol() {
        return col+1;
    }

    public int getStr() {
        return str+1;
    }
}
