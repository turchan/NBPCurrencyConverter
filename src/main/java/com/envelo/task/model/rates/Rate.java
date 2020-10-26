package com.envelo.task.model.rates;

import com.envelo.task.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "rates")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Rate extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Integer rate_id;

    @Column(name = "bid")
    @NotNull(message = "The ask is null")
    private double bid;

    @Column(name = "ask")
    @NotNull(message = "The ask is null")
    private double ask;

    @Column(name = "trading_date")
    @NotBlank(message = "The trading_date is empty")
    private String trading_date;

    @Column(name = "effective_date")
    @NotBlank(message = "The effective_date is empty")
    private String effective_date;

    public Rate(Builder builder) {
        this.rate_id = builder.rate_id;
        this.currency = builder.currency;
        this.code = builder.code;
        this.bid = builder.bid;
        this.ask = builder.ask;
        this.trading_date = builder.trading_date;
        this.effective_date = builder.effective_date;
    }

    public static class Builder {

        private Integer rate_id;
        private String currency;
        private String code;
        private double bid;
        private double ask;
        private String trading_date;
        private String effective_date;

        public Builder setRate_id(Integer rate_id) {
            this.rate_id = rate_id;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setBid(double bid) {
            this.bid = bid;
            return this;
        }

        public Builder setAsk(double ask) {
            this.ask = ask;
            return this;
        }

        public Builder setTrading_date(String trading_date) {
            this.trading_date = trading_date;
            return this;
        }

        public Builder setEffective_date(String effective_date) {
            this.effective_date = effective_date;
            return this;
        }

        public Rate build() {
            return new Rate(this);
        }
    }
}
