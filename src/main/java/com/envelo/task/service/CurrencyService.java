package com.envelo.task.service;

import com.envelo.task.exception.NullValueException;
import com.envelo.task.model.currencies.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> findAll();
    Optional<Currency> findByCode(String code);
    Currency save(Currency currenciesForConversion);
    double conversionToPLN(int amount, String codeCurrency) throws NullValueException;
    double conversionToCurrency(int amount, String codeCurrency, String targetCodeCurrency) throws NullValueException;
    void delete(Integer currency_id);
}
