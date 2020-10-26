package com.envelo.task.service.serviceImpl;

import com.envelo.task.model.actioans.Action;
import com.envelo.task.model.actioans.ActionName;
import com.envelo.task.model.rates.Rate;
import com.envelo.task.model.rates.RateJSON;
import com.envelo.task.model.rates.RatesUrl;
import com.envelo.task.repository.ActionRepository;
import com.envelo.task.repository.RateRepository;
import com.envelo.task.service.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class RateServiceImpl implements RateService {

    private final RestTemplate restTemplate;
    private final RatesUrl ratesUrl;
    private final RateRepository rateRepository;
    private final ActionRepository actionRepository;

    public RateServiceImpl(RestTemplate restTemplate,
                           RatesUrl ratesUrl,
                           RateRepository rateRepository,
                           ActionRepository actionRepository) {
        this.restTemplate = restTemplate;
        this.ratesUrl = ratesUrl;
        this.rateRepository = rateRepository;
        this.actionRepository = actionRepository;
    }
	
	/**
	* Method for fetching the rate list from database
	* @return This return the list of rates from database
	*/
    @Override
    public List<Rate> findAll() {

        Action action = new Action.Builder()
                .setAction_id(null)
                .setActionName(ActionName.GET_ALL_RATES)
                .setRegistrationTime(LocalDateTime.now())
                .build();

        actionRepository.save(action);

        log.info("Rates were fetched from the database");

        return rateRepository.findAll();
    }

	/**
	* Method for fetching the code of rate from database
	* @param code the value of code in Rate
	* @return Optional<Rate> This return the object of Rate with the code
	*/
    @Override
    public Optional<Rate> findByCode(String code) {

        Action action = new Action.Builder()
                .setAction_id(null)
                .setActionName(ActionName.GET_RATES_BY_CODE)
                .setRegistrationTime(LocalDateTime.now())
                .build();

        actionRepository.save(action);

        log.info("Rate with the code " + code + " was fetched from the database");

        return rateRepository.findByCode(code);
    }

	/**
	* Method for fetching the data fron NBP WEB API and saving in to the database
	* @return Nothing
	*/
    @Override
    public void save() throws JsonProcessingException {

        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(ratesUrl.getUrl())
                .path("")
                .query("format={format}")
                .buildAndExpand("json");

        String uri = uriComponents.toUriString();

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 0; i < 13; i++) {
            List<RateJSON> rateJSON = objectMapper.readValue(Objects.requireNonNull(responseEntity.getBody()), new TypeReference<List<RateJSON>>() {
            });

            for (RateJSON object : rateJSON) {

                Optional<Rate> rateJSONObject = rateRepository.findByCode(object.getCode());

                if (rateJSONObject.isPresent()) {

                    Rate rate = new Rate.Builder()
                            .setRate_id(rateJSONObject.get().getRate_id())
                            .setCurrency(object.getCurrency())
                            .setCode(object.getCode())
                            .setBid(object.getBid())
                            .setAsk(object.getAsk())
                            .setTrading_date(object.getTradingDate())
                            .setEffective_date(object.getEffectiveDate())
                            .build();

                    rateRepository.save(rate);

                    log.info("The Rates for code: " + rate.getCode() + "was updated");

                } else {

                    Rate rate = new Rate.Builder()
                            .setRate_id(null)
                            .setCurrency(object.getCurrency())
                            .setCode(object.getCode())
                            .setBid(object.getBid())
                            .setAsk(object.getAsk())
                            .setTrading_date(object.getTradingDate())
                            .setEffective_date(object.getEffectiveDate())
                            .build();

                    rateRepository.save(rate);

                    log.info("The Rates for code: " + rate.getCode() + "was created");
                }

                Action action = new Action.Builder()
                        .setAction_id(null)
                        .setActionName(ActionName.FETCH_RATES_FROM_API)
                        .setRegistrationTime(LocalDateTime.now())
                        .build();

                actionRepository.save(action);
            }
        }
    }
}
