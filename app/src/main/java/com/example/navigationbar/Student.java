package com.example.navigationbar;

public class Student {
    private String id;
    private String name;
    private String department;
    private String semister;
    private String section;
    private String phone;

    public Student() {

    }

    public Student(String id, String name, String department, String semister, String section, String phone) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.semister = semister;
        this.section = section;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSemister() {
        return semister;
    }

    public void setSemister(String semister) {
        this.semister = semister;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
