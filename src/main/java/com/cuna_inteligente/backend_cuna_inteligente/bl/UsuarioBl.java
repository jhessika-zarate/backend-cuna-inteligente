package com.cuna_inteligente.backend_cuna_inteligente.bl;
import com.cuna_inteligente.backend_cuna_inteligente.dao.UsuarioRepository;

import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.UsuarioDto;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioBl {
    private final UsuarioRepository usuarioRepository;

    public  UsuarioBl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioDto createUser(UsuarioDto newUser){
        try {
            if(usuarioRepository.findByGmail(newUser.getGmail()) != null){
                return null;
            }

            Usuario user = new Usuario();
            user.setUsername(newUser.getUsername());
            user.setGmail(newUser.getGmail());
            user.setContrasenia(newUser.getContrasenia());
            return new UsuarioDto(usuarioRepository.save(user));
    }catch (Exception e){
        System.out.println("no se pudo crear"+e);
        return null;
    }
    }

   public List<UsuarioDto> findAllUser(){
    List <UsuarioDto> usuariosdDtos = new ArrayList<>();
    List<Usuario> users = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "idUsuario"));
    for (Usuario user : users){
        usuariosdDtos.add(transformEntityToDto(user));
    }
    return usuariosdDtos;

   }

   private UsuarioDto transformEntityToDto(Usuario user){
       UsuarioDto usuarioDto = new UsuarioDto();
       usuarioDto.setIdUsuario(user.getIdUsuario());
       usuarioDto.setUsername(user.getUsername());
       usuarioDto.setGmail(user.getGmail());
       return usuarioDto;
   }

        


    public  String circo (){
        return "circo";
    }

}
