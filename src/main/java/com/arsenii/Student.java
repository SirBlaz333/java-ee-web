package com.arsenii;

public class Student {
    private final String firstname;
    private final String lastname;
    private final int age;
    private final String email;
    private final String group;
    private final String faculty;

    public Student(String firstname, String lastname, int age, String email, String group, String faculty) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.group = group;
        this.faculty = faculty;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getFaculty() {
        return faculty;
    }
}
