package com.envelo.task.service.serviceImpl;

import com.envelo.task.model.actioans.Action;
import com.envelo.task.model.actioans.ActionName;
import com.envelo.task.model.currencies.Currency;
import com.envelo.task.model.rates.Rate;
import com.envelo.task.repository.ActionRepository;
import com.envelo.task.repository.CurrencyRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private ActionRepository actionRepository;

    @InjectMocks
    private CurrencyServiceImpl currencyServiceImpl;

    private Action action;
    private Currency currency;
    private Rate rate;
    private List<Currency> currencyList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        currency = new Currency.Builder()
                .setCurrency_id(1)
                .setCurrency("dolar amerykański")
                .setCode("usd")
                .build();

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

        currencyList.add(currency);
    }


    @Test
    void findAll() {
        when(currencyRepository.findAll()).thenReturn(currencyList);

        List<Currency> currencies = currencyServiceImpl.findAll();

        Assertions.assertSame(currencies, currencyList);
    }

    @Test
    void findByCode() {
        doReturn(Optional.of(currency)).when(currencyRepository).findByCode(currency.getCode());

        Optional<Currency> findCurrencyCode = currencyServiceImpl.findByCode(currency.getCode());

        Assertions.assertTrue(findCurrencyCode.isPresent());
        Assertions.assertSame(findCurrencyCode.get(), currency);    }

    @Test
    void save() {
        when(currencyRepository.save(currency)).thenReturn(currency);

        Currency currencyTest = currencyServiceImpl.save(currency);

        Assertions.assertSame(currency, currency);
    }

    @Test
    void conversionToPLN() {
        doReturn(Optional.of(currency)).when(currencyRepository).findByCode(currency.getCode());

        Optional<Currency> findCurrencyCode = currencyServiceImpl.findByCode(currency.getCode());

        Assertions.assertTrue(findCurrencyCode.isPresent());
        Assertions.assertSame(findCurrencyCode.get(), currency);

        Assertions.assertEquals(3.5 * 100, rate.getBid() * 100);
    }

    @Test
    void conversionToCurrency() {

        doReturn(Optional.of(currency)).when(currencyRepository).findByCode(currency.getCode());

        Optional<Currency> findCurrencyCode = currencyServiceImpl.findByCode(currency.getCode());

        Assertions.assertTrue(findCurrencyCode.isPresent());
        Assertions.assertSame(findCurrencyCode.get(), currency);

        Assertions.assertEquals( 3.5 * 100, rate.getBid() * 100);
        Assertions.assertEquals(3.5 * 100 / 3, rate.getBid() * 100 / rate.getAsk());

    }
}
