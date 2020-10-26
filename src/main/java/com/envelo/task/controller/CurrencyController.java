package com.envelo.task.controller;

import com.envelo.task.model.currencies.Currency;
import com.envelo.task.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Currency> getAllCurrencies() {
        return currencyService.findAll();
    }

    @GetMapping("/{amount}/{codeCurrency}")
    @ResponseStatus(HttpStatus.OK)
    public double conversationToPLN(@PathVariable int amount,
                                    @PathVariable String codeCurrency) {

        return currencyService.conversionToPLN(amount, codeCurrency);
    }

    @GetMapping("/{amount}/{codeCurrency}/{targetCodeCurrency}")
    @ResponseStatus(HttpStatus.OK)
    public double conversationToCurrency(@PathVariable int amount,
                               @PathVariable String codeCurrency,
                               @PathVariable String targetCodeCurrency){

        return currencyService.conversionToCurrency(amount, codeCurrency, targetCodeCurrency);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Currency saveCurrencyToAllowList(@RequestBody Currency currency) {

        return currencyService.save(currency);
    }

    @DeleteMapping("/{currency_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrencyFromAllowList(@PathVariable Integer currency_id) {
        currencyService.delete(currency_id);
    }
}
