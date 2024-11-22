package com.cuna_inteligente.backend_cuna_inteligente.dto;
import java.math.BigDecimal;
import java.sql.Timestamp;

public  class RegistroalimentacionDto {
    private int idRegistroalimentacion;
    private Timestamp fecha;
    private boolean tipocomida;
    private BebeDto idBebe;
    public RegistroalimentacionDto() {
    }

    public RegistroalimentacionDto(int idRegistroalimentacion, Timestamp fecha, boolean tipocomida, BebeDto idBebe) {
        this.idRegistroalimentacion = idRegistroalimentacion;
        this.fecha = fecha;
        this.tipocomida = tipocomida;
        this.idBebe = idBebe;
    }

    public int getIdRegistroalimentacion() {
        return idRegistroalimentacion;
    }

    public void setIdRegistroalimentacion(int idRegistroalimentacion) {
        this.idRegistroalimentacion = idRegistroalimentacion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public boolean getTipocomida() {
        return tipocomida;
    }

    public void setTipocomida(boolean tipocomida) {
        this.tipocomida = tipocomida;
    }

    public BebeDto getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(BebeDto idBebe) {
        this.idBebe = idBebe;
    }

}