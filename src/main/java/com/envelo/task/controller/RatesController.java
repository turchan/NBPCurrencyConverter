package com.envelo.task.controller;

import com.envelo.task.model.rates.Rate;
import com.envelo.task.service.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/rates")
public class RatesController {

    private final RateService rateService;

    public RatesController(RateService currenciesService) {
        this.rateService = currenciesService;
    }

    @GetMapping
    public List<Rate> getAllRates() {
        return rateService.findAll();
    }

    @GetMapping("/{code}")
    public Optional<Rate> getRateByCode(@PathVariable String code) {
        return rateService.findByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rate> fetchRatesFromApi() throws JsonProcessingException {

        rateService.save();

        return rateService.findAll();
    }
}
