package com.envelo.task.model.currencies;

import com.envelo.task.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "currencies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Currency extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Integer currency_id;

    public Currency(Builder builder) {
        this.currency_id = builder.currency_id;
        this.currency = builder.currency;
        this.code = builder.code;
    }

    public static class Builder {

        private Integer currency_id;
        private String currency;
        private String code;

        public Builder setCurrency_id(Integer currency_id) {
            this.currency_id = currency_id;
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

        public Currency build() {
            return new Currency(this);
        }
    }
}
