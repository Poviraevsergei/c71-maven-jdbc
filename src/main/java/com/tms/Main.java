package com.tms;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.tms") //что спринг будет вычитывать все классы через рефлексию начиная с того пакета где лежит этот класс(Main)
public class Main {
    public static void main(String[] args) {
        //1. создадим Spring Container (ApplicationContext) by Annotation
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Cap capBean = (Cap) context.getBean("cap");
        System.out.println(capBean);
    }
}
