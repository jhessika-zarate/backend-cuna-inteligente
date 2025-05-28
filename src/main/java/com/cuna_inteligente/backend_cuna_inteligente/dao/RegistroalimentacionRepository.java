package com.cuna_inteligente.backend_cuna_inteligente.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Registroalimentacion;

public interface RegistroalimentacionRepository extends JpaRepository<Registroalimentacion, Integer>
{
    //
    @Query(value = "SELECT * FROM registroalimentacion WHERE id_bebe = ?1", nativeQuery = true)
    List<Registroalimentacion> findRegistroAlimentacionByBebe(int idBebe);
    //
    @Query(value = "SELECT * FROM registroalimentacion WHERE id_bebe IN (SELECT id_bebe FROM bebe WHERE id_usuario = ?1)", nativeQuery = true)
    List<Registroalimentacion> findRegistroAlimentacionByUsuario(int idUsuario);
    //
    @Query(value = "SELECT * FROM registroalimentacion WHERE id_bebe = ?1 ORDER BY id_registroalimentacion DESC LIMIT 1", nativeQuery = true)    
    Registroalimentacion findTopByBebeIdBebeOrderByIdRegistroalimentacionDesc(int idBebe);
}
