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
@Table(name = "registrohumedad")
@NamedQueries({
    @NamedQuery(name = "Registrohumedad.findAll", query = "SELECT r FROM Registrohumedad r"),
    @NamedQuery(name = "Registrohumedad.findByIdRegistrohumedad", query = "SELECT r FROM Registrohumedad r WHERE r.idRegistrohumedad = :idRegistrohumedad"),
    @NamedQuery(name = "Registrohumedad.findByHumedad", query = "SELECT r FROM Registrohumedad r WHERE r.humedad = :humedad"),
    @NamedQuery(name = "Registrohumedad.findByFecha", query = "SELECT r FROM Registrohumedad r WHERE r.fecha = :fecha")})
public class Registrohumedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registrohumedad")
    private Integer idRegistrohumedad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "humedad")
    private BigDecimal humedad;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fecha;
    @JoinColumn(name = "id_bebe", referencedColumnName = "id_bebe")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bebe idBebe;

    public Registrohumedad() {
    }

    public Registrohumedad(Integer idRegistrohumedad) {
        this.idRegistrohumedad = idRegistrohumedad;
    }

    public Registrohumedad(Integer idRegistrohumedad, BigDecimal humedad, Timestamp fecha) {
        this.idRegistrohumedad = idRegistrohumedad;
        this.humedad = humedad;
        this.fecha = fecha;
    }

    public Integer getIdRegistrohumedad() {
        return idRegistrohumedad;
    }

    public void setIdRegistrohumedad(Integer idRegistrohumedad) {
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
    
    public Bebe getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(Bebe idBebe) {
        this.idBebe = idBebe;
    }




    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistrohumedad != null ? idRegistrohumedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrohumedad)) {
            return false;
        }
        Registrohumedad other = (Registrohumedad) object;
        if ((this.idRegistrohumedad == null && other.idRegistrohumedad != null) || (this.idRegistrohumedad != null && !this.idRegistrohumedad.equals(other.idRegistrohumedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cuna_inteligente.backend_cuna_inteligente.entity.Registrohumedad[ idRegistrohumedad=" + idRegistrohumedad + " ]";
    }
    
}
