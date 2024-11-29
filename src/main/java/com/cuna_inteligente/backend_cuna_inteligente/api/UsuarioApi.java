package com.cuna_inteligente.backend_cuna_inteligente.api;

import com.cuna_inteligente.backend_cuna_inteligente.bl.UsuarioBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class UsuarioApi {
    private final UsuarioBl usuarioBl;

    public UsuarioApi(UsuarioBl usuarioBl){
        this.usuarioBl = usuarioBl;
      
    }

    @PostMapping("/usuario")
    public ResponseDto<UsuarioDto> createUser(@RequestBody UsuarioDto usuarioDto){
        ResponseDto<UsuarioDto> responseDto = new ResponseDto<>();
        try{
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(usuarioBl.createUser(usuarioDto));
            return responseDto;
        }catch (Exception e){
            responseDto.setErrorMessage("Error al registrar un nuevo usuario");
            responseDto.setCode("500");
            return responseDto;
        }
    }

    @GetMapping("/usuario")
    public ResponseDto<List<UsuarioDto>> getAllUser ()
    {
        ResponseDto<List<UsuarioDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(usuarioBl.findAllUser());
            return responseDto;
        }catch (Exception e){
            responseDto.setErrorMessage("Error al obtener los usuarios");
            responseDto.setCode("500");
            return responseDto;
        }
    }


    
}
