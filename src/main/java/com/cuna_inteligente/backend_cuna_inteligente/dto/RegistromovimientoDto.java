package com.cuna_inteligente.backend_cuna_inteligente.dto;
import java.sql.Timestamp;


public class RegistromovimientoDto {

    private Integer idMovimiento;
    private Timestamp fecha;
    private BebeDto idBebe;

    public RegistromovimientoDto() {
    }

    public RegistromovimientoDto(Integer idMovimiento, Timestamp fecha, BebeDto idBebe) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.idBebe = idBebe;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
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
