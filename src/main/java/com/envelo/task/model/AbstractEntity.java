package com.envelo.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Column(name = "currency")
    @NotBlank(message = "The currency is empty")
    protected String currency;

    @Column(name = "code", length = 3)
    @NotBlank(message = "The code is empty")
    @Size(min = 3, max = 3, message = "The code length suppose to be 3")
    protected String code;
}
