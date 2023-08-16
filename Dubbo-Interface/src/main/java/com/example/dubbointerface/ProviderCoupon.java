package com.example.dubbointerface;

import com.fasterxml.jackson.core.JsonProcessingException;



public interface ProviderCoupon {
    String getCoupons(String habit) throws JsonProcessingException;
}
