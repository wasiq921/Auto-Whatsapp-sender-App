package com.first.liptonapp;

public class UserHelperClass {
    String name, number, email, age, friend;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String number, String email, String age, String friend) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.age = age;
        this.friend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
