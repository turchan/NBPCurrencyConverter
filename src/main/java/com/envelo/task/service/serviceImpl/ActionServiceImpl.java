package com.envelo.task.service.serviceImpl;

import com.envelo.task.model.actioans.Action;
import com.envelo.task.model.actioans.ActionName;
import com.envelo.task.repository.ActionRepository;
import com.envelo.task.service.ActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    public ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

	/**
	* Method for fetching the actioans list from database
	*/
    @Override
    public List<Action> findAll() {

        Action action = new Action.Builder()
                .setAction_id(null)
                .setActionName(ActionName.GET_ALL_ACTIONS)
                .setRegistrationTime(LocalDateTime.now())
                .build();

        actionRepository.save(action);

        log.info("Actions were fetched from the database");

        return actionRepository.findAll();
    }

    @Override
    public Action save(Action action) {
        actionRepository.save(action);

        log.info("Action was saves to the database");

        return action;
    }
}
