package com.example.springblog.controllers;

import com.example.springblog.models.Order;
import com.example.springblog.models.OrderStatus;
import com.example.springblog.models.User;
import com.example.springblog.repo.OrderRepository;
import com.example.springblog.repo.OrderStatusRepository;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import com.example.springblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@Controller
public class DriverController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @GetMapping("/drivers")
    public String showDriversPage(Model model){

        model.addAttribute("unassignedOrders", orderRepository.findOrdersByOrderStatusId(1));
        model.addAttribute("assignedOrders", orderRepository.findOrdersByOrderStatusId(2));
        return "drivers";
    }

    @PostMapping("/drivers")
    public String assignOrderStatus(@RequestParam("orderId") Long orderId) {
        Order existingOrder = orderRepository.findOne(orderId);

        existingOrder.setOrderStatusId(orderStatusRepository.findById(2));

        orderRepository.save(existingOrder);

        return "redirect:/drivers";
    }


}
