package com.cuna_inteligente.backend_cuna_inteligente.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrovacuna;

public interface RegistrovacunaRepository extends JpaRepository<Registrovacuna, Integer>
{
    //
    @Query(value = "SELECT * FROM registrovacuna WHERE id_bebe = ?1", nativeQuery = true)
    List<Registrovacuna> findRegistroVacunaByBebe(int idBebe);
    //
    @Query(value = "SELECT * FROM registrovacuna WHERE id_bebe IN (SELECT id_bebe FROM bebe WHERE id_usuario = ?1)", nativeQuery = true)
    List<Registrovacuna> findRegistroVacunaByUsuario(int idUsuario);
    //
    @Query(value = "SELECT * FROM registrovacuna WHERE id_bebe = ?1 ORDER BY id_registrovacuna DESC LIMIT 1", nativeQuery = true)    
    Registrovacuna findTopByBebeIdBebeOrderByIdRegistrovacunaDesc(int idBebe);
}