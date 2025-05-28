package com.cuna_inteligente.backend_cuna_inteligente.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.dao.RegistroalimentacionRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistroalimentacionDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Registroalimentacion;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.bl.BebeBl;
import java.sql.Timestamp;

@Service
public class RegistroalimentacionBl {
    private final RegistroalimentacionRepository RegistroalimentacionRepository;

    private final BebeBl bebeBl; // Inyecta la dependencia de BebeBl

    public RegistroalimentacionBl(RegistroalimentacionRepository RegistroalimentacionRepository, BebeBl bebeBl) {
        this.RegistroalimentacionRepository = RegistroalimentacionRepository;
        this.bebeBl = bebeBl;  // Asigna la instancia de BebeBl
    }

    public RegistroalimentacionDto findById(Integer id) {
        Registroalimentacion registroalimentacion = RegistroalimentacionRepository.findById(id).orElse(null);
        return registroalimentacion != null ? transformEntityToDto(registroalimentacion) : null;
    }

    public List<RegistroalimentacionDto> findRegistroAlimentacionByBebe(Integer idBebe) {
        List<Registroalimentacion> registros = RegistroalimentacionRepository.findRegistroAlimentacionByBebe(idBebe);
        List<RegistroalimentacionDto> registroDtos = new ArrayList<>();
        for (Registroalimentacion registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }

    public RegistroalimentacionDto save(RegistroalimentacionDto registroalimentacionDto, Integer idBebe) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }
            Registroalimentacion registroalimentacion = transformDtoToEntityToCreate(registroalimentacionDto, idBebe);
            return transformEntityToDto(RegistroalimentacionRepository.save(registroalimentacion));
        } catch (Exception e) {
            System.out.println("Error al guardar el registroalimentacion");
            throw new RuntimeException("Error al guardar el registroalimentacion", e);
        }
    }

    public Registroalimentacion transformDtoToEntityToCreate(RegistroalimentacionDto registroalimentacionDto, Integer idBebe) {
        try {
            Registroalimentacion registroalimentacion = new Registroalimentacion();
            registroalimentacion.setFecha(registroalimentacionDto.getFecha());
            registroalimentacion.setTipocomida(registroalimentacionDto.getTipocomida());
            registroalimentacion.setIdBebe(new Bebe(idBebe));
            return registroalimentacion;
        } catch (Exception e) {
            System.out.println("Error al transformar el registroalimentacionDto a entidad");
            throw new RuntimeException("Error al transformar el registroalimentacionDto a entidad", e);
        }
    }


    public RegistroalimentacionDto transformEntityToDto(Registroalimentacion registroalimentacion) {
       try{
        BebeDto bebeDto = convertitrBebeToDto(registroalimentacion.getIdBebe());
        RegistroalimentacionDto registroalimentacionDto = new RegistroalimentacionDto();
        registroalimentacionDto.setIdRegistroalimentacion(registroalimentacion.getIdRegistroalimentacion());
        registroalimentacionDto.setFecha(registroalimentacion.getFecha());
        registroalimentacionDto.setTipocomida(registroalimentacion.getTipocomida());
        registroalimentacionDto.setIdBebe(bebeDto);
        return registroalimentacionDto;
       }catch(Exception e){
        System.out.println("Error al transformar la entidad registroalimentacionDto a DTO");
        throw new RuntimeException("Error al transformar la entidad registroalimentacionDto a DTO", e);

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
