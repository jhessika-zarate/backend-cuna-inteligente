package com.cuna_inteligente.backend_cuna_inteligente.api;

import com.cuna_inteligente.backend_cuna_inteligente.bl.AuthBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.LoginDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
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
    public ResponseDto<String> login(@RequestBody LoginDto loginDto){
        ResponseDto<String> responseDto = new ResponseDto<>();
        try{
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
              return responseDto;
        }catch (Exception e){
            responseDto.setErrorMessage("Error al iniciar sesión");
            responseDto.setCode("500");
            return responseDto;
        }
    }

    @GetMapping("/validate/{id}")
    public ResponseDto<String> validateToken(@PathVariable int id){
        ResponseDto<String> responseDto = new ResponseDto<>();
        try{
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(authBl.validateToken(id));
            return responseDto;
        }catch (Exception e){
            responseDto.setErrorMessage("Error al validar el token");
            responseDto.setCode("500");
            return responseDto;
        }
    }
    
}
