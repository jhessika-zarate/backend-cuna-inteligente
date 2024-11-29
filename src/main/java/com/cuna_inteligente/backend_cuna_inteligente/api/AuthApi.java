package com.cuna_inteligente.backend_cuna_inteligente.api;

import com.cuna_inteligente.backend_cuna_inteligente.bl.AuthBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.LoginDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.TokenDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthApi {
    private AuthBl authBl;

    public AuthApi(AuthBl authBl){
        this.authBl = authBl;
    }

    @PostMapping("/login")
    public ResponseDto<TokenDto> login(@RequestBody LoginDto loginDto){
        return authBl.gestionarRespuestaToken(0);
    }

    @PostMapping("/refresh")
    public ResponseDto<TokenDto> refresh(@RequestBody TokenDto tokenDto){
        return authBl.gestionarRespuestaToken(1);
    }
}
