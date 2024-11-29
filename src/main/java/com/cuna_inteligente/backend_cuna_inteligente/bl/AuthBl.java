package com.cuna_inteligente.backend_cuna_inteligente.bl;
import com.cuna_inteligente.backend_cuna_inteligente.dao.UsuarioRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.LoginDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.interfaces.DecodedJWT;


@Service
public class AuthBl {
    public static final String KEY = "cuna_inteligente";
    private final UsuarioRepository usuarioRepository;

    public AuthBl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

  
    public String validateToken(int idPath) {
       
            return "Token valido";/// Pequeño cambio, antes devolvía el rol como verificación de que estaba bien
    }

 

   
    public <T> ResponseDto<T> gestionarRespuesta( Integer idUser, String errorMessage) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode("200");
        return responseDto;
    }


    
}
