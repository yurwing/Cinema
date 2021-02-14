package com.dev.cinema.service.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.request.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.response.ShoppingCartResponseDto;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartMapper implements Mapper<ShoppingCart,
        ShoppingCartResponseDto, ShoppingCartRequestDto> {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartMapper(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public ShoppingCartResponseDto getDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setUserEmail(shoppingCart.getUser().getEmail());
        shoppingCartResponseDto.setTicketsId(shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }

    @Override
    public ShoppingCart getEntity(ShoppingCartRequestDto shoppingCartRequestDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userService.findByEmail(shoppingCartRequestDto.getUserEmail()).get());
        ShoppingCart shoppingCartByUser = shoppingCartService.getByUser(userService
                .findByEmail(shoppingCartRequestDto.getUserEmail()).get());
        shoppingCart.setTickets(shoppingCartByUser.getTickets().stream()
                .filter(s -> s.getMovieSession().getId().equals(shoppingCartRequestDto.getMovieSessionId()))
                .collect(Collectors.toList()));
        return shoppingCart;
    }
}
