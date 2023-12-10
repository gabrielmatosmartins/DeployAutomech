package com.horou.Tis.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.horou.Tis.Models.Usuario;
import com.horou.Tis.Repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        List<Usuario> u = this.usuarioRepository.findAll();
        return u;
    }

    public Usuario findById(Integer id){
        Optional<Usuario> u = this.usuarioRepository.findById(id);
        return u.orElseThrow(() -> new RuntimeException(
            "Usuario não encontrado na base de dados"
        ));
    }

    @Transactional
    public Usuario create(Usuario u){
        u.setId(null);
        Usuario u1 = u;
        u = this.usuarioRepository.saveAndFlush(u1);
        u.getId();
        return u;
    }

    @Transactional
    public Usuario update(Usuario u){
        Usuario newUsuario = findById(u.getId());
        newUsuario.setNome(u.getNome());
        newUsuario.setCpf(u.getCpf());

        return this.usuarioRepository.save(newUsuario);
    }

    public void delete(Integer id){
        Usuario u = findById(id);
        try{
            this.usuarioRepository.deleteById(u.getId());
        }catch(Exception e){
            throw new RuntimeException("Não é possível excluir, pois possui entidades relacionadas");
        }
    }
}
