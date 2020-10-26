package com.envelo.task.repository;

import com.envelo.task.model.actioans.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Integer> {
}
