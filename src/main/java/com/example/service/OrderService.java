package com.example.service;

import com.example.dto.OrderItemResponse;
import com.example.dto.OrderPaymentResponse;
import com.example.dto.OrderResponse;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import com.example.entity.OrderPayment;
import com.example.entity.Product;
import com.example.exception.OrderNotFoundException;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository,ProductRepository productRepository){
        this.orderRepository=orderRepository;
        this.productRepository=productRepository;
    }

    public OrderResponse createOrder(){
        Order order=new Order();
        OrderResponse orderResponse;
        order.setOrderNumber("ORD-101");
        order.setTotalamount(1500.0);

        OrderPayment payment=new OrderPayment();
        payment.setPaymentMode("UPI");
        payment.setPaymentStatus("SUCCESS");
        payment.setOrder(order);

        order.setPayment(payment);

        OrderItem i1=new OrderItem();
        i1.setProductName("Phone");
        i1.setQuantity(1);
        i1.setPrice(1000.0);
        i1.setOrder(order);

        OrderItem i2=new OrderItem();
        i2.setProductName("Charger");
        i2.setQuantity(1);
        i2.setPrice(1000.0);
        i2.setOrder(order);

        order.setItems(List.of(i1,i2));

        Product p1=new Product();
        p1.setId(11L);
        p1.setName("Mobile");
        p1.setPrice(1100.0);

        Product p2=new Product();
        p2.setId(12L);
        p2.setName("Mobile");
        p2.setPrice(1100.0);
        order.setProducts(Set.of(p1,p2));
        for(Product p: order.getProducts()){
            productRepository.save(p);
        }

        Order result = orderRepository.save(order);
        ArrayList<OrderItemResponse> listOfItems=new ArrayList<>();
        for(OrderItem item : result.getItems()){
            OrderItemResponse oir=new OrderItemResponse(item.getId(),item.getProductName(), item.getQuantity(), item.getPrice());
            listOfItems.add(oir);
        }

        orderResponse = new OrderResponse(result.getId(), result.getOrderNumber(),
                result.getTotalamount(),
                new OrderPaymentResponse(result.getPayment().getId(),
                        result.getPayment().getPaymentMode(),
                        result.getPayment().getPaymentStatus()),
                 listOfItems);


        return orderResponse;
    }

    public String getOrderById(Long id){
        //dummy failure
        if(id==99){
            throw new OrderNotFoundException(id);
        }
        return "Order-" + id;
    }
}
