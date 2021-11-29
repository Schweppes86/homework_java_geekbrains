package com.geekbrains.part2.lesson1;

public class lesson1 {

    public static void main(String[] args) {

        Course course = new Course(new Obstacle(), new Obstacle());

        Team firstTeam = new Team("Чемпионы прыжков",
                new Member("Константин", 8),
                new Member("Василий", 2),
                new Member("Юрий", 10),
                new Member("Никита" ,5));

        course.doIt(firstTeam);
        firstTeam.showResults();
    }
}
