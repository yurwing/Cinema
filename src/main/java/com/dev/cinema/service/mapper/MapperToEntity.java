package com.dev.cinema.service.mapper;

public interface MapperToEntity<T, S> {
    T getEntity(S requestDto);
}
