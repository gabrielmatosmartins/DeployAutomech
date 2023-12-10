package com.horou.Tis.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horou.Tis.Models.Oficina;
import com.horou.Tis.Repositories.OficinaRepository;

import jakarta.transaction.Transactional;

@Service
public class OficinaService {
    
    @Autowired
    private OficinaRepository oficinaRepository;

    public Oficina findById(Integer id){
        Optional<Oficina> oficina = this.oficinaRepository.findById(id);
        return oficina.orElseThrow(() -> new RuntimeException("Oficina não encontrado - id: "+id+", Tipo: " + Oficina.class.getName()));
    }

    @Transactional
    public Oficina create(Oficina objModel){
        objModel.setId(null);
        objModel = this.oficinaRepository.save(objModel);
        return objModel;
    }

    @Transactional
    public Oficina update(Oficina objModel){
        Oficina oficina = findById(objModel.getId());
        oficina.setCnpj(objModel.getCnpj());
        oficina.setNome(objModel.getNome());
        oficina.setQtdfuncionarios(objModel.getQtdfuncionarios());
        oficina.setTelefone(objModel.getTelefone());
        oficina.setEmail(objModel.getEmail());
        oficina.setTemsite(objModel.getTemsite());
        oficina.setEndereco(objModel.getEndereco());
        oficina.setDescricao(objModel.getDescricao());
        oficina.setHorarioFuncionamento(objModel.gethorarioFuncionamento());
        oficina.setDataInclusao(objModel.getDataInclusao());
        return this.oficinaRepository.save(oficina);
    }

    public void delete(Integer id){
        try {
            this.oficinaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois existem entidades relacionadas");
        }
    }

    public List<Oficina> findByName(String nome){
        Optional<List<Oficina>> oficina = this.oficinaRepository.findByNome(nome);
        return oficina.orElseThrow(() -> new RuntimeException("Nenhuma oficina encontrada, Tipo: " + Oficina.class.getName()));
    }

    public List<String> returnEspecialidades(Integer id){
        List<String> especialidades = this.oficinaRepository.returnEspecialidades(id);
        return especialidades;
    }

    public List<Oficina> findByEspecialidade(String nome){
        Optional<List<Oficina>> oficina = this.oficinaRepository.findByEspecialidade(nome);
        return oficina.orElseThrow(() -> new RuntimeException("Nenhuma oficina encontrada, Tipo: " + Oficina.class.getName()));
    }

}
