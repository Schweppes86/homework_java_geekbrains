package com.geekbrains.part2.lesson4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(
                "я", "мог", "бы", "выпить", "море",
                "я", "мог", "бы", "стать", "другим",
                "вечно", "молодым",
                "вечно", "пьяным",
                "я", "мог", "бы", "стать", "рекой",
                "быть", "тёмною", "водой",
                "вечно", "молодой",
                "вечно", "пьяный"));

        firstExercise(wordList);
        secondExercise();
    }

    //Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
    // Посчитать, сколько раз встречается каждое слово.
    public  static void firstExercise(ArrayList<String> arrayList) {
        System.out.println("Исходный массив: " + arrayList);
        Set<String> uniqueWords = new LinkedHashSet<>();
        uniqueWords.addAll(arrayList);
        System.out.println("Список уникальных слов:" + uniqueWords);

        Iterator<String> iter = uniqueWords.iterator();
        while (iter.hasNext()) {
            int counter = 0;
            String str = iter.next();
            for(String words: arrayList) {
                if (str.equals(words)) {
                    counter+=1;
                }
            }
            System.out.println("Слово " + str + " повторялось " + counter + " раз");
        }
    }

    //Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров.
    // В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get()
    // искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
    // (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.

    public  static void secondExercise() {
        System.out.println("\n===========================================\n");
        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванова", 32211);
        phonebook.add("Петров", 31512);
        phonebook.add("Лосев", 32565);
        phonebook.add("Иванова", 30003);
        phonebook.get("Иванова");

    }
}
