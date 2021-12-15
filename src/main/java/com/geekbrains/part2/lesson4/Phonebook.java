package com.geekbrains.part2.lesson4;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Phonebook {
    HashMap<Integer, String> hm;

    public Phonebook() {
        this.hm = new HashMap<>();
    }

    public void add(String name, int phone) {
        hm.put(phone, name);
    }

    public void get(String name) {
        Iterator iterator = hm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            if (pair.getValue() == name) {
                System.out.println("Найдена запись: " + pair.getValue() + " телефон: " + pair.getKey());
            }
        }
    }
}
