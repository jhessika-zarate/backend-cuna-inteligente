package com.cuna_inteligente.backend_cuna_inteligente.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RegistrotemperaturaDto {
      private Integer idRegistrotemp;
    private BigDecimal temperatura;
    private Timestamp fecha;
     private BebeDto idBebe;

    public RegistrotemperaturaDto() {
    }

    public RegistrotemperaturaDto(Integer idRegistrotemp, BigDecimal temperatura, Timestamp fecha, BebeDto idBebe) {
        this.idRegistrotemp = idRegistrotemp;
        this.temperatura = temperatura;
        this.fecha = fecha;
        this.idBebe = idBebe;
    }


    public Integer getIdRegistrotemp() {
        return idRegistrotemp;
    }

    public void setIdRegistrotemp(Integer idRegistrotemp) {
        this.idRegistrotemp = idRegistrotemp;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public BebeDto getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(BebeDto idBebe) {
        this.idBebe = idBebe;
    }

    
}
