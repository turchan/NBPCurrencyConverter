package com.envelo.task.controller;

import com.envelo.task.model.actioans.Action;
import com.envelo.task.service.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actions")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Action> getAllActions() {
        return actionService.findAll();
    }

}
