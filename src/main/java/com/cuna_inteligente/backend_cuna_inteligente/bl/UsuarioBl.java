package com.cuna_inteligente.backend_cuna_inteligente.bl;
import com.cuna_inteligente.backend_cuna_inteligente.dao.UsuarioRepository;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.utility.HashingUtility;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
public class UsuarioBl {
    private final UsuarioRepository usuarioRepository;

    public  UsuarioBl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioDto createUser(UsuarioDto newUser){
        try {
            if(usuarioRepository.findByGmail(newUser.getGmail()) != null){
                return null;
            }

            HashingUtility hashingUtility = new HashingUtility();
            Usuario user = new Usuario();
            user.setUsername(newUser.getUsername());
            user.setGmail(newUser.getGmail());
            user.setContrasenia(hashingUtility.hashPassword(newUser.getContrasenia()));
            return new UsuarioDto(usuarioRepository.save(user));
    }catch (Exception e){
        System.out.println(e);
        return null;
    }
    }

    public  String circo (){
        return "circo";
    }

}
