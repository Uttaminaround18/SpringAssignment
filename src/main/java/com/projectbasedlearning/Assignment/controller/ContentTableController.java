package com.projectbasedlearning.Assignment.controller;

import com.projectbasedlearning.Assignment.components.ByeMsg;
import com.projectbasedlearning.Assignment.components.WelcomeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ContentTableController {

    @Autowired
    private WelcomeMsg welcomeMsg;

    @Autowired
    private ByeMsg byeMsg;

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMsg() {
        if(!welcomeMsg.getMessage().isEmpty())
            return new ResponseEntity<>(welcomeMsg.getMessage(), HttpStatus.OK);
        return new ResponseEntity<>("NO VALUE FROM THE DATABASE", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/bye")
    public ResponseEntity<String> byeMsg() {
        if(!byeMsg.getMessage().isEmpty())
            return new ResponseEntity<>(byeMsg.getMessage(), HttpStatus.OK);
        String msg = "NO VALUE FOUND FROM DATABASE";
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

    }
}
