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
import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import java.sql.Timestamp;  // Importar Timestamp
import java.util.List;

/**
 *
 * @author jhessikazarateluque
 */
@Entity
@Table(name = "registrotemperatura")
@NamedQueries({
    @NamedQuery(name = "Registrotemperatura.findAll", query = "SELECT r FROM Registrotemperatura r"),
    @NamedQuery(name = "Registrotemperatura.findByIdRegistrotemp", query = "SELECT r FROM Registrotemperatura r WHERE r.idRegistrotemp = :idRegistrotemp"),
    @NamedQuery(name = "Registrotemperatura.findByTemperatura", query = "SELECT r FROM Registrotemperatura r WHERE r.temperatura = :temperatura"),
    @NamedQuery(name = "Registrotemperatura.findByFecha", query = "SELECT r FROM Registrotemperatura r WHERE r.fecha = :fecha")})
public class Registrotemperatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registrotemp")
    private Integer idRegistrotemp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "temperatura")
    private BigDecimal temperatura;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fecha;
    @JoinColumn(name = "id_bebe", referencedColumnName = "id_bebe")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bebe idBebe;

    public Registrotemperatura() {
    }

    public Registrotemperatura(Integer idRegistrotemp) {
        this.idRegistrotemp = idRegistrotemp;
    }

    public Registrotemperatura(Integer idRegistrotemp, BigDecimal temperatura, Timestamp fecha) {
        this.idRegistrotemp = idRegistrotemp;
        this.temperatura = temperatura;
        this.fecha = fecha;
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
    
    public Bebe getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(Bebe idBebe) {
        this.idBebe = idBebe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistrotemp != null ? idRegistrotemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrotemperatura)) {
            return false;
        }
        Registrotemperatura other = (Registrotemperatura) object;
        if ((this.idRegistrotemp == null && other.idRegistrotemp != null) || (this.idRegistrotemp != null && !this.idRegistrotemp.equals(other.idRegistrotemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cuna_inteligente.backend_cuna_inteligente.entity.Registrotemperatura[ idRegistrotemp=" + idRegistrotemp + " ]";
    }
    
}
