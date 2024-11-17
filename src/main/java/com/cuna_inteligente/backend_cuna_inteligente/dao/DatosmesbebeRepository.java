package com.cuna_inteligente.backend_cuna_inteligente.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Datosmesbebe;

public  interface DatosmesbebeRepository extends JpaRepository<Datosmesbebe, Integer>
{
    // los datos de un mes de un bebe
    @Query(value = "SELECT * FROM datosmesbebe WHERE id_bebe = ?1", nativeQuery = true)
    List<Datosmesbebe> findDatosMesBebe(int idBebe);
    

}