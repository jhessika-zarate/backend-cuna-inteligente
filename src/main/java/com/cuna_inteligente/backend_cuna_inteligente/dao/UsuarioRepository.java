package com.cuna_inteligente.backend_cuna_inteligente.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByUsername(String username);
    public Usuario findByGmail(String gmail);
    public Usuario findByContrasenia(String contrasenia);

} 