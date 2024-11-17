package com.cuna_inteligente.backend_cuna_inteligente.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.sql.Timestamp;  // Importar Timestamp
import java.util.List;

/**
 *
 * @author jhessikazarateluque
 */
@Entity
@Table(name = "bebe")
@NamedQueries({
    @NamedQuery(name = "Bebe.findAll", query = "SELECT b FROM Bebe b"),
    @NamedQuery(name = "Bebe.findByIdBebe", query = "SELECT b FROM Bebe b WHERE b.idBebe = :idBebe"),
    @NamedQuery(name = "Bebe.findByNombre", query = "SELECT b FROM Bebe b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Bebe.findByApellidopaterno", query = "SELECT b FROM Bebe b WHERE b.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "Bebe.findByApellidomaterno", query = "SELECT b FROM Bebe b WHERE b.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "Bebe.findBySeleccionado", query = "SELECT b FROM Bebe b WHERE b.seleccionado = :seleccionado"),
    @NamedQuery(name = "Bebe.findByFechadenacimiento", query = "SELECT b FROM Bebe b WHERE b.fechadenacimiento = :fechadenacimiento"),
    @NamedQuery(name = "Bebe.findByColor", query = "SELECT b FROM Bebe b WHERE b.color = :color"),
    //agrega el campo movimiento que es un boleano
    @NamedQuery(name = "Bebe.findByMovimiento", query = "SELECT b FROM Bebe b WHERE b.movimiento = :movimiento")})
    public class Bebe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bebe")
    private Integer idBebe;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidopaterno")
    private String apellidopaterno;
    @Basic(optional = false)
    @Column(name = "apellidomaterno")
    private String apellidomaterno;
    @Basic(optional = false)
    @Column(name = "seleccionado")
    private boolean seleccionado;
    @Basic(optional = false)
    @Column(name = "fechadenacimiento")
    private Timestamp fechadenacimiento;  // Cambiado a Timestamp
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @Column(name = "movimiento")
    private boolean movimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBebe", fetch = FetchType.LAZY)
    private List<Registroalimentacion> registroalimentacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBebe", fetch = FetchType.LAZY)
    private List<Registrohumedad> registrohumedadList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBebe", fetch = FetchType.LAZY)
    private List<Registrollanto> registrollantoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBebe", fetch = FetchType.LAZY)
    private List<Registrotemperatura> registrotemperaturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBebe", fetch = FetchType.LAZY)
    private List<Datosmesbebe> datosmesbebeList;

    public Bebe() {
    }

    public Bebe(Integer idBebe) {
        this.idBebe = idBebe;
    }

    public Bebe(Integer idBebe, String nombre, String apellidopaterno, String apellidomaterno, boolean seleccionado, Timestamp fechadenacimiento, String color,boolean movimiento) {
        this.idBebe = idBebe;
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.seleccionado = seleccionado;
        this.fechadenacimiento = fechadenacimiento;  // Cambiado a Timestamp
        this.color = color;
        this.movimiento = movimiento;
    }

    public Integer getIdBebe() {
        return idBebe;
    }

    public void setIdBebe(Integer idBebe) {
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
        return fechadenacimiento;  // Cambiado a Timestamp
    }

    public void setFechadenacimiento(Timestamp fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;  // Cambiado a Timestamp
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
    

    public List<Registroalimentacion> getRegistroalimentacionList() {
        return registroalimentacionList;
    }

    public void setRegistroalimentacionList(List<Registroalimentacion> registroalimentacionList) {
        this.registroalimentacionList = registroalimentacionList;
    }

    public List<Registrohumedad> getRegistrohumedadList() {
        return registrohumedadList;
    }

    public void setRegistrohumedadList(List<Registrohumedad> registrohumedadList) {
        this.registrohumedadList = registrohumedadList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Registrollanto> getRegistrollantoList() {
        return registrollantoList;
    }

    public void setRegistrollantoList(List<Registrollanto> registrollantoList) {
        this.registrollantoList = registrollantoList;
    }

    public List<Registrotemperatura> getRegistrotemperaturaList() {
        return registrotemperaturaList;
    }

    public void setRegistrotemperaturaList(List<Registrotemperatura> registrotemperaturaList) {
        this.registrotemperaturaList = registrotemperaturaList;
    }

    public List<Datosmesbebe> getDatosmesbebeList() {
        return datosmesbebeList;
    }

    public void setDatosmesbebeList(List<Datosmesbebe> datosmesbebeList) {
        this.datosmesbebeList = datosmesbebeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBebe != null ? idBebe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bebe)) {
            return false;
        }
        Bebe other = (Bebe) object;
        if ((this.idBebe == null && other.idBebe != null) || (this.idBebe != null && !this.idBebe.equals(other.idBebe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe[ idBebe=" + idBebe + " ]";
    }
}
