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

    //Index page
    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Order> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "index";
    }

    //Orders==============================================================================
    @RequestMapping("/addOrder")
    public String addOrder(Model model) {
        Iterable<OrderTemplate> orderTemplates = orderService.getTemplates();
        model.addAttribute("orderTemplates", orderTemplates);
        return "addOrder";
    }

    @RequestMapping("/saveOrder")
    public String saveOrder(@RequestParam long id, BigDecimal prev, BigDecimal pres) {
        orderService.saveOrder(id, prev, pres);
        return "redirect:/";
    }

    //Templates====================================================================================
    @RequestMapping("/addTemplate")
    public String addTemplate() {
        return "addTemplate";
    }

    @RequestMapping("/saveTemplate")
    public String saveTemplate(@RequestParam(value = "tariff2", required = false) BigDecimal tariff2,
                               String name, BigDecimal tariff1) {
        orderService.saveTemplate(name, tariff1, tariff2);
        return "redirect:/";
    }




}
