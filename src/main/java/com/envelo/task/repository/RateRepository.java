package com.envelo.task.repository;

import com.envelo.task.model.rates.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Integer> {
    Optional<Rate> findByCode(String code);
}
