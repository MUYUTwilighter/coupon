package com.example.dubboconsume.Controller.Dao.Others.Service;

import com.example.dubboconsume.Controller.Dao.Others.PriceRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GeneratePrice {
    private final double weight = 0.7;
    private Random random = new Random();
    private PriceRange rangeOfOcupation;
    private PriceRange rangeOfHobby;
    private int min;
    private int max;

    GeneratePrice(){}

    GeneratePrice(PriceRange rangeOfOcupation,PriceRange rangeOfHobby){
        this.rangeOfHobby=rangeOfHobby;
        this.rangeOfOcupation=rangeOfOcupation;
    }
    public void setRangeOfOcupation(PriceRange rangeOfOcupation) {
        this.rangeOfOcupation = rangeOfOcupation;
    }

    public void setRangeOfHobby(PriceRange rangeOfHobby) {
        this.rangeOfHobby = rangeOfHobby;
    }


    public int generatePrice(){
        double randomDouble = random.nextDouble();
        if(randomDouble>weight){
            min = rangeOfOcupation.getMin();
            max = rangeOfOcupation.getMax();
        }else{
            min = rangeOfHobby.getMin();
            max = rangeOfHobby.getMax();
        }
        return random.nextInt(max-min)+min;
    }


}
