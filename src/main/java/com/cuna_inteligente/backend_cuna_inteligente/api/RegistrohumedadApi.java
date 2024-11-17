package com.cuna_inteligente.backend_cuna_inteligente.api;

import org.springframework.web.bind.annotation.*;
import com.cuna_inteligente.backend_cuna_inteligente.bl.RegistrohumedadBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrohumedadDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RegistrohumedadApi {
    private final RegistrohumedadBl registrohumedadBl;  

    public RegistrohumedadApi(RegistrohumedadBl registrohumedadBl) {
        this.registrohumedadBl = registrohumedadBl;
    }

    @PostMapping("registrohumedad/{idBebe}")
    public ResponseDto<RegistrohumedadDto> createRegistroHumedad(@RequestBody RegistrohumedadDto registrohumedadDto, @PathVariable Integer idBebe) {
        ResponseDto<RegistrohumedadDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrohumedadBl.save(registrohumedadDto, idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de humedad");
            responseDto.setCode("500");
        }
        return responseDto;

    }

    @PostMapping("registrohumedad/prueba/{idBebe}/{temperatura}")
    public ResponseDto<RegistrohumedadDto> createRegistroHumedadTodoId( @PathVariable Integer idBebe, @PathVariable BigDecimal temperatura) {
        ResponseDto<RegistrohumedadDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrohumedadBl.saveTodo( idBebe, temperatura));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de humedad");
            responseDto.setCode("500");
        }
        return responseDto;

    }


    @GetMapping("registrohumedad/{idBebe}")
    public ResponseDto<List<RegistrohumedadDto>> getRegistroHumedadByBebe(@PathVariable Integer idBebe){
        ResponseDto<List<RegistrohumedadDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrohumedadBl.findRegistroHumedadByBebe(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de humedad");
            responseDto.setCode("500");
        }
        return responseDto;
    }
     //obtener temperatura de todos los bebes del usuario no da
    @GetMapping("registrohumedad/usuario/{idUsuario}")
    public ResponseDto<List<RegistrohumedadDto>> getRegistroHumedadByUsuario(@PathVariable Integer idUsuario){
        ResponseDto<List<RegistrohumedadDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrohumedadBl.findRegistroHumedadByUsuario(idUsuario));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de humedad");
            responseDto.setCode("500");
        }
        return responseDto;
    }
    
    
}
