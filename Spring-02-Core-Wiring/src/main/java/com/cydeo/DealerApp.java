package com.cydeo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DealerApp {
    public static void main(String[] args) {
        ApplicationContext  context = new AnnotationConfigApplicationContext(ConfigCar.class);
        Car c = context.getBean(Car.class);
        Person p = context.getBean(Person.class);

        System.out.println("P name: " + p.getName());
        System.out.println("Car make: "+c.getMake());
        System.out.println("Person's car : " + p.getCar().getMake());
    }
}
