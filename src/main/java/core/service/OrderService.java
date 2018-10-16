package core.service;

import core.model.CustomUser;
import core.model.Order;
import core.model.OrderTemplate;
import core.repository.OrderRepository;
import core.repository.OrderTemplateRepository;
import core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderTemplateRepository orderTemplateRepository;
    public OrderService(UserService userService, UserRepository userRepository,
                        OrderRepository orderRepository,
                        OrderTemplateRepository orderTemplateRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
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

        String currentLogin = userService.getCurrentLogin();
        CustomUser customUser = userRepository.findByLogin(currentLogin).get();
        List<Order> orders = customUser.getOrders();
        int size = orders.size();
        orders.add(size, order);
        userRepository.save(customUser);
    }

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void delOrder(long id) {
        orderRepository.deleteById(id);
    }

    //Templates========================================================================
    public void saveTemplate(String name, BigDecimal tariff1, BigDecimal tariff2) {
        if (tariff2 == null) {
            tariff2 = BigDecimal.ZERO;
        }
        OrderTemplate orderTemplate = new OrderTemplate(name, tariff1, tariff2);

        String currentLogin = userService.getCurrentLogin();
        CustomUser customUser = userRepository.findByLogin(currentLogin).get();
        List<OrderTemplate> templates = customUser.getOrderTemplates();
        int size = templates.size();
        templates.add(size, orderTemplate);
        userRepository.save(customUser);
    }

    public Iterable<OrderTemplate> getTemplates() {
        return orderTemplateRepository.findAll();
    }

    public void delTemplate(long id) {
        orderTemplateRepository.deleteById(id);
    }

}
