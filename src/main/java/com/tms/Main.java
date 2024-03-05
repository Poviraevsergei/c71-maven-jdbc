package com.tms;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //1. создадим Spring Container (ApplicationContext) by Java
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Cap capBean = (Cap)context.getBean("getCapBean");
        System.out.println(capBean);
    }
}
