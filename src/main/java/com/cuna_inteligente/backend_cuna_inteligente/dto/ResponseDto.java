package com.cuna_inteligente.backend_cuna_inteligente.dto;


public class ResponseDto<T> {
    private String code;
    private T response;
    private String errorMessage;

    public ResponseDto() {
    }

    public ResponseDto(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResponse() {
        return this.response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "{" +
                " code='" + getCode() + "'" +
                ", response='" + getResponse() + "'" +
                ", errorMessage='" + getErrorMessage() + "'" +
                "}";
    }
}