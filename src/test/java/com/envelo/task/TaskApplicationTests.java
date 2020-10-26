package com.envelo.task;

import com.envelo.task.controller.ActionController;
import com.envelo.task.controller.CurrencyController;
import com.envelo.task.controller.RatesController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    private ActionController actionController;

    @Autowired
    private CurrencyController currencyController;

    @Autowired
    private RatesController ratesController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(actionController);
        Assertions.assertNotNull(currencyController);
        Assertions.assertNotNull(ratesController);
    }

}
