package com.cuna_inteligente.backend_cuna_inteligente.dto;
import java.sql.Timestamp;

public class RegistrovacunaDto {
    private int idVacuna;
    private String nombreVacuna;
    private Timestamp fechaVacuna;
    private String dosis;
    private String centroSalud;
    private BebeDto idBebe;
    public  RegistrovacunaDto() {
    }

    public RegistrovacunaDto(int idVacuna, String nombreVacuna, Timestamp fechaVacuna, String dosis, String centroSalud, BebeDto idBebe) {
        this.idVacuna = idVacuna;
        this.nombreVacuna = nombreVacuna;
        this.fechaVacuna = fechaVacuna;
        this.dosis = dosis;
        this.centroSalud = centroSalud;
        this.idBebe = idBebe;
    }

    public int getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(int idVacuna) {
        this.idVacuna = idVacuna;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public Timestamp getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(Timestamp fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getCentroSalud() {
        return centroSalud;
    }

    public void setCentroSalud(String centroSalud) {
        this.centroSalud = centroSalud;
    }

    public BebeDto getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(BebeDto idBebe) {
        this.idBebe = idBebe;
    }
   

   
    
}
