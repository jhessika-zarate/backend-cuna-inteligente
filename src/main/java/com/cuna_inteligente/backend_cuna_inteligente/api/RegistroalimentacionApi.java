package com.cuna_inteligente.backend_cuna_inteligente.api;
import org.springframework.web.bind.annotation.*;

import com.cuna_inteligente.backend_cuna_inteligente.bl.RegistroalimentacionBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistroalimentacionDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RegistroalimentacionApi {
    private final RegistroalimentacionBl registroalimentacionBl;

    public RegistroalimentacionApi(RegistroalimentacionBl registroalimentacionBl) {
        this.registroalimentacionBl = registroalimentacionBl;
    }

    @PostMapping("registroalimentacion/{idBebe}")
    public ResponseDto<RegistroalimentacionDto> createRegistroAlimentacion(@RequestBody RegistroalimentacionDto registroalimentacionDto, @PathVariable Integer idBebe) {
        ResponseDto<RegistroalimentacionDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registroalimentacionBl.save(registroalimentacionDto, idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de alimentacion");
            responseDto.setCode("500");
        }
        return responseDto;

    }

    @GetMapping("registroalimentacion/{idBebe}")
    public ResponseDto<List<RegistroalimentacionDto>> getRegistroAlimentacionByBebe(@PathVariable Integer idBebe){
        ResponseDto<List<RegistroalimentacionDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registroalimentacionBl.findRegistroAlimentacionByBebe(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de alimentacion");
            responseDto.setCode("500");
        }
        return responseDto;
    }
    
    
}
