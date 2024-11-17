package com.cuna_inteligente.backend_cuna_inteligente.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrohumedad;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;

import com.cuna_inteligente.backend_cuna_inteligente.dao.RegistrotemperaturaRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrotemperaturaDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrotemperatura;

import java.sql.Timestamp;
@Service
public class RegistrotemperaturaBl {
    private final RegistrotemperaturaRepository registrotemperaturaRepository;

    private final BebeBl bebeBl; // Inyecta la dependencia de BebeBl

    public RegistrotemperaturaBl(RegistrotemperaturaRepository registrotemperaturaRepository, BebeBl bebeBl) {
        this.registrotemperaturaRepository = registrotemperaturaRepository;
        this.bebeBl = bebeBl;  // Asigna la instancia de BebeBl
    }

    public RegistrotemperaturaDto findById(Integer id) {
        Registrotemperatura registrotemperatura = registrotemperaturaRepository.findById(id).orElse(null);
        return registrotemperatura != null ? transformEntityToDto(registrotemperatura) : null;
    }

    public List<RegistrotemperaturaDto> findRegistroTemperaturaByBebe(Integer idBebe) {
        List<Registrotemperatura> registros = registrotemperaturaRepository.findRegistroTemperaturaByBebe(idBebe);
        List<RegistrotemperaturaDto> registroDtos = new ArrayList<>();
        for (Registrotemperatura registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }
    //registro de temperatura de todos los bebes del usuario 
    public List<RegistrotemperaturaDto> findRegistroTemperaturaByUsuario(Integer idUsuario) {
        List<Registrotemperatura> registros = registrotemperaturaRepository.findRegistroTemperaturaByUsuario(idUsuario);
        List<RegistrotemperaturaDto> registroDtos = new ArrayList<>();
        for (Registrotemperatura registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }

    public RegistrotemperaturaDto save(RegistrotemperaturaDto registrotemperaturaDto, Integer idBebe) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }

            Registrotemperatura registrotemperatura = transformDtoToEntityToCreate(registrotemperaturaDto, idBebe);
            registrotemperatura = registrotemperaturaRepository.save(registrotemperatura);
            return transformEntityToDto(registrotemperatura);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el registro de temperatura", e);
        }
    }

    public Registrotemperatura transformDtoToEntityToCreate(RegistrotemperaturaDto registrotemperaturaDto, Integer idBebe) {
        try {
            Registrotemperatura registrotemperatura = new Registrotemperatura();
            registrotemperatura.setTemperatura(registrotemperaturaDto.getTemperatura());
            registrotemperatura.setFecha(registrotemperaturaDto.getFecha());
            registrotemperatura.setIdBebe(new Bebe(idBebe));
            return registrotemperatura;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al transformar el registro de temperatura a entity", e);
        }

    }

    public RegistrotemperaturaDto saveTodo(Integer idBebe , BigDecimal temperatura) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }

            Registrotemperatura registrotemperatura = transformDtoToEntityToCreatePrueba(idBebe, temperatura);
            registrotemperatura = registrotemperaturaRepository.save(registrotemperatura);
            return transformEntityToDto(registrotemperatura);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el registro de temperatura", e);
        }
    }

     public Registrotemperatura transformDtoToEntityToCreatePrueba(Integer idBebe, BigDecimal temperatura) {
        try{
           
            Registrotemperatura registrotemperatura = new Registrotemperatura();
            registrotemperatura.setTemperatura(temperatura);
            registrotemperatura.setFecha(new Timestamp(System.currentTimeMillis()));
            registrotemperatura.setIdBebe(new Bebe(idBebe));
            return registrotemperatura;
        }catch(Exception e){
            System.out.println("Error al transformar el DTO de Registrohumedad a entidad");
            throw new RuntimeException("Error al transformar el DTO de Registrohumedad a entidad", e);
        }
            
    }






    public RegistrotemperaturaDto transformEntityToDto (Registrotemperatura registrotemperatura){
    try{
        BebeDto bebeDto = convertitrBebeToDto(registrotemperatura.getIdBebe());
           RegistrotemperaturaDto registrotemperaturaDto = new RegistrotemperaturaDto();
              registrotemperaturaDto.setIdRegistrotemp(registrotemperatura.getIdRegistrotemp());
                registrotemperaturaDto.setTemperatura(registrotemperatura.getTemperatura());
                registrotemperaturaDto.setFecha(registrotemperatura.getFecha());
                registrotemperaturaDto.setIdBebe(bebeDto);
                return registrotemperaturaDto;
    }catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException("Error al transformar el registro de temperatura a dto", e);
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
