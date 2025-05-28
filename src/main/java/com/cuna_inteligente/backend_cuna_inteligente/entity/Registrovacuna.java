/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cuna_inteligente.backend_cuna_inteligente.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author jhessikazarateluque
 */
@Entity
@Table(name = "registrovacuna")
@NamedQueries({
    @NamedQuery(name = "Registrovacuna.findAll", query = "SELECT r FROM Registrovacuna r"),
    @NamedQuery(name = "Registrovacuna.findByIdVacuna", query = "SELECT r FROM Registrovacuna r WHERE r.idVacuna = :idVacuna"),
    @NamedQuery(name = "Registrovacuna.findByNombreVacuna", query = "SELECT r FROM Registrovacuna r WHERE r.nombreVacuna = :nombreVacuna"),
    @NamedQuery(name = "Registrovacuna.findByFechaVacuna", query = "SELECT r FROM Registrovacuna r WHERE r.fechaVacuna = :fechaVacuna"),
    @NamedQuery(name = "Registrovacuna.findByDosis", query = "SELECT r FROM Registrovacuna r WHERE r.dosis = :dosis"),
    @NamedQuery(name = "Registrovacuna.findByCentroSalud", query = "SELECT r FROM Registrovacuna r WHERE r.centroSalud = :centroSalud")})
public class Registrovacuna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vacuna")
    private Integer idVacuna;
    @Basic(optional = false)
    @Column(name = "nombreVacuna")
    private String nombreVacuna;
    @Basic(optional = false)
    @Column(name = "fechaVacuna")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaVacuna;
    @Basic(optional = false)
    @Column(name = "dosis")
    private String dosis;
    @Basic(optional = false)
    @Column(name = "centroSalud")
    private String centroSalud;
    @JoinColumn(name = "id_bebe", referencedColumnName = "id_bebe")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bebe idBebe;

    public Registrovacuna() {
    }

    public Registrovacuna(Integer idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Registrovacuna(Integer idVacuna, String nombreVacuna, Timestamp fechaVacuna, String dosis, String centroSalud) {
        this.idVacuna = idVacuna;
        this.nombreVacuna = nombreVacuna;
        this.fechaVacuna = fechaVacuna;
        this.dosis = dosis;
        this.centroSalud = centroSalud;
    }

    public Integer getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Integer idVacuna) {
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

    public Bebe getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(Bebe idBebe) {
        this.idBebe = idBebe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacuna != null ? idVacuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrovacuna)) {
            return false;
        }
        Registrovacuna other = (Registrovacuna) object;
        if ((this.idVacuna == null && other.idVacuna != null) || (this.idVacuna != null && !this.idVacuna.equals(other.idVacuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cuna_inteligente.backend_cuna_inteligente.entity.Registrovacuna[ idVacuna=" + idVacuna + " ]";
    }

    public void setFechaVacuna(java.security.Timestamp fechaVacuna2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFechaVacuna'");
    }
    
}
