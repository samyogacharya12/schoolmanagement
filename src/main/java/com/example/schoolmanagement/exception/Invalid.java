package com.example.schoolmanagement.exception;

public class Invalid extends RuntimeException {

    private String message;

    private Object detail;

    public Invalid(String message, Object detail) {
        this.message = message;
        this.detail=detail;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
