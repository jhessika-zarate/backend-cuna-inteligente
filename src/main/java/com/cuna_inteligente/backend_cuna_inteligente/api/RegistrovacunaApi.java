package com.cuna_inteligente.backend_cuna_inteligente.api;
import org.springframework.web.bind.annotation.*;

import com.cuna_inteligente.backend_cuna_inteligente.bl.RegistrovacunaBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrovacunaDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RegistrovacunaApi {

    private final RegistrovacunaBl registrovacunaBl;

    public RegistrovacunaApi(RegistrovacunaBl registrovacunaBl) {
        this.registrovacunaBl = registrovacunaBl;
    }

    @PostMapping("registrovacuna/{idBebe}")
    public ResponseDto<RegistrovacunaDto> createRegistroVacuna(@RequestBody RegistrovacunaDto registrovacunaDto, @PathVariable Integer idBebe) {
        ResponseDto<RegistrovacunaDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrovacunaBl.save(registrovacunaDto, idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de vacuna");
            responseDto.setCode("500");
        }
        return responseDto;

    }

    @GetMapping("registrovacuna/{idBebe}")
    public ResponseDto<List<RegistrovacunaDto>> getRegistroVacunaByBebe(@PathVariable Integer idBebe){
        ResponseDto<List<RegistrovacunaDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrovacunaBl.findRegistroVacunaByBebe(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de vacuna");
            responseDto.setCode("500");
        }
        return responseDto;
    }
    
}
