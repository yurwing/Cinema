package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.OrderRequestDto;
import com.dev.cinema.model.dto.response.OrderResponseDto;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper implements Mapper<Order,
        OrderResponseDto, OrderRequestDto> {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderMapper(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public OrderResponseDto getDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTicketsId(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setUserEmail(order.getUser().getEmail());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        return orderResponseDto;
    }

    @Override
    public Order getEntity(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        User user = userService.findByEmail(orderRequestDto.getUserEmail()).get();
        order.setUser(user);
        ShoppingCart shoppingCartByUser = shoppingCartService.getByUser(user);
        order.setTickets(shoppingCartByUser.getTickets());
        order.setOrderDate(LocalDateTime.parse(orderRequestDto.getOrderDate()));
        return order;
    }
}
