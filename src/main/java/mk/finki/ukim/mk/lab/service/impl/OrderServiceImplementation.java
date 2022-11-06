package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplementation implements OrderService {
    Order order = null;

    @Override
    public Order placeOrder(String balloonColor, String clientName, String address) {
        return new Order(balloonColor,"",clientName, address, (long) 43);
    }
}