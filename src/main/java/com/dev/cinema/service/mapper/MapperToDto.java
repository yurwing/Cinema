package com.dev.cinema.service.mapper;

public interface MapperToDto<T, S> {
    S getDto(T entity);
}
