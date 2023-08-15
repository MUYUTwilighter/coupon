package com.example.dubbointerface;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProviderCoupon {
    String distributeCoupons(String hobby) throws JsonProcessingException;
}
