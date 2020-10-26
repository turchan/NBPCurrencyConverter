package com.envelo.task.service.serviceImpl;

import com.envelo.task.exception.ExistsValueException;
import com.envelo.task.exception.NullValueException;
import com.envelo.task.model.actioans.Action;
import com.envelo.task.model.actioans.ActionName;
import com.envelo.task.model.currencies.Currency;
import com.envelo.task.model.rates.Rate;
import com.envelo.task.repository.ActionRepository;
import com.envelo.task.repository.CurrencyRepository;
import com.envelo.task.repository.RateRepository;
import com.envelo.task.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RateRepository rateRepository;
    private final ActionRepository actionRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository,
                               RateRepository rateRepository,
                               ActionRepository actionRepository) {
        this.currencyRepository = currencyRepository;
        this.rateRepository = rateRepository;
        this.actionRepository = actionRepository;
    }

	/**
	* Method for fetching the actions list from database
	* @return This return the list of Currencies from database
	*/
    @Override
    public List<Currency> findAll() {

        Action action = new Action.Builder()
                .setAction_id(null)
                .setActionName(ActionName.GET_ALL_CURRENCIES_FROM_ALLOW__LIST)
                .setRegistrationTime(LocalDateTime.now())
                .build();

        actionRepository.save(action);

        log.info("Currencies were fetched from the database");

        return currencyRepository.findAll();
    }

	/**
	* Method for fetching the actioans list from database
	* @param code the value of code in Rate
	* @return Optional<Currency> This return the object of Currency which containt code value
	*/
    @Override
    public Optional<Currency> findByCode(String code) {
        return currencyRepository.findByCode(code);
    }

	/**
	* Method for saving the Currency to database
	* @param currenciesForConversion is for receiving the currency object for saving
	* @return Currency This return the object that was added to the database
	*/
    @Override
    public Currency save(Currency currenciesForConversion) throws ExistsValueException {

        Optional<Currency> currency = currencyRepository.findByCode(currenciesForConversion.getCode());

        if (currency.isEmpty()) {
            Currency newCurrency = new Currency.Builder()
                    .setCurrency_id(null)
                    .setCurrency(currenciesForConversion.getCurrency())
                    .setCode(currenciesForConversion.getCode())
                    .build();

            Action action = new Action.Builder()
                    .setAction_id(null)
                    .setActionName(ActionName.SAVE_CURRENCY_TO_ALLOW_LIST)
                    .setRegistrationTime(LocalDateTime.now())
                    .build();

            actionRepository.save(action);

            log.info("Currency was save to the database");

            return currencyRepository.save(newCurrency);
        } else {
            throw new ExistsValueException("", currenciesForConversion.getCode());
        }
    }

	/**
	* Method for conversion the amount of currency to PLN
	* @param amount the amount of currency 
	* @param codeCurrency the code of currency for receiving the Bid price 
	* @return double This return the result of the conversion
	*/
    @Override
    public double conversionToPLN(int amount, String codeCurrency) throws NullValueException {

        Optional<Rate> rate = rateRepository.findByCode(codeCurrency);
        Optional<Currency> currency = currencyRepository.findByCode(codeCurrency);

        if (currency.isPresent()) {

            Action action = new Action.Builder()
                    .setAction_id(null)
                    .setActionName(ActionName.CONVERSION_TO_PLN)
                    .setRegistrationTime(LocalDateTime.now())
                    .build();

            actionRepository.save(action);

            log.info("Conversion To PLN");

            return rate.get().getBid() * amount;
        } else {
            throw new NullValueException("", codeCurrency);
        }
    }

	/**
	* Method for conversion the amount of currency to another
	* @param amount the amount of currency 
	* @param codeCurrency the code of currency for receiving the Bid price 
	* @param targetCodeCurrency the code of target currency for receiving the Ask price 
	* @return double This return the resul of the conversion
	*/
    @Override
    public double conversionToCurrency(int amount, String codeCurrency, String targetCodeCurrency) throws NullValueException {

        Optional<Rate> rateTarget = rateRepository.findByCode(targetCodeCurrency);
        Optional<Currency> currencyTarget = currencyRepository.findByCode(targetCodeCurrency);

        if (currencyTarget.isPresent()) {

            Action action = new Action.Builder()
                    .setAction_id(null)
                    .setActionName(ActionName.CONVERSION_TO_CURRENCY)
                    .setRegistrationTime(LocalDateTime.now())
                    .build();

            actionRepository.save(action);

            log.info("Conversion from one currency to another through PLN");

            double rateFromCodeCurrencyToPLN = conversionToPLN(amount, codeCurrency);

            return rateFromCodeCurrencyToPLN / rateTarget.get().getAsk();

        } else {
            throw new NullValueException("", codeCurrency, targetCodeCurrency);
        }
    }


	/**
	* Method for deleting currency from allow list of currencies for conversion
	* @param currency_id the id of currency 
	* @return Nothing
	*/
    @Override
    public void delete(Integer currency_id) {

        Action action = new Action.Builder()
                .setAction_id(null)
                .setActionName(ActionName.DELETE_CURRENCY_FROM_ALLOW_LIST)
                .setRegistrationTime(LocalDateTime.now())
                .build();

        actionRepository.save(action);

        log.info("Delete the currency from allow list for conversion");

        currencyRepository.deleteById(currency_id);
    }
}
