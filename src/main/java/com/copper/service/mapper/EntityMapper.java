package com.copper.service.mapper;

public interface EntityMapper <D, E> {

    E toEntity(D dto);
    
    D toDto(E entity);
}
