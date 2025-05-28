package com.cuna_inteligente.backend_cuna_inteligente.api;

import org.springframework.web.bind.annotation.*;
import com.cuna_inteligente.backend_cuna_inteligente.bl.RegistromovimientoBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistromovimientoDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;

import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RegistromovimientoApi {
    private final RegistromovimientoBl registromovimientoBl;

    public RegistromovimientoApi(RegistromovimientoBl registromovimientoBl) {
        this.registromovimientoBl = registromovimientoBl;
    }

    @PostMapping("registromovimiento/{idBebe}")
    public ResponseDto<RegistromovimientoDto> createRegistromovimiento(@PathVariable Integer idBebe) {
        ResponseDto<RegistromovimientoDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registromovimientoBl.saveTodo(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de movimiento");
            responseDto.setCode("500");
        }
        return responseDto;

    }

    @GetMapping("registromovimiento/{idBebe}")
    public ResponseDto<List<RegistromovimientoDto>> getRegistromovimientoByBebe(@PathVariable Integer idBebe){
        ResponseDto<List<RegistromovimientoDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registromovimientoBl.findRegistroMovimientoByBebe(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de movimiento");
            responseDto.setCode("500");
        }
        return responseDto;
    }
    
    
}
