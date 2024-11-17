
package com.cuna_inteligente.backend_cuna_inteligente.api;

import org.springframework.web.bind.annotation.*;
import com.cuna_inteligente.backend_cuna_inteligente.bl.RegistrotemperaturaBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrotemperaturaDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RegistrotemperaturaApi {
    private final RegistrotemperaturaBl registrotemperaturaBl;  

    public RegistrotemperaturaApi(RegistrotemperaturaBl registrotemperaturaBl) {
        this.registrotemperaturaBl = registrotemperaturaBl;
    }

    @PostMapping("registrotemperatura/{idBebe}")
    public ResponseDto<RegistrotemperaturaDto> createRegistrotemperatura(@RequestBody RegistrotemperaturaDto registrotemperaturaDto, @PathVariable Integer idBebe) {
        ResponseDto<RegistrotemperaturaDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrotemperaturaBl.save(registrotemperaturaDto, idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de temperatura");
            responseDto.setCode("500");
        }
        return responseDto;

    }

    @PostMapping("registrotemperatura/prueba/{idBebe}/{temperatura}")
    public ResponseDto<RegistrotemperaturaDto> createRegistrotemperaturaTodoId( @PathVariable Integer idBebe, @PathVariable BigDecimal temperatura) {
        ResponseDto<RegistrotemperaturaDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrotemperaturaBl.saveTodo( idBebe, temperatura));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de temperatura");
            responseDto.setCode("500");
        }
        return responseDto;

    }


    @GetMapping("registrotemperatura/{idBebe}")
    public ResponseDto<List<RegistrotemperaturaDto>> getRegistrotemperaturaByBebe(@PathVariable Integer idBebe){
        ResponseDto<List<RegistrotemperaturaDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrotemperaturaBl.findRegistroTemperaturaByBebe(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de temperatura");
            responseDto.setCode("500");
        }
        return responseDto;
    }

    @GetMapping("registrotemperatura/usuario/{idUsuario}")
    public ResponseDto<List<RegistrotemperaturaDto>> getRegistrotemperaturaByUsuario(@PathVariable Integer idUsuario){
        ResponseDto<List<RegistrotemperaturaDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrotemperaturaBl.findRegistroTemperaturaByUsuario(idUsuario));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de temperatura");
            responseDto.setCode("500");
        }
        return responseDto;
    }
    
}
