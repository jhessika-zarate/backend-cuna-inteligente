package com.cuna_inteligente.backend_cuna_inteligente.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrotemperatura;

public interface RegistrotemperaturaRepository extends JpaRepository<Registrotemperatura, Integer>
{
    // los registros de temperatura de un bebe
    @Query(value = "SELECT * FROM registrotemperatura WHERE id_bebe = ?1", nativeQuery = true)
    List<Registrotemperatura> findRegistroTemperaturaByBebe(int idBebe);
    

}