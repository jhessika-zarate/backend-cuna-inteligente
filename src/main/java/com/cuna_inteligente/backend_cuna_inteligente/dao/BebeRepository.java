package com.cuna_inteligente.backend_cuna_inteligente.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;

public interface BebeRepository extends JpaRepository<Bebe, Integer>
{
      // solo el bebe que tenga el booleano de seleccionado true,  dame su int idBebe, boolean seleccionado
        @Query(value = "SELECT * FROM bebe WHERE seleccionado = true", nativeQuery = true)
        List<Bebe> findSelectedBebe();
        // solo el bebe que tenga el booleano de seleccionado true,  dame su int idBebe, boolean seleccionado
        @Query(value = "SELECT * FROM bebe WHERE seleccionado = true", nativeQuery = true)
        List<Bebe> findSelectedBebeConId();
        // los bebes que tiene un usuario
        @Query(value = "SELECT * FROM bebe WHERE id_usuario = ?1", nativeQuery = true)
        List<Bebe> findBebesByUsuario(int idUsuario);
    
} 
