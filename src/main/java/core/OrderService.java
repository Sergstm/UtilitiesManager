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

    public void addOrder(String name, BigDecimal prev, BigDecimal pres,
                         BigDecimal tariff1, BigDecimal tariff2) {
        if (tariff2 == null) {
            tariff2 = BigDecimal.ZERO;

            BigDecimal vol = pres.subtract(prev);
            BigDecimal price = vol.multiply(tariff1);

            LocalDateTime dateTime = LocalDateTime.now();
            Order order = new Order(name, prev, pres, tariff1, tariff2, vol, price, dateTime);
            orderRepository.save(order);
        } else {
            BigDecimal price;

            BigDecimal vol = pres.subtract(prev);
            if (vol.compareTo(BigDecimal.valueOf(100)) < 1) {
                price = vol.multiply(tariff1);
            } else {
                BigDecimal result = new BigDecimal("100").multiply(tariff1);
                BigDecimal res = vol.subtract(new BigDecimal("100"));
                BigDecimal sum = res.multiply(tariff2);
                price = result.add(sum);
            }

            LocalDateTime dateTime = LocalDateTime.now();
            Order order = new Order(name, prev, pres, tariff1, tariff2, vol, price, dateTime);
            orderRepository.save(order);
        }
    }

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }
}
