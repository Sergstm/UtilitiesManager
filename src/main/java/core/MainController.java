package core;

import core.model.Order;
import core.model.OrderTemplate;
import core.service.OrderService;
import core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    private final OrderService orderService;
    private final UserService userService;

    public MainController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    //Index page
    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    //Registration page
    @RequestMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    //Login page
    @RequestMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login";
    }

    //Add user
    @RequestMapping("/addUser")
    public String addUser(@RequestParam String username, String password) {
        userService.addUser(username, password);
        return "login";
    }

    //Denied page
    @RequestMapping("/deny")
    public String denyPage() {
        return "deny";
    }

    //Error page
    @RequestMapping("/errPage")
    public String errorPage() {
        return "errPage";
    }

    //Orders==========================================================================
    @RequestMapping("/addOrder")
    public String addOrder(Model model) {
        String currentLogin = userService.getCurrentLogin();
        List<OrderTemplate> orderTemplates = userService.getData().get()
                .getOrderTemplates();
        List<Order> orders = userService.getData().get().getOrders();
        Collections.reverse(orders);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("Y.MM.dd - HH:mm");
        model.addAttribute("login", currentLogin);
        model.addAttribute("orderTemplates", orderTemplates);
        model.addAttribute("orders", orders);
        model.addAttribute("dtf", dtf);
        return "addOrder";
    }

    @RequestMapping("/saveOrder")
    public String saveOrder(@RequestParam long id, BigDecimal prev, BigDecimal pres) {
        if (pres.compareTo(prev) < 0) {
            return "redirect:/errPage";
        } else {
            orderService.saveOrder(id, prev, pres);
        }
        return "redirect:/addOrder";
    }

    @RequestMapping("/delOrder")
    public String delOrder(@RequestParam long id) {
        orderService.delOrder(id);
        return "redirect:/addOrder";
    }

    //Templates=======================================================================
    @RequestMapping("/addTemplate")
    public String addTemplate(Model model) {
        String currentLogin = userService.getCurrentLogin();
        List<OrderTemplate> createdTemplates = userService.getData().get()
                .getOrderTemplates();

        model.addAttribute("login", currentLogin);
        model.addAttribute("createdTemplates", createdTemplates);
        return "addTemplate";
    }

    @RequestMapping("/saveTemplate")
    public String saveTemplate(@RequestParam(value = "tariff2",
            required = false) BigDecimal tariff2, String name, BigDecimal tariff1) {
        orderService.saveTemplate(name, tariff1, tariff2);
        return "redirect:/addTemplate";
    }

    @RequestMapping("/delTemplate")
    public String delTemplate(@RequestParam long id) {
        orderService.delTemplate(id);
        return "redirect:/addTemplate";
    }

}
