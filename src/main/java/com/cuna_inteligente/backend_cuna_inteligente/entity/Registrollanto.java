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
import java.sql.Timestamp;
import java.util.Date;

import java.io.Serializable;
import java.sql.Timestamp;  // Importar Timestamp
import java.util.List;

/**
 *
 * @author jhessikazarateluque
 */
@Entity
@Table(name = "registrollanto")
@NamedQueries({
    @NamedQuery(name = "Registrollanto.findAll", query = "SELECT r FROM Registrollanto r"),
    @NamedQuery(name = "Registrollanto.findByIdRegistrollanto", query = "SELECT r FROM Registrollanto r WHERE r.idRegistrollanto = :idRegistrollanto"),
    @NamedQuery(name = "Registrollanto.findByLlanto", query = "SELECT r FROM Registrollanto r WHERE r.llanto = :llanto"),
    @NamedQuery(name = "Registrollanto.findByRazon", query = "SELECT r FROM Registrollanto r WHERE r.razon = :razon"),
    @NamedQuery(name = "Registrollanto.findByFecha", query = "SELECT r FROM Registrollanto r WHERE r.fecha = :fecha")})
public class Registrollanto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registrollanto")
    private Integer idRegistrollanto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "llanto")
    private BigDecimal llanto;
    @Basic(optional = false)
    @Column(name = "razon")
    private BigDecimal razon;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fecha;
    @JoinColumn(name = "id_bebe", referencedColumnName = "id_bebe")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bebe idBebe;

    public Registrollanto() {
    }

    public Registrollanto(Integer idRegistrollanto) {
        this.idRegistrollanto = idRegistrollanto;
    }

    public Registrollanto(Integer idRegistrollanto, BigDecimal llanto, BigDecimal razon, Timestamp fecha) {
        this.idRegistrollanto = idRegistrollanto;
        this.llanto = llanto;
        this.razon = razon;
        this.fecha = fecha;
    }

    public Integer getIdRegistrollanto() {
        return idRegistrollanto;
    }

    public void setIdRegistrollanto(Integer idRegistrollanto) {
        this.idRegistrollanto = idRegistrollanto;
    }

    public BigDecimal getLlanto() {
        return llanto;
    }

    public void setLlanto(BigDecimal llanto) {
        this.llanto = llanto;
    }

    public BigDecimal getRazon() {
        return razon;
    }

    public void setRazon(BigDecimal razon) {
        this.razon = razon;
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
        hash += (idRegistrollanto != null ? idRegistrollanto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrollanto)) {
            return false;
        }
        Registrollanto other = (Registrollanto) object;
        if ((this.idRegistrollanto == null && other.idRegistrollanto != null) || (this.idRegistrollanto != null && !this.idRegistrollanto.equals(other.idRegistrollanto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cuna_inteligente.backend_cuna_inteligente.entity.Registrollanto[ idRegistrollanto=" + idRegistrollanto + " ]";
    }
    
}
