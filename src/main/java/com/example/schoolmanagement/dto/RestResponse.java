package com.example.schoolmanagement.dto;

import com.example.schoolmanagement.enumconstants.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse {

    private String message;
    private String status;
    private Object detail;
    private ResponseStatus responseStatus;

    public RestResponse(String message, String status, Object detail, ResponseStatus responseStatus) {
        this.message = message;
        this.status = status;
        this.detail = detail;
        this.responseStatus = responseStatus;
    }

    public RestResponse() {
    }

    public String getMessage() {
        return message;
    }

    public RestResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RestResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getDetail() {
        return detail;
    }

    public RestResponse setDetail(Object detail) {
        this.detail = detail;
        return this;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public RestResponse setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }
}
