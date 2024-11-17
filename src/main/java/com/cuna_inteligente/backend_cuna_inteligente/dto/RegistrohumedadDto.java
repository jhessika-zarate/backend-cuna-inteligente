package com.cuna_inteligente.backend_cuna_inteligente.dto;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class RegistrohumedadDto {
    private int idRegistrohumedad;
    private BigDecimal humedad;
    private Timestamp fecha;
    private BebeDto idBebe;

    public RegistrohumedadDto() {
    }

    public RegistrohumedadDto(int idRegistrohumedad, BigDecimal humedad, Timestamp fecha, BebeDto idBebe) {
        this.idRegistrohumedad = idRegistrohumedad;
        this.humedad = humedad;
        this.fecha = fecha;
        this.idBebe = idBebe;
    }

    public int getIdRegistrohumedad() {
        return idRegistrohumedad;
    }

    public void setIdRegistrohumedad(int idRegistrohumedad) {
        this.idRegistrohumedad = idRegistrohumedad;
    }

    public BigDecimal getHumedad() {
        return humedad;
    }

    public void setHumedad(BigDecimal humedad) {
        this.humedad = humedad;
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
