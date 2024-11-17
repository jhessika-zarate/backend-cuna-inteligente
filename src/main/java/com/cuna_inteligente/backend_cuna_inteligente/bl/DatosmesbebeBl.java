package com.cuna_inteligente.backend_cuna_inteligente.bl;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;

import com.cuna_inteligente.backend_cuna_inteligente.dao.DatosmesbebeRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.DatosmesbebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Datosmesbebe;

@Service
public class DatosmesbebeBl {
    private final DatosmesbebeRepository datosmesbebeRepository;
    private final BebeBl bebeBl; // Inyecta la dependencia de BebeBl

    public DatosmesbebeBl(DatosmesbebeRepository datosmesbebeRepository, BebeBl bebeBl) {
        this.datosmesbebeRepository = datosmesbebeRepository;
        this.bebeBl = bebeBl;  // Asigna la instancia de BebeBl
    }

    public DatosmesbebeDto findById(Integer id) {
        Datosmesbebe datosmesbebe = datosmesbebeRepository.findById(id).orElse(null);
        return datosmesbebe != null ? transformEntityToDto(datosmesbebe) : null;
    }

    public List<DatosmesbebeDto> findDatosMesBebe(Integer idBebe) {
        List<Datosmesbebe> datos = datosmesbebeRepository.findDatosMesBebe(idBebe);
        List<DatosmesbebeDto> datosDtos = new ArrayList<>();
        for (Datosmesbebe dato : datos) {
            datosDtos.add(transformEntityToDto(dato));
        }
        return datosDtos;
    }

    public DatosmesbebeDto save(DatosmesbebeDto datosmesbebeDto, Integer idBebe) {
        try {
            // Ahora usamos la instancia inyectada de BebeBl
            if (bebeBl.findById(idBebe) == null) {
                throw new RuntimeException("No se encontro el bebe");
            }

            Datosmesbebe datosmesbebe = transformDtoToEntityToCreate(datosmesbebeDto, idBebe);
            datosmesbebe = datosmesbebeRepository.save(datosmesbebe);
            return transformEntityToDto(datosmesbebe);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar los datos del mes del bebe", e);
        }
    }
    private Datosmesbebe transformDtoToEntityToCreate(DatosmesbebeDto datosmesbebeDto, Integer idBebe) {
        ;
           try {
              Datosmesbebe datosmesbebe = new Datosmesbebe();
              datosmesbebe.setFecha(datosmesbebeDto.getFecha());
              datosmesbebe.setPeso(datosmesbebeDto.getPeso());
              datosmesbebe.setAltura(datosmesbebeDto.getAltura());
              datosmesbebe.setFecha(datosmesbebeDto.getFecha());
              datosmesbebe.setIdBebe(new Bebe(idBebe));
              return datosmesbebe;
          } catch (Exception e) {
              e.printStackTrace();
              throw new RuntimeException("Error al transformar el dto a entidad", e);
          }
      }


 

    private DatosmesbebeDto transformEntityToDto(Datosmesbebe datosmesbebe) {
        try {
            BebeDto bebeDto = convertitrBebeToDto(datosmesbebe.getIdBebe());
            DatosmesbebeDto datosmesbebeDto = new DatosmesbebeDto();
            datosmesbebeDto.setIdRegistrocaracteristicas(datosmesbebe.getIdRegistrocaracteristicas());
            datosmesbebeDto.setPeso(datosmesbebe.getPeso());
            datosmesbebeDto.setAltura(datosmesbebe.getAltura());
            datosmesbebeDto.setFecha(datosmesbebe.getFecha());
            datosmesbebeDto.setIdBebe(bebeDto);
            return datosmesbebeDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al transformar la entidad a dto", e);
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
