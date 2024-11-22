package com.cuna_inteligente.backend_cuna_inteligente.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.dao.RegistrollantoRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrollantoDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrollanto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.bl.BebeBl;
import java.sql.Timestamp;

@Service
public class RegistrollantoBl {
    private final RegistrollantoRepository RegistrollantoRepository;

    private final BebeBl bebeBl; // Inyecta la dependencia de BebeBl

    public RegistrollantoBl(RegistrollantoRepository RegistrollantoRepository, BebeBl bebeBl) {
        this.RegistrollantoRepository = RegistrollantoRepository;
        this.bebeBl = bebeBl;  // Asigna la instancia de BebeBl
    }

    public RegistrollantoDto findById(Integer id) {
        Registrollanto registrollanto = RegistrollantoRepository.findById(id).orElse(null);
        return registrollanto != null ? transformEntityToDto(registrollanto) : null;
    }

    public List<RegistrollantoDto> findRegistrollantoByBebe(Integer idBebe) {
        List<Registrollanto> registros = RegistrollantoRepository.findRegistrollantoByBebe(idBebe);
        List<RegistrollantoDto> registroDtos = new ArrayList<>();
        for (Registrollanto registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }

    public RegistrollantoDto save(RegistrollantoDto registrollantoDto, Integer idBebe) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }
            Registrollanto registrollanto = transformDtoToEntityToCreate(registrollantoDto, idBebe);
            return transformEntityToDto(RegistrollantoRepository.save(registrollanto));
        } catch (Exception e) {
            System.out.println("Error al guardar el registrollanto");
            throw new RuntimeException("Error al guardar el registrollanto", e);
        }
    }

    public Registrollanto transformDtoToEntityToCreate (RegistrollantoDto registrollantoDto, Integer idBebe) {
        try {
            Registrollanto registrollanto = new Registrollanto();
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            registrollanto.setFecha(fechaActual);
            registrollanto.setRazon(registrollantoDto.getRazon());
            registrollanto.setIdBebe(new Bebe(idBebe));
            return registrollanto;
        } catch (Exception e) {
            System.out.println("Error al transformar el registrollanto a entity");
            throw new RuntimeException("Error al transformar el registrollanto a entity", e);
        }
    }
    


    public RegistrollantoDto transformEntityToDto(Registrollanto registrollanto) {
        try{
            BebeDto bebeDto = convertitrBebeToDto(registrollanto.getIdBebe());
            RegistrollantoDto registrollantoDto = new RegistrollantoDto();
            registrollantoDto.setIdRegistrollanto(registrollanto.getIdRegistrollanto());
            registrollantoDto.setFecha(registrollanto.getFecha());
            registrollantoDto.setRazon(registrollanto.getRazon());
            registrollantoDto.setIdBebe(bebeDto);
            return registrollantoDto;
        }catch(Exception e){
            System.out.println("Error al transformar el registrollanto a dto");
            throw new RuntimeException("Error al transformar el registrollanto a dto", e);

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