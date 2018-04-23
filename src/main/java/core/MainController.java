package core;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/addOrder")
    public String addOrder(@RequestParam String name,
                         BigDecimal prev, BigDecimal pres, BigDecimal tariff) {
        orderService.addOrder(name, prev, pres, tariff);
        return "redirect:/";
    }

}
