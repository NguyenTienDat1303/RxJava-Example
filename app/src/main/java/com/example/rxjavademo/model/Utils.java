package com.example.rxjavademo.model;

import java.util.ArrayList;

public class Utils {
    public static String TAG = "WHATHEFUCK";


    public static ArrayList<String> getMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("Rx Operator");
        return menus;
    }

    public static Integer[] getNumbers() {
        Integer[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        return nums;
    }

    public static Integer[] getDistinctNumbers() {
        Integer[] nums = {1,1,2,2,2,3,3,4,5,2,3,4,5,1,12,13};
        return nums;
    }

    public static ArrayList<String> getOperatorMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("Basic syntax");
        menus.add("Operator just");
        menus.add("Operator fromArray");
        menus.add("Operator range");
        menus.add("Operator create");
        menus.add("Operator map");
        menus.add("Operator flatMap");
        menus.add("Operator concatMap");
        menus.add("Operator buffer");
        menus.add("Operator filter");
        menus.add("Operator distinct");
        menus.add("Operator skip");
        menus.add("Operator skipLast");
        return menus;
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(
                "student 1",
                "student1@gmail.com",
                27
        ));
        students.add(new Student(
                "student 2",
                "student2@gmail.com",
                20
        ));
        students.add(new Student(
                "student3",
                "student3@gmail.com",
                20
        ));
        students.add(new Student(
                "student 4",
                "student4@gmail.com",
                20
        ));
        students.add(new Student(
                "student 5",
                "student5@gmail.com",
                20
        ));
        return students;
    }
}
