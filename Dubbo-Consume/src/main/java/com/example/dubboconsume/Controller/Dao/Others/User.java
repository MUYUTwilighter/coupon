package com.example.dubboconsume.Controller.Dao.Others;


public class User {
    private String name;
    private int age;
    private String gender;
    private Ocupation Ocupation;
    private Hobby hobby;
    private Habit habit;

    public User(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Ocupation getOcupation() {
        return Ocupation;
    }

    public void setOcupation(Ocupation ocupation) {
        Ocupation = ocupation;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}
