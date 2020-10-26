package com.envelo.task.service;

import com.envelo.task.model.actioans.Action;

import java.util.List;

public interface ActionService {
    List<Action> findAll();
    Action save(Action action);
}
