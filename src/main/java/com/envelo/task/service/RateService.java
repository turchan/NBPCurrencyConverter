package com.envelo.task.service;

import com.envelo.task.model.rates.Rate;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface RateService {
    List<Rate> findAll();
    Optional<Rate> findByCode(String code);
    void save() throws JsonProcessingException;
}
