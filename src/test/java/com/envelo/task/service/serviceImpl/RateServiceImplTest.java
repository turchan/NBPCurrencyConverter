package com.envelo.task.service.serviceImpl;

import com.envelo.task.model.actioans.Action;
import com.envelo.task.model.actioans.ActionName;
import com.envelo.task.model.rates.Rate;
import com.envelo.task.repository.ActionRepository;
import com.envelo.task.repository.RateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class RateServiceImplTest {

    @Mock
    private RateRepository rateRepository;

    @Mock
    private ActionRepository actionRepository;

    @InjectMocks
    private RateServiceImpl rateServiceImpl;

    private Action action;
    private Rate rate;
    private List<Rate> rateList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        rate = new Rate.Builder()
                .setRate_id(1)
                .setCurrency("dolar amerykański")
                .setCode("usd")
                .setBid(3.5)
                .setAsk(3)
                .build();

        action = new Action.Builder()
                .setAction_id(1)
                .setActionName(ActionName.GET_ALL_ACTIONS)
                .setRegistrationTime(LocalDateTime.now())
                .build();

        rateList.add(rate);
    }

    @Test
    void findAll() {
        when(rateRepository.findAll()).thenReturn(rateList);

        List<Rate> rates = rateServiceImpl.findAll();

        Assertions.assertSame(rates, rateList);
    }

    @Test
    void findByCode() {
        doReturn(Optional.of(rate)).when(rateRepository).findByCode(rate.getCode());

        Optional<Rate> findRateCode = rateServiceImpl.findByCode(rate.getCode());

        Assertions.assertTrue(findRateCode.isPresent());
        Assertions.assertSame(findRateCode.get(), rate);
    }

    @Test
    void save() {
    }
}
