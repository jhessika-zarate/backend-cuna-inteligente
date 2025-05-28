package com.cuna_inteligente.backend_cuna_inteligente.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import org.springframework.stereotype.Service;
import com.cuna_inteligente.backend_cuna_inteligente.dao.RegistrovacunaRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.RegistrovacunaDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Registrovacuna;



@Service
public class RegistrovacunaBl {
    private final RegistrovacunaRepository RegistrovacunaRepository;

    private final BebeBl bebeBl; // Inyecta la dependencia de BebeBl

    public RegistrovacunaBl(RegistrovacunaRepository RegistrovacunaRepository, BebeBl bebeBl) {
        this.RegistrovacunaRepository = RegistrovacunaRepository;
        this.bebeBl = bebeBl;  // Asigna la instancia de BebeBl
    }

    public RegistrovacunaDto findById(Integer id) {
        Registrovacuna registrovacuna = RegistrovacunaRepository.findById(id).orElse(null);
        return registrovacuna != null ? transformEntityToDto(registrovacuna) : null;
    }

    public List<RegistrovacunaDto> findRegistroVacunaByBebe(Integer idBebe) {
        List<Registrovacuna> registros = RegistrovacunaRepository.findRegistroVacunaByBebe(idBebe);
        List<RegistrovacunaDto> registroDtos = new ArrayList<>();
        for (Registrovacuna registro : registros) {
            registroDtos.add(transformEntityToDto(registro));
        }
        return registroDtos;
    }

    public RegistrovacunaDto save(RegistrovacunaDto registrovacunaDto, Integer idBebe) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }
            Registrovacuna registrovacuna = transformDtoToEntityToCreate(registrovacunaDto, idBebe);
            return transformEntityToDto(RegistrovacunaRepository.save(registrovacuna));
        } catch (Exception e) {
            System.out.println("Error al guardar el registrovacuna");
            throw new RuntimeException("Error al guardar el registrovacuna", e);
        }
    }

    public Registrovacuna transformDtoToEntityToCreate(RegistrovacunaDto registrovacunaDto, Integer idBebe) {
        try {
            Registrovacuna registrovacuna = new Registrovacuna();
            registrovacuna.setIdVacuna(registrovacunaDto.getIdVacuna());
            registrovacuna.setFechaVacuna(registrovacunaDto.getFechaVacuna());
            registrovacuna.setNombreVacuna(registrovacunaDto.getNombreVacuna());
            registrovacuna.setDosis(registrovacunaDto.getDosis());
            registrovacuna.setCentroSalud(registrovacunaDto.getCentroSalud());
            registrovacuna.setIdBebe(new Bebe(idBebe));
            return registrovacuna;
        } catch (Exception e) {
            System.out.println("Error al transformar el registrovacuna a entity");
            throw new RuntimeException("Error al transformar el registrovacuna a entity", e);
        }
    }

    public  RegistrovacunaDto transformEntityToDto(Registrovacuna registrovacuna) {
        try{
            BebeDto bebeDto = convertitrBebeToDto(registrovacuna.getIdBebe());
            RegistrovacunaDto registrovacunaDto = new RegistrovacunaDto();
            registrovacunaDto.setIdVacuna(registrovacuna.getIdVacuna());
            registrovacunaDto.setFechaVacuna(registrovacuna.getFechaVacuna());
            registrovacunaDto.setNombreVacuna(registrovacuna.getNombreVacuna());
            registrovacunaDto.setDosis(registrovacuna.getDosis());  
            registrovacunaDto.setCentroSalud(registrovacuna.getCentroSalud());  
            registrovacunaDto.setIdBebe(bebeDto);
            return registrovacunaDto;
           
        }catch(Exception e){
            System.out.println("Error al transformar el registrovacuna a dto");
            throw new RuntimeException("Error al transformar el registrovacuna a dto", e);

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

