package com.envelo.task.model.actioans;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "actions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Integer action_id;

    @Column(name = "actionName")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "The actionName can not be null")
    private ActionName actionName;

    @Column(name = "registrationTime")
    @NotNull(message = "The registrationTime can not be empty")
    private LocalDateTime registrationTime;

    public Action(Builder builder) {
        this.action_id = builder.action_id;
        this.actionName = builder.actionName;
        this.registrationTime = builder.registrationTime;
    }

    public static class Builder {
        private Integer action_id;
        private ActionName actionName;
        private LocalDateTime registrationTime;

        public Builder setAction_id(Integer action_id) {
            this.action_id = action_id;
            return this;
        }

        public Builder setActionName(ActionName actionName) {
            this.actionName = actionName;
            return this;
        }

        public Builder setRegistrationTime(LocalDateTime registrationTime) {
            this.registrationTime = registrationTime;
            return this;
        }

        public Action build() {
            return new Action(this);
        }
    }
}
