package com.study.fooddeliveryapplication.model;

public class Student {
    private int imageResource;
    private String fullName;
    private String studentID;

    public Student(int imageResource, String fullName, String studentID) {
        this.imageResource = imageResource;
        this.fullName = fullName;
        this.studentID = studentID;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStudentID() {
        return studentID;
    }
}

