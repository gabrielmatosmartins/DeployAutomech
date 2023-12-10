package com.horou.Tis.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.horou.Tis.Models.Funcionario;
import com.horou.Tis.Models.Usuario;
import com.horou.Tis.Repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Funcionario findById(Integer id){
        Optional<Funcionario> f = this.funcionarioRepository.findById(id);
        return f.orElseThrow(() -> new RuntimeException(
            "Funcionario não encontrado"
        ));
    }

    public List<Funcionario> getAll(){
        List<Funcionario> f = this.funcionarioRepository.findAll();
        return f;
    }

    public Funcionario findByUserId(Integer id){
        Optional<Funcionario> f = this.funcionarioRepository.findByUsuario_Id(id);
        return f.orElseThrow(() -> new RuntimeException(
            "Funcionario não encontrado"
        ));
    }

    @Transactional
    public Funcionario create(Funcionario f){
        Usuario u = this.usuarioService.findById(f.getUserId());

        // f.setId(null);
        f.setUsuario(u);
        f = this.funcionarioRepository.save(f);
        return f;
    }

    @Transactional
    public Funcionario update(Funcionario f){
        Funcionario newFuncionario = findByUserId(f.getUserId());
        newFuncionario.setSalario(f.getSalario());
        return this.funcionarioRepository.save(newFuncionario);
    }

    public void delete(Integer id){
        Funcionario f = findByUserId(id);

        try{
            this.funcionarioRepository.deleteById(f.getUserId());
        }catch(Exception e){
            throw new RuntimeException("Não é possível excluir, pois possui entidades relacionadas");
        }
    }
}
