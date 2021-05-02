/* User class: */
/* Class for storing user information */

package com.example.harjoitusty20;

public class User {

    private String name;
    private int age;
    private String height;
    private String area;
    private String membership;

    public User(String name, int age, String height, String area, String membership) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.area = area;
        this.membership = membership;
    }

    @Override
    public String toString() {
        return (" Age:  " + this.getAge() +
                "\n Height:  " + this.getHeight() +
                "\n Area:  " + this.getArea() +
                "\n Membership:  " + this.getMembership());
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getArea() {
        return area;
    }

    public String getMembership() {
        return membership;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }
}
