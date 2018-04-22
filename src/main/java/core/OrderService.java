package core;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(String name, BigDecimal prev, BigDecimal pres, BigDecimal tariff) {
        LocalDateTime dateTime = LocalDateTime.now();
        Order order = new Order(name, prev, pres, tariff, dateTime);
        orderRepository.save(order);
    }
}
