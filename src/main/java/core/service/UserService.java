package core.service;

import core.model.CustomUser;
import core.model.Order;
import core.model.OrderTemplate;
import core.repository.UserRepository;
import core.security.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder encoder = PasswordEncoderFactories
            .createDelegatingPasswordEncoder();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(String username, String password) {
        CustomUser user = new CustomUser(username,
                encoder.encode(password), UserRole.USER);

        List<Order> orders = initOrders();
        user.setOrders(orders);

        List<OrderTemplate> orderTemplates = initOrderTemplates();
        user.setOrderTemplates(orderTemplates);

        userRepository.save(user);
    }

    public String getCurrentLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Optional<CustomUser> getData() {
        return userRepository.findByLogin(getCurrentLogin());
    }

    private List<Order> initOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Hot water", new BigDecimal("5"), new BigDecimal("10"),
                new BigDecimal("80"), null, new BigDecimal("5"), new BigDecimal("400"),
                LocalDateTime.now())); return orders;
    }

    private List<OrderTemplate> initOrderTemplates() {
        List<OrderTemplate> orderTemplates = new ArrayList<>();
        orderTemplates.add(new OrderTemplate("Hot water", new BigDecimal("80.5") , null));
        orderTemplates.add(new OrderTemplate("Cold water", new BigDecimal("7.5") , null));
        orderTemplates.add(new OrderTemplate("Electricity", new BigDecimal("0.8") , new BigDecimal("1.6")));
        orderTemplates.add(new OrderTemplate("Heating", new BigDecimal("1350") , null));
        orderTemplates.add(new OrderTemplate("Sewage", new BigDecimal("8.25") , null));
        return orderTemplates;
    }



}
