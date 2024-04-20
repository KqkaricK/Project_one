package com.learning.dto;

import java.util.ArrayList;
import java.util.List;

public class JsonMessageDto {

    private List<String> errors  = new ArrayList<>();
    private String message;

    private String id;

    private List<String> errorCode  = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrorCode() {return errorCode;}

    public void setErrorCode(List<String> errorCode) {this.errorCode = errorCode;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}
}