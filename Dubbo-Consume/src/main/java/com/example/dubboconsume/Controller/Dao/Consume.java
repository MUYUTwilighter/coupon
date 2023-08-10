package com.example.dubboconsume.Controller.Dao;


import com.example.dubboconsume.Controller.Dao.Others.Service.Coupon;
import com.example.dubboconsume.Controller.Dao.Others.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface Consume {
    List<Coupon> getCoupons(User user) throws JsonProcessingException;
}
