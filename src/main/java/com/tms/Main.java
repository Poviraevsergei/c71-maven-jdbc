package com.tms;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
// XML, Annotation, Java


        //1. создадим Spring Container (ApplicationContext)
        ApplicationContext context = new ClassPathXmlApplicationContext("my-settings.xml"); //Это когда настройки через XML
        Cap capBean = (Cap) context.getBean("cap");
        Cap capBeanSecond = (Cap) context.getBean("cap");

        capBean.text = "NEW_ONE";

        // cap это один и тот же бин!
        System.out.println(capBean.text);
        System.out.println(capBeanSecond.text);
        System.out.println(capBean.hashCode());
        System.out.println(capBeanSecond.hashCode());



    }
}
