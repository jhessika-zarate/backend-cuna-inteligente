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
@Table(name = "datosmesbebe")
@NamedQueries({
    @NamedQuery(name = "Datosmesbebe.findAll", query = "SELECT d FROM Datosmesbebe d"),
    @NamedQuery(name = "Datosmesbebe.findByIdRegistrocaracteristicas", query = "SELECT d FROM Datosmesbebe d WHERE d.idRegistrocaracteristicas = :idRegistrocaracteristicas"),
    @NamedQuery(name = "Datosmesbebe.findByPeso", query = "SELECT d FROM Datosmesbebe d WHERE d.peso = :peso"),
    @NamedQuery(name = "Datosmesbebe.findByAltura", query = "SELECT d FROM Datosmesbebe d WHERE d.altura = :altura"),
    @NamedQuery(name = "Datosmesbebe.findByFecha", query = "SELECT d FROM Datosmesbebe d WHERE d.fecha = :fecha")})
public class Datosmesbebe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registrocaracteristicas")
    private Integer idRegistrocaracteristicas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "peso")
    private BigDecimal peso;
    @Basic(optional = false)
    @Column(name = "altura")
    private BigDecimal altura;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fecha;
    @JoinColumn(name = "id_bebe", referencedColumnName = "id_bebe")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bebe idBebe;

    public Datosmesbebe() {
    }

    public Datosmesbebe(Integer idRegistrocaracteristicas) {
        this.idRegistrocaracteristicas = idRegistrocaracteristicas;
    }

    
    public Datosmesbebe(Integer idRegistrocaracteristicas, BigDecimal peso, BigDecimal altura, Timestamp fecha) {
        this.idRegistrocaracteristicas = idRegistrocaracteristicas;
        this.peso = peso;
        this.altura = altura;
        this.fecha = fecha;
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

    public Bebe getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(Bebe idBebe) {
        this.idBebe = idBebe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistrocaracteristicas != null ? idRegistrocaracteristicas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datosmesbebe)) {
            return false;
        }
        Datosmesbebe other = (Datosmesbebe) object;
        if ((this.idRegistrocaracteristicas == null && other.idRegistrocaracteristicas != null) || (this.idRegistrocaracteristicas != null && !this.idRegistrocaracteristicas.equals(other.idRegistrocaracteristicas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cuna_inteligente.backend_cuna_inteligente.entity.Datosmesbebe[ idRegistrocaracteristicas=" + idRegistrocaracteristicas + " ]";
    }
    
}
