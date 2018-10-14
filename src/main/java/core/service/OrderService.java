package core.service;

import core.model.Order;
import core.model.OrderTemplate;
import core.repository.OrderRepository;
import core.repository.OrderTemplateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderTemplateRepository orderTemplateRepository;
    public OrderService(OrderRepository orderRepository,
                        OrderTemplateRepository orderTemplateRepository) {
        this.orderRepository = orderRepository;
        this.orderTemplateRepository = orderTemplateRepository;
    }

    //Orders=========================================================================
    public void saveOrder(long id, BigDecimal prev, BigDecimal pres) {
        OrderTemplate orderTemplate = orderTemplateRepository.findById(id).get();
        BigDecimal tariff1 = orderTemplate.getTariff1();
        BigDecimal tariff2 = orderTemplate.getTariff2();
        String name = orderTemplate.getName();

        BigDecimal vol = pres.subtract(prev);
        BigDecimal price;

        if (tariff2 == null) {
            price = vol.multiply(tariff1);
        } else {
            if (vol.compareTo(BigDecimal.valueOf(100)) < 1) {
                price = vol.multiply(tariff1);
            } else {
                BigDecimal result = new BigDecimal("100").multiply(tariff1);
                BigDecimal res = vol.subtract(new BigDecimal("100"));
                BigDecimal sum = res.multiply(tariff2);
                price = result.add(sum);
            }
        }
        LocalDateTime dateTime = LocalDateTime.now();
        Order order = new Order(name, prev, pres, tariff1, tariff2, vol, price, dateTime);
        orderRepository.save(order);
    }

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    //Templates========================================================================
    public void saveTemplate(String name, BigDecimal tariff1, BigDecimal tariff2) {
        if (tariff2 == null) {
            tariff2 = BigDecimal.ZERO;
        }
        OrderTemplate orderTemplate = new OrderTemplate(name, tariff1, tariff2);
        orderTemplateRepository.save(orderTemplate);
    }

    public Iterable<OrderTemplate> getTemplates() {
        return orderTemplateRepository.findAll();
    }

}