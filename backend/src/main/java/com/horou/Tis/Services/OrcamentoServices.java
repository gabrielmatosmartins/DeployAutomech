package com.horou.Tis.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horou.Tis.Models.Orcamento;
import com.horou.Tis.Models.Veiculo;
import com.horou.Tis.Repositories.OrcamentoRepository;
import com.horou.Tis.Repositories.VeiculoRepository;

import jakarta.transaction.Transactional;

@Service
public class OrcamentoServices {
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Orcamento> findAll(){
        List<Orcamento> o= this.orcamentoRepository.findAll();
        return o;
    }
    
    public Orcamento findById(Integer id){
        Optional<Orcamento> o = this.orcamentoRepository.findById(id);
        return o.orElseThrow(()-> new RuntimeException("Não foi possivel encontrar o usuario por este ID"));
    }
    
    @Transactional
    public Orcamento create(Orcamento obj){
        Veiculo v= this.veiculoRepository.findById(obj.getVeiculo().getId())
        .orElseThrow(()-> new RuntimeException("Veiculo nao encontrado"));
        obj.setVeiculo(v);
        obj.setDescricaoorcamento(obj.getDescricaoorcamento());
        obj.setValororcamento(obj.getValororcamento());
        obj.setStatusorcamento(obj.getStatusorcamento());
        obj.setDataorcamento(obj.getDataorcamento());
        obj.setIdorcamento(obj.getIdorcamento());
        obj=this.orcamentoRepository.save(obj);
        return obj;
    }

    @Transactional
    public Orcamento update(Orcamento obj){
        Orcamento newObj=findById(obj.getIdorcamento());
        newObj.setDescricaoorcamento(obj.getDescricaoorcamento());
        newObj.setStatusorcamento(obj.getStatusorcamento());
        newObj.setValororcamento(obj.getValororcamento());
        newObj.setDataorcamento(obj.getDataorcamento());
        return this.orcamentoRepository.save(newObj);
    }
    
    public void delete(Integer id){
     Orcamento o= findById(id);
     try {
        this.orcamentoRepository.delete(o);
     } catch (Exception e) {
        throw new RuntimeException("Não é possivel excluir pois o orcamento esta relacionado com mais entidades");
     }
    }

    public List<Orcamento> findOrcamentosByCliente(Integer clienteid) {
        return orcamentoRepository.findOrcamentosByCliente(clienteid);
    }

}
