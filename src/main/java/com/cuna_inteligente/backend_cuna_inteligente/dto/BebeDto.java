package com.cuna_inteligente.backend_cuna_inteligente.dto;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class BebeDto {
    private int idBebe;
    private String nombre;
    private String apellidopaterno;
    private String apellidomaterno;
    private boolean seleccionado;
    private Timestamp fechadenacimiento; // Cambiado a Timestamp
    private String color;
    private boolean movimiento;
    private UsuarioDto idUsuario;
    
    public BebeDto() {
    }

    public BebeDto(int idBebe, String nombre, String apellidopaterno, String apellidomaterno, boolean seleccionado, Timestamp fechadenacimiento, String color, UsuarioDto idUsuario, boolean movimiento) {
        this.idBebe = idBebe;
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.seleccionado = seleccionado;
        this.fechadenacimiento = fechadenacimiento;
        this.color = color;
        this.idUsuario = idUsuario;
        this.movimiento = movimiento;
    }


    public BebeDto (int idBebe, String nombre, String color, boolean seleccionado, boolean movimiento)
    {
        this.idBebe = idBebe;
        this.nombre = nombre;
        this.color = color;
        this.seleccionado = seleccionado;
        this.movimiento = movimiento;
    }

    public BebeDto (int idBebe, boolean seleccionado, UsuarioDto idUsuario)
    {
        this.idBebe = idBebe;
        this.seleccionado = seleccionado;
        this.idUsuario = idUsuario;
    }

    public int getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(int idBebe) {
        this.idBebe = idBebe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Timestamp getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(Timestamp fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }

    public UsuarioDto getIdUsuario() {
        return idUsuario;
    }


    public void setIdUsuario(UsuarioDto idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
