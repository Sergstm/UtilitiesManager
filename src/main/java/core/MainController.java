package core;

import org.springframework.stereotype.Controller;
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

    //Maintenance mode
//    @RequestMapping("/")
//    public String construction() {
//        return "construction";
//    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addOrder(@RequestParam String name,
                           BigDecimal prev, BigDecimal pres, BigDecimal tariff) {
        orderService.addOrder(name, prev, pres, tariff);
        return "Saved";
    }



}
