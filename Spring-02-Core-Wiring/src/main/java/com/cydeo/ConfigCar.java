package com.cydeo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCar {
    @Bean
    Car car(){
        Car c = new Car();
        c.setMake("Subaru");
        return c;
    }
//Direct Wiring
//    @Bean
//    Person person(){
//        Person p = new Person();
//        p.setName("Johnny");
//        p.setCar(car());//direct wiring
//        return p;
//    }

    //Autowiring
    @Bean
    Person person(Car car){
        Person p = new Person();
        p.setName("Johnny");
        p.setCar(car);//auto wiring
        return p;
    }
}
