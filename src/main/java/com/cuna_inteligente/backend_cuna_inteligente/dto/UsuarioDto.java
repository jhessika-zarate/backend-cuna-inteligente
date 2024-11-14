package com.cuna_inteligente.backend_cuna_inteligente.dto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
public class UsuarioDto {
    private Integer idUsuario;
    private String username;
    private String gmail;
    private String contrasenia;
    public UsuarioDto() {
    }
    
    public UsuarioDto(int idUsuario, String username, String gmail, String contrasenia) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.gmail = gmail;
        this.contrasenia = contrasenia;
    }

    public UsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.username = usuario.getUsername();
        this.gmail = usuario.getGmail();
        this.contrasenia = usuario.getContrasenia();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    
}
