package com.cuna_inteligente.backend_cuna_inteligente.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrollanto;

public interface RegistrollantoRepository extends JpaRepository<Registrollanto, Integer>
{
    //
    @Query(value = "SELECT * FROM registrollanto WHERE id_bebe = ?1", nativeQuery = true)
    List<Registrollanto> findRegistrollantoByBebe(int idBebe);
    //
    @Query(value = "SELECT * FROM registrollanto WHERE id_bebe IN (SELECT id_bebe FROM bebe WHERE id_usuario = ?1)", nativeQuery = true)
    List<Registrollanto> findRegistrollantoByUsuario(int idUsuario);
    //
    @Query(value = "SELECT * FROM registrollanto WHERE id_bebe = ?1 ORDER BY id_registrollanto DESC LIMIT 1", nativeQuery = true)    
    Registrollanto findTopByBebeIdBebeOrderByIdRegistrollantoDesc(int idBebe);
}