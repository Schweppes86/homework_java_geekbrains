package com.geekbrains.part2.lesson8;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Course course = new StudentCourse("Math");
        Course course1 = new StudentCourse("Biology");
        Course course2 = new StudentCourse("Geography");
        Course course3 = new StudentCourse("History");
        Course course4 = new StudentCourse("English");
        Course course5 = new StudentCourse("Russian");
        Course course6 = new StudentCourse("Astronomy");
        Course course7 = new StudentCourse("IT");
        Course course8 = new StudentCourse("Literature");
        Course course9 = new StudentCourse("Physical Activity");

        Student studentIvan = new StudentOfCollage("Ivan", Arrays.asList(course, course1, course2, course3));
        Student studentElena = new StudentOfCollage("Elena", Arrays.asList(course1, course5, course6));
        Student studentNikita = new StudentOfCollage("Nikita", Arrays.asList(course1, course5, course3, course8, course9));
        Student studentSasha = new StudentOfCollage("Sasha", Arrays.asList(course, course2));
        System.out.println("---------Список курсов и людей-------------");
        List<Student> students = Arrays.asList(studentIvan, studentElena, studentNikita, studentSasha);
        System.out.println(students);
        System.out.println("1. Вывод всех уникальных курсов, которые посещают студенты");
        System.out.println(uniqCourseNames(students));
        System.out.println("2. Самые любознательные студенты");
        System.out.println(mostCuriousStudent(students));
        System.out.println("3. Список студентов, посещающих Math");
        System.out.println(studentsThisCourse(students, course));
    }


    //1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов, на которые подписаны студенты.
    public static Set<String> uniqCourseNames(List<Student> students) {
        return students.stream()
                .map(Student::getAllCourses)
                .flatMap(List::stream)
                .map(Course::getStudentCourseName)
                .collect(Collectors.toSet());
    }

    //2. Написать функцию, принимающую на вход список Student и
    // возвращающую список из трех самых любознательных (любознательность определяется количеством курсов).
    public static List<String> mostCuriousStudent(List<Student> students) { //Так просто имена
        return students.stream()
                .sorted((student1, student2) -> student2.getAllCourses().size() - student1.getAllCourses().size())
                .limit(3)
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    //3. Написать функцию, принимающую на вход список Student и экземпляр Course,
    // возвращающую список студентов, которые посещают этот курс.
    public static List<String> studentsThisCourse(List<Student> students, Course course) {
        return students.stream()
                .filter(a -> a.getAllCourses().stream().anyMatch(course1 -> course1.equals(course)))
                .map(Student::getName)
                .collect(Collectors.toList());
    }
}

class StudentOfCollage implements Student {
    private String name;
    private List<Course> courses;

    public StudentOfCollage(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(this.courses);
    }

    @Override
    public String toString() {
        return "\nСтудент " + name + " посещает курсы: " + courses;
    }
}

class StudentCourse implements Course {
    private String nameStudentCourse;

    public StudentCourse(String nameStudentCourse) {
        this.nameStudentCourse = nameStudentCourse;
    }

    public String getNameStudentCourse() {
        return nameStudentCourse;
    }

    @Override
    public String getStudentCourseName() {
        return this.nameStudentCourse;
    }

    @Override
    public String toString() {
        return getNameStudentCourse();
    }
}

interface Student {
    String getName();
    List<Course> getAllCourses();
}

interface Course {
    String getStudentCourseName();
}
