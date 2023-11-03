package com.sistemafichajes.repository;

import com.sistemafichajes.controller.dto.outputs.PersonOutputDto;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface PersonRepositoryCustom {
    public List<PersonOutputDto> getGreaterQuery(
            HashMap<String, Object> conditions, Pageable pageable);
    public List<PersonOutputDto> getLessQuery(
            HashMap<String, Object> conditions,Pageable pageable);
}
