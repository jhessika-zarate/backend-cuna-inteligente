package com.cuna_inteligente.backend_cuna_inteligente.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;

import com.cuna_inteligente.backend_cuna_inteligente.dao.RegistromovimientoRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistromovimientoDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Registromovimiento;

import java.sql.Timestamp;
@Service
public class RegistromovimientoBl {
    private final RegistromovimientoRepository registromovimientoRepository;

    private final BebeBl bebeBl; // Inyecta la dependencia de BebeBl

    public RegistromovimientoBl(RegistromovimientoRepository registromovimientoRepository, BebeBl bebeBl) {
        this.registromovimientoRepository = registromovimientoRepository;
        this.bebeBl = bebeBl;  // Asigna la instancia de BebeBl
    }

    public RegistromovimientoDto findById(Integer id) {
        Registromovimiento registromovimiento = registromovimientoRepository.findById(id).orElse(null);
        return registromovimiento != null ? transformEntityToDto(registromovimiento) : null;
    }

    //findRegistroTemperaturaByBebe
    public List<RegistromovimientoDto> findRegistroMovimientoByBebe(Integer idBebe) {
        List<Registromovimiento> registros = registromovimientoRepository.findRegistroMovimientoByBebe(idBebe);
        List<RegistromovimientoDto> registroDtos = new ArrayList<>();
        for (Registromovimiento registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }

    public RegistromovimientoDto saveTodo(Integer idBebe){
        try{
             // Ahora usamos la instancia inyectada de BebeBl
             if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }
            
            Registromovimiento registromovimiento = transformDtoToEntityToCreatePrueba(idBebe);
            registromovimiento= registromovimientoRepository.save(registromovimiento);
            return transformEntityToDto(registromovimiento);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el registro de movimiento", e);
        }
    }

    public Registromovimiento transformDtoToEntityToCreatePrueba(Integer idBebe){
        try{
            Registromovimiento registromovimiento = new Registromovimiento();

            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            registromovimiento.setFecha(fechaActual); // Esto asigna la fecha y hora actuales    
            registromovimiento.setIdBebe(new Bebe(idBebe));
          
            return registromovimiento;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al transformar el registro de movimiento a entidad", e);
        }
    }



    public RegistromovimientoDto transformEntityToDto(Registromovimiento registromovimiento){
        try{
            BebeDto bebeDto = convertitrBebeToDto(registromovimiento.getIdBebe());
            RegistromovimientoDto registromovimientoDto = new RegistromovimientoDto();
            registromovimientoDto.setIdMovimiento(registromovimiento.getIdMovimiento());
            registromovimientoDto.setFecha(registromovimiento.getFecha());
            registromovimientoDto.setIdBebe(bebeDto);
            return registromovimientoDto;
        }catch(Exception e){
            e.printStackTrace();
        throw new RuntimeException("Error al transformar el registro de movimiento a dto", e);
   

        }
    }

    public BebeDto convertitrBebeToDto(Bebe bebe) {
        if (bebe == null) {
            return null;
        }
        BebeDto bebeDto = new BebeDto();
        bebeDto.setIdBebe(bebe.getIdBebe());
        bebeDto.setNombre(bebe.getNombre());
        bebeDto.setApellidopaterno(bebe.getApellidopaterno());
        bebeDto.setApellidomaterno(bebe.getApellidomaterno());
        bebeDto.setSeleccionado(bebe.getSeleccionado());
        bebeDto.setFechadenacimiento(bebe.getFechadenacimiento());
        bebeDto.setColor(bebe.getColor());
        bebeDto.setIdUsuario(convertitrUsuarioToDto(bebe.getIdUsuario()));
        bebeDto.setMovimiento(bebe.getMovimiento());
        return bebeDto;
    }

      public UsuarioDto convertitrUsuarioToDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setGmail(usuario.getGmail());
        return usuarioDto;
    }

}
