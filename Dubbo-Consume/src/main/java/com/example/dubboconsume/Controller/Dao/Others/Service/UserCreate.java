package com.example.dubboconsume.Controller.Dao.Others.Service;

import com.example.dubboconsume.Controller.Dao.Others.Habit;
import com.example.dubboconsume.Controller.Dao.Others.Hobby;
import com.example.dubboconsume.Controller.Dao.Others.Ocupation;
import com.example.dubboconsume.Controller.Dao.Others.User;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserCreate {
    private Random random =new Random();
    public UserCreate(){}

    public User generateRandomUser(){
        User user = new User();
        user.setAge(generateRandomAge());
        user.setGender(generateRandomGender());
        user.setHabit(generateRandomHabit());
        user.setName(generateRandomName());
        user.setHobby(generateRandomHobby());
        user.setOcupation(generateRandomOcupation());
        return user;
    }

    private String generateRandomName() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        int length = random.nextInt(6)+2;
        StringBuilder name = new StringBuilder(upper.charAt(random.nextInt(upper.length())));
        for(int i=0;i<length;i++){
            name.append(lower.charAt(random.nextInt(lower.length())));
        }
        return name.toString();
    }

    private int generateRandomAge() {
        return random.nextInt(60)+15;
    }

    private String generateRandomGender() {
        String[] gender = {"Man","Woman"};
        return gender[random.nextInt(1)];
    }

    private Ocupation generateRandomOcupation() {
        Ocupation[] professions = Ocupation.values();
        return professions[random.nextInt(professions.length)];
    }

    private Hobby generateRandomHobby() {
        Hobby[] hobbies = Hobby.values();
        return hobbies[random.nextInt(hobbies.length)];
    }

    private Habit generateRandomHabit() {
        Habit[] habits = Habit.values();
        return habits[random.nextInt(habits.length)];
    }
}

