package com.cuna_inteligente.backend_cuna_inteligente.dto;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class DatosmesbebeDto {
    private Integer idRegistrocaracteristicas;
    private BigDecimal peso;
    private BigDecimal altura;
    private Timestamp fecha;
    private BebeDto idBebe;

    public DatosmesbebeDto() {
    }

    public DatosmesbebeDto(Integer idRegistrocaracteristicas, BigDecimal peso, BigDecimal altura, Timestamp fecha, BebeDto idBebe) {
        this.idRegistrocaracteristicas = idRegistrocaracteristicas;
        this.peso = peso;
        this.altura = altura;
        this.fecha = fecha;
        this.idBebe = idBebe;
    }

    public Integer getIdRegistrocaracteristicas() {
        return idRegistrocaracteristicas;
    }

    public void setIdRegistrocaracteristicas(Integer idRegistrocaracteristicas) {
        this.idRegistrocaracteristicas = idRegistrocaracteristicas;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
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
