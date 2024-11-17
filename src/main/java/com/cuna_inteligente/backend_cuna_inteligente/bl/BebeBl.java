package com.cuna_inteligente.backend_cuna_inteligente.bl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cuna_inteligente.backend_cuna_inteligente.dao.BebeRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.BebeDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Bebe;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;

@Service
public class BebeBl {
    private final BebeRepository bebeRepository;

    public BebeBl(BebeRepository bebeRepository) {
        this.bebeRepository = bebeRepository;
    }

    public BebeDto findById(Integer id) {
        Bebe bebe = bebeRepository.findById(id).orElse(null);
        return bebe != null ? transformEntityToDto(bebe) : null;
    }
    

    public List<BebeDto> findBebeAll() {
        List<Bebe> bebes = bebeRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
        List<BebeDto> bebeDtos = new ArrayList<>();
        for (Bebe bebe : bebes) {
            bebeDtos.add(transformEntityToDto(bebe));
        }
        return bebeDtos;
    }

    public BebeDto findBebeById(Integer id) {
        Bebe bebe = bebeRepository.findById(id).orElse(null);
        return bebe != null ? transformEntityToDto(bebe) : null;
    }

    public List<BebeDto> findBebeByUsuario(Integer idUsuario) {
        List<Bebe> bebes = bebeRepository.findBebesByUsuario(idUsuario);
        List<BebeDto> bebeDtos = new ArrayList<>();
        for (Bebe bebe : bebes) {
            bebeDtos.add(transformEntityToDto(bebe));
        }
        return bebeDtos;
    }

    public BebeDto findSelectedBebe() {
        Bebe bebe = bebeRepository.findSelectedBebe().stream().findFirst().orElse(null);
        return bebe != null ? transformEntityToDto(bebe) : null;
    }

    public BebeDto save(BebeDto bebeDto, Integer idUsuario) {
        try {
            Bebe bebe = transformDtoToEntityToCreate(bebeDto);
            bebe = bebeRepository.save(bebe);
            return transformEntityToDto(bebe);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo guardar el bebé", e);
        }
    }

  
    public Bebe transformDtoToEntityToCreate(BebeDto bebeDto) {
        Bebe bebe = new Bebe();
        bebe.setNombre(bebeDto.getNombre());
        bebe.setApellidopaterno(bebeDto.getApellidopaterno());
        bebe.setApellidomaterno(bebeDto.getApellidomaterno());
        bebe.setSeleccionado(false);
        bebe.setFechadenacimiento(bebeDto.getFechadenacimiento());
        bebe.setColor(bebeDto.getColor());
        bebe.setMovimiento(false);
        bebe.setIdUsuario(new Usuario(bebeDto.getIdUsuario().getIdUsuario()));
        return bebe;
    }

    public BebeDto updateBebe(BebeDto bebeDto) {
        try {
            Bebe bebe = bebeRepository.findById(bebeDto.getIdBebe()).orElse(null);
            if (bebe == null) {
                throw new RuntimeException("El bebé con ID " + bebeDto.getIdBebe() + " no existe");
            }
            
            System.out.println(String.format("Bebe antes de la actualización: %s", bebe));
            
            // Actualización de los campos
            bebe.setApellidomaterno(bebeDto.getApellidomaterno());
            bebe.setApellidopaterno(bebeDto.getApellidopaterno());
            bebe.setColor(bebeDto.getColor());
            bebe.setFechadenacimiento(bebeDto.getFechadenacimiento());
            bebe.setNombre(bebeDto.getNombre());
            bebe.setSeleccionado(bebeDto.getSeleccionado());
            bebe.setMovimiento(bebeDto.getMovimiento());
            bebe = bebeRepository.save(bebe);
    
            System.out.println(String.format("Bebe después de la actualización: %s", bebe));
    
            return transformEntityToDto(bebe);
        } catch (Exception e) {
            System.out.println(String.format("Error al actualizar el bebé con ID %d: %s", bebeDto.getIdBebe(), e.getMessage()));
            throw new RuntimeException("No se pudo actualizar el bebé", e);
        }
    }

    //quiero una funcion put, donde te den el id del bebe y Seleccionado se ponga en true y los demas en false
    public BebeDto updateSelectedBebe(Integer idBebe) {
        try {
            List<Bebe> bebes = bebeRepository.findAll();
            for (Bebe bebe : bebes) {
                if (bebe.getIdBebe() == idBebe) {
                    bebe.setSeleccionado(true);
                } else {
                    bebe.setSeleccionado(false);
                }
                bebeRepository.save(bebe);
            }
            return transformEntityToDto(bebeRepository.findById(idBebe).orElse(null));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el bebé seleccionado", e);
        }
    }
    //quiero una funcion put, donde pongas a todos los bebes en movimiento false, y al bebe seleccionado en true
    public BebeDto updateMovimientoBebeTrue() {
        try {
            List<Bebe> bebes = bebeRepository.findAll();
            for (Bebe bebe : bebes) {
                if (bebe.getSeleccionado()) {
                    bebe.setMovimiento(true);
                } else {
                    bebe.setMovimiento(false);
                }
                bebeRepository.save(bebe);
            }
            return transformEntityToDto(bebeRepository.findSelectedBebe().stream().findFirst().orElse(null));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el bebé seleccionado", e);
        }
    }

    //quiero una funcion put, donde pongas al bebe seleccionado en false
    public BebeDto updateMovimientoBebeFalse() {
        try {
            Bebe bebe = bebeRepository.findSelectedBebe().stream().findFirst().orElse(null);
            if (bebe != null) {
                bebe.setMovimiento(false);
                bebeRepository.save(bebe);
            }
            return transformEntityToDto(bebe);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el bebé seleccionado", e);
        }
    }
    

     //quiero una funcion put, donde te den el id del usuario y pongas a todos sus bebes en movimiento en true y los demas false y todos los demas en false
        public BebeDto updateMovimientoBebe(Integer idUsuario) {
            try {
                List<Bebe> bebes = bebeRepository.findAll();
                for (Bebe bebe : bebes) {
                    if (bebe.getIdUsuario().getIdUsuario() == idUsuario) {
                        bebe.setMovimiento(true);
                  
                    } else {
                        bebe.setMovimiento(false);
                    }
                    bebeRepository.save(bebe);
                }
                return transformEntityToDto(bebeRepository.findById(idUsuario).
                orElse(null));
            } catch (Exception e) {
                throw new RuntimeException("No se pudo actualizar el bebé seleccionado", e);
            }
        }



    public BebeDto transformEntityToDto(Bebe bebe) {
        try {
            UsuarioDto usuarioDto = convertitrUsuarioToDto(bebe.getIdUsuario());
            BebeDto bebeDto = new BebeDto();
            bebeDto.setIdBebe(bebe.getIdBebe());
            bebeDto.setNombre(bebe.getNombre());
            bebeDto.setApellidopaterno(bebe.getApellidopaterno());
            bebeDto.setApellidomaterno(bebe.getApellidomaterno());
            bebeDto.setSeleccionado(bebe.getSeleccionado());
            bebeDto.setFechadenacimiento(bebe.getFechadenacimiento());
            bebeDto.setColor(bebe.getColor());
            bebeDto.setIdUsuario(usuarioDto);
            bebeDto.setMovimiento(bebe.getMovimiento());
            System.out.println(String.format("BebeDto transformado: %s", bebeDto));
    
            return bebeDto;
        } catch (Exception e) {
            System.out.println("Error al transformar la entidad Bebe a DTO");
            throw new RuntimeException("Error al transformar la entidad Bebe a DTO", e);
        }
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

    public BebeDto convertBebeToDto(Bebe bebe) {
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

    public List<BebeDto> findAll() {
        List<Bebe> bebes = bebeRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
        List<BebeDto> bebeDtos = new ArrayList<>();
        for (Bebe bebe : bebes) {
            bebeDtos.add(convertBebeToDto(bebe));
        }
        return bebeDtos;
    }
}
