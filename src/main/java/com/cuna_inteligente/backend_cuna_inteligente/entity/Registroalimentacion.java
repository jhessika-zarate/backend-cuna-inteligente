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
@Table(name = "registroalimentacion")
@NamedQueries({
    @NamedQuery(name = "Registroalimentacion.findAll", query = "SELECT r FROM Registroalimentacion r"),
    @NamedQuery(name = "Registroalimentacion.findByIdRegistroalimentacion", query = "SELECT r FROM Registroalimentacion r WHERE r.idRegistroalimentacion = :idRegistroalimentacion"),
    @NamedQuery(name = "Registroalimentacion.findByFecha", query = "SELECT r FROM Registroalimentacion r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Registroalimentacion.findByTipocomida", query = "SELECT r FROM Registroalimentacion r WHERE r.tipocomida = :tipocomida")})
public class Registroalimentacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registroalimentacion")
    private Integer idRegistroalimentacion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fecha;
    @Basic(optional = false)
    @Column(name = "tipocomida")
    private boolean tipocomida;
    @JoinColumn(name = "id_bebe", referencedColumnName = "id_bebe")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bebe idBebe;

    public Registroalimentacion() {
    }

    public Registroalimentacion(Integer idRegistroalimentacion) {
        this.idRegistroalimentacion = idRegistroalimentacion;
    }

    public Registroalimentacion(Integer idRegistroalimentacion, Timestamp fecha, boolean tipocomida) {
        this.idRegistroalimentacion = idRegistroalimentacion;
        this.fecha = fecha;
        this.tipocomida = tipocomida;
    }

    public Integer getIdRegistroalimentacion() {
        return idRegistroalimentacion;
    }

    public void setIdRegistroalimentacion(Integer idRegistroalimentacion) {
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

    public Bebe getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(Bebe idBebe) {
        this.idBebe = idBebe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroalimentacion != null ? idRegistroalimentacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroalimentacion)) {
            return false;
        }
        Registroalimentacion other = (Registroalimentacion) object;
        if ((this.idRegistroalimentacion == null && other.idRegistroalimentacion != null) || (this.idRegistroalimentacion != null && !this.idRegistroalimentacion.equals(other.idRegistroalimentacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cuna_inteligente.backend_cuna_inteligente.entity.Registroalimentacion[ idRegistroalimentacion=" + idRegistroalimentacion + " ]";
    }
    
}
