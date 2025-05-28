package com.cuna_inteligente.backend_cuna_inteligente.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Registromovimiento;

public interface RegistromovimientoRepository extends JpaRepository<Registromovimiento, Integer>
{
    // los registros de movimiento de un bebe
    @Query(value = "SELECT * FROM registromovimiento WHERE id_bebe = ?1", nativeQuery = true)
    List<Registromovimiento> findRegistroMovimientoByBebe(int idBebe);
    //obtener movimiento de todos los bebes del usuario
    @Query(value = "SELECT * FROM registromovimiento WHERE id_bebe IN (SELECT id_bebe FROM bebe WHERE id_usuario = ?1)", nativeQuery = true)
    List<Registromovimiento> findRegistroMovimientoByUsuario(int idUsuario);
    

}