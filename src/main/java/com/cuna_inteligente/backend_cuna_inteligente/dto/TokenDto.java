package com.cuna_inteligente.backend_cuna_inteligente.dto;


public class TokenDto {
    private String authToken;
    private String refreshToken;
    private int type;
    private int idUser;

    public TokenDto() {
    }

    public TokenDto(String authToken, String refreshToken, int type, int idUser) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.type = type;
        this.idUser = idUser;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "authToken='" + authToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", type=" + type +
                ", idUser=" + idUser +
                '}';
    }
}