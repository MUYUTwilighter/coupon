package com.example.dubboconsume.Controller;

import com.example.dubboconsume.Controller.Dao.Others.Service.BuyProduct;
import com.example.dubboconsume.Controller.Dao.Others.Service.IsBuy;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
    @Autowired
    BuyProduct buyProduct;

    @RequestMapping("/getInfo")
    public String returnInfor() throws JsonProcessingException {
        IsBuy isBuy = buyProduct.buyProduct();
        return isBuy.toString();
    }
}
