package com.tms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //делает бин при старте по умолчанию
public class Cap {
    public String text;

    @Autowired
    public Inner inner;

    //1. Это через поле(Autowire над полем inner)
}
