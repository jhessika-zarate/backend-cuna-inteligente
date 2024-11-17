package com.cuna_inteligente.backend_cuna_inteligente.api;

import com.cuna_inteligente.backend_cuna_inteligente.bl.BebeBl;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class BebeApi {
    private final BebeBl bebeBl;

    public BebeApi(BebeBl bebeBl){
        this.bebeBl = bebeBl;
    }

    @PostMapping("bebe/{idUsuario}")
    public ResponseDto<BebeDto> createBebe(@PathVariable Integer idUsuario, @RequestBody BebeDto bebeDto){
        ResponseDto<BebeDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(bebeBl.save(bebeDto,idUsuario));
            return responseDto;
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al registrar un nuevo bebé");
            responseDto.setCode("500");
            return responseDto;
        }
    }

    @PostMapping("bebe/registrar")
    public ResponseDto<BebeDto> registrarBebeAbierto(@RequestBody BebeDto bebeDto) {
    ResponseDto<BebeDto> responseDto = new ResponseDto<>();
    try {
        responseDto.setResponse(bebeBl.save(bebeDto, bebeDto.getIdUsuario().getIdUsuario()));
        responseDto.setCode("200");
        responseDto.setErrorMessage(null);
    } catch (Exception e) {
        responseDto.setErrorMessage("Error al registrar un nuevo bebé");
        responseDto.setCode("500");
    }
    return responseDto;
}


    @PutMapping("bebe/{id}")
public ResponseDto<BebeDto> updateBebe(@PathVariable Integer id, @RequestBody BebeDto bebeDto){
    ResponseDto<BebeDto> responseDto = new ResponseDto<>();
    try {
        responseDto.setErrorMessage(null);
        responseDto.setCode("200");
        responseDto.setResponse(bebeBl.updateBebe(bebeDto));
        return responseDto;
    } catch (Exception e) {
        System.out.println(String.format("Error al actualizar el bebé con ID %d: %s", id, e.getMessage()));
        responseDto.setErrorMessage("Error al actualizar el bebé con ID " + id);
        responseDto.setCode("500");
        return responseDto;
    }
}
 // Obtener el bebé seleccionado
 @GetMapping("seleccionado")
 public BebeDto getSelectedBebe() {
     return bebeBl.findSelectedBebe();
 }

  // Obtener el solo id bebé seleccionado
  @GetMapping("seleccionado/id")
  public int getSelectedBebeOnlyId() {
        return bebeBl.findSelectedBebe().getIdBebe();
  }

  // obtener solo si esta en true o false movimiento del bebe selecionado
    @GetMapping("seleccionado/movimiento")
    public boolean getMovimientoBebe() {
        return bebeBl.findSelectedBebe().getMovimiento();
    }


    @GetMapping("bebe")
    public ResponseDto<List<BebeDto>> getAllBebes() {
        ResponseDto<List<BebeDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(bebeBl.findAll());
            return responseDto;
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los bebés");
            responseDto.setCode("500");
            return responseDto;
        }
    }

    @GetMapping("bebe/{id}")
    public ResponseDto<BebeDto> getBebeById(@PathVariable Integer id) {
        ResponseDto<BebeDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(bebeBl.findById(id));
            return responseDto;
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener el bebé con ID " + id);
            responseDto.setCode("500");
            return responseDto;
        }
    }

    @GetMapping("bebe/usuario/{idUsuario}")
    public ResponseDto<List<BebeDto>> getBebesByUsuario(@PathVariable Integer idUsuario) {
        ResponseDto<List<BebeDto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(bebeBl.findBebeByUsuario(idUsuario));
            return responseDto;
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al obtener los bebés del usuario con ID " + idUsuario);
            responseDto.setCode("500");
            return responseDto;
        }
    }


    @PutMapping("bebe/seleccionado/{id}")
    public ResponseDto<BebeDto> updateSelectedBebe(@PathVariable Integer id) {
        ResponseDto<BebeDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(bebeBl.updateSelectedBebe(id));
            return responseDto;
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al actualizar el bebé seleccionado");
            responseDto.setCode("500");
            return responseDto;
        }
    }

    //put donde coloques todos los bebes en movimiento en false y al bebe seleccionado en true
    @PutMapping("bebe/cambiar/movimiento/true")
        public ResponseDto<BebeDto> updateMovimientoBebeTrue() {
            ResponseDto<BebeDto> responseDto = new ResponseDto<>();
            try {
                responseDto.setErrorMessage(null);
                responseDto.setCode("200");
                responseDto.setResponse(bebeBl.updateMovimientoBebeTrue());
                return responseDto;
            } catch (Exception e) {
                responseDto.setErrorMessage("Error al actualizar el bebé seleccionado");
                responseDto.setCode("500");
                return responseDto;
            }
        }
    //quiero una funcion put, donde pongas al bebe seleccionado en false
   
    @PutMapping("bebe/cambiar/movimiento/false")
    public ResponseDto<BebeDto> updateMovimientoBebeFalse() {
        ResponseDto<BebeDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(bebeBl.updateMovimientoBebeFalse());
            return responseDto;
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al actualizar el bebé seleccionado");
            responseDto.setCode("500");
            return responseDto;
        }
    }



    @PutMapping("bebe/movimiento/{idUsuario}")
    public ResponseDto<BebeDto> updateMovimientoBebe(@PathVariable Integer idUsuario) {
        ResponseDto<BebeDto> responseDto = new ResponseDto<>();
        try {
            responseDto.setErrorMessage(null);
            responseDto.setCode("200");
            responseDto.setResponse(bebeBl.updateMovimientoBebe(idUsuario));
            return responseDto;
        } catch (Exception e) {
            responseDto.setErrorMessage("Error al actualizar el bebé seleccionado");
            responseDto.setCode("500");
            return responseDto;
        }
    }


    
    

    
}
