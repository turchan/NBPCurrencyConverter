package com.envelo.task.service.serviceImpl;

import com.envelo.task.model.actioans.Action;
import com.envelo.task.model.actioans.ActionName;
import com.envelo.task.repository.ActionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class ActionServiceImplTest {

    @Mock
    private ActionRepository actionRepository;

    @InjectMocks
    private ActionServiceImpl actionServiceImpl;

    private Action action;
    private List<Action> actionList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        action = new Action.Builder()
                .setAction_id(1)
                .setActionName(ActionName.GET_ALL_ACTIONS)
                .setRegistrationTime(LocalDateTime.now())
                .build();

        actionList.add(action);
    }

    @Test
    void findAll() {

        when(actionRepository.findAll()).thenReturn(actionList);

        List<Action> actions = actionServiceImpl.findAll();

        Assertions.assertSame(actions, actionList);
    }

    @Test
    void save() {

        when(actionRepository.save(action)).thenReturn(action);

        Action actionTest = actionServiceImpl.save(action);

        Assertions.assertSame(actionTest, action);
    }
}
