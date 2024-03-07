package com.tms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Cap {
    public String text;

    public InterfaceInner inner;

    @Autowired
    public Cap(@Qualifier("innerSecond") InterfaceInner inner) {
        this.inner = inner;
    }

    @Override
    public String toString() {
        return "Cap{" +
                "text='" + text + '\'' +
                ", inner=" + inner +
                '}';
    }

    //1. Это через поле(Autowire над полем inner)
    //2. Через конструктор (лучшая опция)
    //3. Через метод(сеттер)
}
