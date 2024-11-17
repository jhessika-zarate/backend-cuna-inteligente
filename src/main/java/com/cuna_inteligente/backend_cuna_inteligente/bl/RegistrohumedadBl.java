package com.cuna_inteligente.backend_cuna_inteligente.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.dao.RegistrohumedadRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrohumedadDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrohumedad;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.bl.BebeBl;
import java.sql.Timestamp;


@Service
public class RegistrohumedadBl {
    private final RegistrohumedadRepository registrohumedadRepository;

    private final BebeBl bebeBl; // Inyecta la dependencia de BebeBl

    public RegistrohumedadBl(RegistrohumedadRepository registrohumedadRepository, BebeBl bebeBl) {
        this.registrohumedadRepository = registrohumedadRepository;
        this.bebeBl = bebeBl;  // Asigna la instancia de BebeBl
    }
    
    public RegistrohumedadDto findById(Integer id) {
        Registrohumedad registrohumedad = registrohumedadRepository.findById(id).orElse(null);
        return registrohumedad != null ? transformEntityToDto(registrohumedad) : null;
    }

    public List<RegistrohumedadDto> findRegistroHumedadByBebe(Integer idBebe) {
        List<Registrohumedad> registros = registrohumedadRepository.findRegistroHumedadByBebe(idBebe);
        List<RegistrohumedadDto> registroDtos = new ArrayList<>();
        for (Registrohumedad registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }
     //obtener temperatura de todos los bebes del usuario no da
    public List<RegistrohumedadDto> findRegistroHumedadByUsuario(Integer idUsuario) {
        List<Registrohumedad> registros = registrohumedadRepository.findRegistroHumedadByUsuario(idUsuario);
        List<RegistrohumedadDto> registroDtos = new ArrayList<>();
        for (Registrohumedad registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }

    public RegistrohumedadDto save(RegistrohumedadDto registrohumedadDto, Integer idBebe) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }

            Registrohumedad registrohumedad = transformDtoToEntityToCreate(registrohumedadDto, idBebe);
            registrohumedad = registrohumedadRepository.save(registrohumedad);
            return transformEntityToDto(registrohumedad);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el registro de humedad", e);
        }
    }

    public RegistrohumedadDto saveTodo( Integer idBebe, BigDecimal humedad) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }

            Registrohumedad registrohumedad = transformDtoToEntityToCreatePrueba( idBebe, humedad);
            registrohumedad = registrohumedadRepository.save(registrohumedad);
            return transformEntityToDto(registrohumedad);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el registro de humedad", e);
        }
    }


    public Registrohumedad transformDtoToEntityToCreate(RegistrohumedadDto registrohumedadDto, Integer idBebe) {
        try{
            Registrohumedad registrohumedad = new Registrohumedad();
            registrohumedad.setHumedad(registrohumedadDto.getHumedad());
            registrohumedad.setFecha(registrohumedadDto.getFecha());
            registrohumedad.setIdBebe(new Bebe(idBebe));
            return registrohumedad;
        }catch(Exception e){
            System.out.println("Error al transformar el DTO de Registrohumedad a entidad");
            throw new RuntimeException("Error al transformar el DTO de Registrohumedad a entidad", e);
        }
            
    }
    public Registrohumedad transformDtoToEntityToCreatePrueba(Integer idBebe, BigDecimal temperatura) {
        try{
            Registrohumedad registrohumedad = new Registrohumedad();
            registrohumedad.setHumedad(temperatura);
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            registrohumedad.setFecha(fechaActual); // Esto asigna la fecha y hora actuales    
            registrohumedad.setIdBebe(new Bebe(idBebe));
            return registrohumedad;
        }catch(Exception e){
            System.out.println("Error al transformar el DTO de Registrohumedad a entidad");
            throw new RuntimeException("Error al transformar el DTO de Registrohumedad a entidad", e);
        }
            
    }

    public RegistrohumedadDto updateRegistrohumedad(RegistrohumedadDto registrohumedadDto, Integer id) {
        try {
            Registrohumedad registrohumedad = registrohumedadRepository.findById(id).orElse(null);
            if (registrohumedad == null) {
                throw new RuntimeException("No se encontro el registro de humedad");
            }

            registrohumedad.setHumedad(registrohumedadDto.getHumedad());
            registrohumedad.setFecha(registrohumedadDto.getFecha());
            registrohumedad.setIdBebe(new Bebe(registrohumedadDto.getIdBebe().getIdBebe()));
            registrohumedad = registrohumedadRepository.save(registrohumedad);
            return transformEntityToDto(registrohumedad);
        } catch (Exception e) {
            System.out.println("Error al actualizar el registro de humedad");
            throw new RuntimeException("Error al actualizar el registro de humedad", e);
        }
        
    }
        


    public RegistrohumedadDto transformEntityToDto(Registrohumedad registrohumedad) {
        try{
            BebeDto bebeDto = convertitrBebeToDto(registrohumedad.getIdBebe());
            RegistrohumedadDto registrohumedadDto = new RegistrohumedadDto();
            registrohumedadDto.setIdRegistrohumedad(registrohumedad.getIdRegistrohumedad());
            registrohumedadDto.setHumedad(registrohumedad.getHumedad());
            registrohumedadDto.setFecha(registrohumedad.getFecha());
            registrohumedadDto.setIdBebe(bebeDto);

            return registrohumedadDto;
        }catch(Exception e){
            System.out.println("Error al transformar la entidad Registrohumedad a DTO");
            throw new RuntimeException("Error al transformar la entidad Registrohumedad a DTO", e);

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
