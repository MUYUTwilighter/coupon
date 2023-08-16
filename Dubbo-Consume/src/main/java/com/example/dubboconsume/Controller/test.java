package com.example.dubboconsume.Controller;

import com.example.dubboconsume.Controller.Dao.Consume;

import com.example.dubboconsume.Controller.Dao.Others.Service.BuyProduct;
import com.example.dubboconsume.Controller.Dao.Others.Service.IsBuy;
import com.example.dubboconsume.Controller.Dao.Others.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class test {
    @Autowired
    BuyProduct buyProduct;

    @ResponseBody
    @RequestMapping("/get")
    public String returnInfor() throws IOException {
        IsBuy isBuy = buyProduct.buyProduct();
        return isBuy.toString();

    }
}
