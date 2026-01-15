package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
@RequestMapping("/order")
public class OrderServiceApplication {

 public PaymentClient paymentClient;

 public OrderServiceApplication(PaymentClient paymentClient){
   this.paymentClient=paymentClient;
 }

 @GetMapping("/{id}")
 public String order(@PathVariable int id) {

  //check payment status
  String str = paymentClient.pay(id);
  return "Order created with id " + id + " Payment : "+str;
 }

 public static void main(String[] args) {

  SpringApplication.run(OrderServiceApplication.class, args);
 }
}
