package com.distribution.uidgen.controller;

import com.distribution.uidgen.uid.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UidController {
    @Autowired
    @Qualifier("snowFlakeGenerator")
    UidGenerator uidGenerator;

    @GetMapping("/id")
    public Object getId(){
        return uidGenerator.gen();
    }
}
