package com.geekbrains.part2.lesson3;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String[] coffeeMenu = {"Espresso", "Americano", "Latte", "Cappuccino"};
        replaceArrayElement(coffeeMenu,0, 2);

        Box<Apple> firstBox = new Box(new Apple());
        Box<Orange> secondBox = new Box(new Orange());
        Box<Orange> thirdBox = new Box(new Orange());

        fruitBoxAction(firstBox, secondBox, thirdBox);

    }

    //Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
    private static void replaceArrayElement(Object[] array, int firstIndex, int secondIndex) {
        System.out.println("Старый массив:" + Arrays.toString(array));
        Object buffer = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = buffer;
        System.out.println("Новый массив:" + Arrays.toString(array));
        System.out.println("==============================");
    }

    private static void fruitBoxAction(Box<Apple> firstBox, Box<Orange> secondBox, Box<Orange> thirdBox) {
        //добавить по одному фрукту
        firstBox.addFruit(new Apple());
        secondBox.addFruit(new Orange());
        thirdBox.addFruit(new Orange());

        // сравнить 2 одинаковых ящика
        System.out.println("Вес первой коробки (яблоки) " + firstBox.getBoxWeight());
        System.out.println("Вес второй коробки (апельсины) " + secondBox.getBoxWeight());
        System.out.println("Вес третей коробки (апельсины) " + thirdBox.getBoxWeight());
        System.out.println(secondBox.compare(thirdBox));

        //сравнить 2 разных ящика
        System.out.println(firstBox.compare(thirdBox));

        //пересыпать все из предыдущей коробки
        secondBox.addFruits(thirdBox);
        System.out.println("Вес второй коробки (апельсины) " + secondBox.getBoxWeight());
        System.out.println("Вес третей коробки (апельсины) " + thirdBox.getBoxWeight());

        // добавить апельсин
        thirdBox.addFruit(new Orange());
        System.out.println("Вес третей коробки (апельсины) " + thirdBox.getBoxWeight());
    }
}
