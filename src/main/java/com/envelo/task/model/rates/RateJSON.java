package com.envelo.task.model.rates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateJSON {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("code")
    private String code;
    @JsonProperty("bid")
    private double bid;
    @JsonProperty("ask")
    private double ask;
    @JsonProperty("tradingDate")
    private String tradingDate;
    @JsonProperty("effectiveDate")
    private String effectiveDate;

    static int i = 0;

    @JsonProperty("rates")
    public void setRates(List<Map<String, Object>> ratesEntries) {

        Map<String, Object> rates = ratesEntries.get(i); i++;
        setCurrency((String) rates.get("currency"));
        setCode((String) rates.get("code"));
        setBid((Double) rates.get("bid"));
        setAsk((Double) rates.get("ask"));

        if (i == 13) {
            i = 0;
        }
    }
}
