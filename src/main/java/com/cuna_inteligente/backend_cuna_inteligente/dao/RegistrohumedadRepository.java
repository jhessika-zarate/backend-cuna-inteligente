package com.cuna_inteligente.backend_cuna_inteligente.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrohumedad;


public interface RegistrohumedadRepository extends JpaRepository<Registrohumedad, Integer>
{
    // los registros de humedad de un bebe
    @Query(value = "SELECT * FROM registrohumedad WHERE id_bebe = ?1", nativeQuery = true)
    List<Registrohumedad> findRegistroHumedadByBebe(int idBebe);
    

}
