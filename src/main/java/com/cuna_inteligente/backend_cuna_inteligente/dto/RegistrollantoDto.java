package com.cuna_inteligente.backend_cuna_inteligente.dto;

import java.sql.Timestamp;


public class RegistrollantoDto {
    private int idRegistrollanto;
    private Timestamp fecha;
    private String razon;
    private BebeDto idBebe;
   
    public RegistrollantoDto() {
    }

    public RegistrollantoDto(int idRegistrollanto, Timestamp fecha, String razon, BebeDto idBebe) {
        this.idRegistrollanto = idRegistrollanto;
        this.fecha = fecha;
        this.razon = razon;
        this.idBebe = idBebe;
    }

    public int getIdRegistrollanto() {
        return idRegistrollanto;
    }

    public void setIdRegistrollanto(int idRegistrollanto) {
        this.idRegistrollanto = idRegistrollanto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public BebeDto getIdBebe() {
        return idBebe;
    }


    public void setIdBebe(BebeDto idBebe) {
        this.idBebe = idBebe;
    }

}