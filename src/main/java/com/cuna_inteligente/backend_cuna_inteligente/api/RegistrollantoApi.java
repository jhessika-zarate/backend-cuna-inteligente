package com.cuna_inteligente.backend_cuna_inteligente.api;
import org.springframework.web.bind.annotation.*;

import com.cuna_inteligente.backend_cuna_inteligente.bl.RegistrollantoBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrollantoDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RegistrollantoApi {
    private final RegistrollantoBl registrollantoBl;

    public RegistrollantoApi(RegistrollantoBl registrollantoBl) {
        this.registrollantoBl = registrollantoBl;
    }

    @PostMapping("registrollanto/{idBebe}")
    public ResponseDto<RegistrollantoDto> createRegistroLlanto(@RequestBody RegistrollantoDto registrollantoDto, @PathVariable Integer idBebe) {
        ResponseDto<RegistrollantoDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrollantoBl.save(registrollantoDto, idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de llanto");
            responseDto.setCode("500");
        }
        return responseDto;

    }

    @GetMapping("registrollanto/{idBebe}")
    public ResponseDto<List<RegistrollantoDto>> getRegistroLlantoByBebe(@PathVariable Integer idBebe){
        ResponseDto<List<RegistrollantoDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(registrollantoBl.findRegistrollantoByBebe(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de llanto");
            responseDto.setCode("500");
        }
        return responseDto;
    }

    
}
