package com.geekbrains.part2.lesson2;

import static java.lang.Integer.parseInt;

public class ArraySum {
    public void sumAllElements(String[][] array) {
        int sum = 0;

        try {
            arraySizeChecker(array);
            sumAllElements(array, sum);
        }

        catch (MyArraySizeException e) {
            e.printStackTrace();
        }
    }

    private void sumAllElements(String[][] array, int sum) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                try {
                    sum += parseSymbol(array[i][j]);
                }

                catch (MyArrayDataException e) {
                    e.printStackTrace();
                    System.out.println("Строка " + i + ", столбец " + j + " - элемент не удалось распарсить!");
                }
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
}
