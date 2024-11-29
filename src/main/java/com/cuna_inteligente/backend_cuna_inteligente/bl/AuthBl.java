package com.cuna_inteligente.backend_cuna_inteligente.bl;
import com.cuna_inteligente.backend_cuna_inteligente.dao.UsuarioRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.LoginDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.TokenDto;
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

    @Transactional
    public TokenDto login(LoginDto loginDto) {
        TokenDto tokenDto = new TokenDto();
        try {
        Usuario user = usuarioRepository.findByGmail(loginDto.getGmail());
        if (user != null) {
            int idUser = user.getIdUsuario();
            tokenDto.setIdUser(user.getIdUsuario());
            tokenDto.setAuthToken("");
             tokenDto.setRefreshToken("");
             tokenDto.setType(1);
        }else{
            tokenDto.setType(-1);
        }
        return tokenDto;
    }catch (Exception e){
        tokenDto.setType(-2);
    }
    return tokenDto;
    }

    public String validateToken(int idPath) {
       
            return "Token valido";/// Pequeño cambio, antes devolvía el rol como verificación de que estaba bien
    }

 

    public ResponseDto<TokenDto> gestionarRespuestaToken(Integer tokenDto) {
        ResponseDto<TokenDto> responseDto = new ResponseDto<>();
        switch (tokenDto) {
            case -2:
                responseDto.setCode("500");
                responseDto.setErrorMessage("Error en el servidor");
                break;
            case -1:
                responseDto.setCode("401");
                responseDto.setErrorMessage("Usuario o contraseña incorrectos");
                break;
            case -3:
                responseDto.setErrorMessage("Refresh token expirado");
                responseDto.setCode("401");
                break;
            case -4:
                responseDto.setErrorMessage("Refresh token invalido");
                responseDto.setCode("401");
                break;
            default:
                responseDto.setCode("200");
                break;
        }
        return responseDto;
    }


    public <T> ResponseDto<T> gestionarRespuesta( Integer idUser, String errorMessage) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode("200");
        return responseDto;
    }


    
}
