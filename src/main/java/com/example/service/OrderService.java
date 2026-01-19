package com.example.service;

import com.example.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public String getOrderById(Long id){
        //dummy failure
        if(id==99){
            throw new OrderNotFoundException(id);
        }
        return "Order-" + id;
    }
}
