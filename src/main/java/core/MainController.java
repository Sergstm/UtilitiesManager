package core;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class MainController {

    private final OrderService orderService;
    public MainController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Order> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "index";
    }

    @RequestMapping("/add")
    public String addPage() {
        return "add";
    }

    @RequestMapping("/preferences")
    public String preferences() {
        return "preferences";
    }

    @RequestMapping("/addOrder")
    public String addOrder(@RequestParam(value = "tariff2", required = false) BigDecimal tariff2,
                           String name, BigDecimal prev, BigDecimal pres, BigDecimal tariff1) {
        orderService.addOrder(name, prev, pres, tariff1, tariff2);
        return "redirect:/";
    }

}
