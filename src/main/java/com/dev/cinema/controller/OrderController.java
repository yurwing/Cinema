package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.response.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, UserService userService, ShoppingCartService shoppingCartService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderResponseDto> get(@RequestParam Long userId) {
        return orderService.getOrdersHistory(userService.getById(userId))
                .stream()
                .map(orderMapper::getDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {
        orderService.completeOrder(shoppingCartService.getByUser(userService.getById(userId)));
    }
}
