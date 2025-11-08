package com.projectbasedlearning.Assignment.components;

import com.projectbasedlearning.Assignment.annotation.InjectConfigValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ByeMsg {
    @InjectConfigValue(envKey = "BYE_KEY")
    private String message;
}
