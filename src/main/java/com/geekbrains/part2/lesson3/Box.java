package com.geekbrains.part2.lesson3;

import java.util.ArrayList;

public class Box <T>  {
    private double fruitWeight;
    ArrayList<T> fruitBox = new ArrayList();

    public Box (Fruit fruits) {
       this.fruitWeight = fruits.getWeight();
   }

    public boolean compare(Box o) {
        return (this.fruitWeight - o.fruitWeight == 0);
    }

    public void addFruit(T fruit) {
        fruitBox.add(fruit);
    }

    public void addFruits(Box<T> box) {
        fruitBox.addAll(box.fruitBox);
        box.fruitBox.clear();
    }

    public double getBoxWeight(){
        return fruitBox.size() * fruitWeight;
    }
}
