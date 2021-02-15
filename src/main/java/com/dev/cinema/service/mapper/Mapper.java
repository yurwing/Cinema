package com.dev.cinema.service.mapper;

public interface Mapper<T, S, U> {
    S getDto(T entity);

    T getEntity(U requestDto);
}
