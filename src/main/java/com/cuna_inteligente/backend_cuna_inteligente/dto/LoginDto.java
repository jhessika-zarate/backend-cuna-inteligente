package com.cuna_inteligente.backend_cuna_inteligente.dto;

public class LoginDto {
    private String gmail;
    private String contrasenia;

    public LoginDto() {
    }

    public LoginDto(String gmail, String contrasenia) {
        this.gmail = gmail;
        this.contrasenia = contrasenia;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override

    public String toString() {
        return "LoginDto{" +
                "gmail='" + gmail + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
