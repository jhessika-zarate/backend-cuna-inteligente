package com.cuna_inteligente.backend_cuna_inteligente.api;
import org.springframework.web.bind.annotation.*;

import com.cuna_inteligente.backend_cuna_inteligente.bl.DatosmesbebeBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.DatosmesbebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")

public class DatosmesbebeApi {
    private final DatosmesbebeBl datosmesbebeBl;

    public DatosmesbebeApi(DatosmesbebeBl datosmesbebeBl) {
        this.datosmesbebeBl = datosmesbebeBl;
    }

    @PostMapping("datosmesbebe/{idBebe}")
    public ResponseDto<DatosmesbebeDto> createDatosMesBebe(@RequestBody DatosmesbebeDto datosmesbebeDto, @PathVariable Integer idBebe) {
        ResponseDto<DatosmesbebeDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(datosmesbebeBl.save(datosmesbebeDto, idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo registro de datos del mes del bebé");
            responseDto.setCode("500");
        }
        return responseDto;

    }

    @GetMapping("datosmesbebe/{idBebe}")
    public ResponseDto<List<DatosmesbebeDto>> getDatosMesBebe(@PathVariable Integer idBebe) {
        ResponseDto<List<DatosmesbebeDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setResponse(datosmesbebeBl.findDatosMesBebe(idBebe));
            responseDto.setCode("200");
            responseDto.setErrorMessage(null);
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los registros de datos del mes del bebé");
            responseDto.setCode("500");
        }
        return responseDto;
    }
    
    
}
