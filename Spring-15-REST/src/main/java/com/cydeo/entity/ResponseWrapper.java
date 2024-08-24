package com.cydeo.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ResponseWrapper {

    private boolean success; //fields for api response
    private String message; //custom message
    private Integer code; //any status code
    private Object data; //data for api response

    public ResponseWrapper(String message, Object data) { //used for GET api calls
        this.success = true;
        this.message = message;
        this.code = HttpStatus.OK.value();
        this.data = data;
    }

    public ResponseWrapper(String message) {//many argument constructor
        this.message = message;
        this.code = HttpStatus.OK.value();
        this.success = true;
    }
}
