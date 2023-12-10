package com.horou.Tis.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horou.Tis.Models.Oficina;
import com.horou.Tis.Models.Reparo;
import com.horou.Tis.Repositories.ReparoRepository;

import jakarta.transaction.Transactional;

@Service
public class ReparoService {
    
    @Autowired
    private ReparoRepository reparoRepository;

    public Reparo findById(Integer id){
        Optional<Reparo> reparo = this.reparoRepository.findById(id);
        return reparo.orElseThrow(() -> new RuntimeException("Reparo não encontrado - id: "+id+", Tipo: " + Reparo.class.getName()));
    }

    public List<Reparo> findByOrcamento(Integer idOrcamento){
        Optional<List<Reparo>> reparo = this.reparoRepository.findByOrcamento(idOrcamento);
        return reparo.orElseThrow(() -> new RuntimeException("Nenhum orçamento encontrada, Tipo: " + Oficina.class.getName()));
    }

    @Transactional
    public int endRepair(List<Integer> id){
        LocalDate dataAtual = LocalDate.now();
        for(Integer i : id){
            Reparo reparo = findById(i);
            reparo.setStatusreparo(2);
            reparo.setDatareparo(dataAtual);
        }
        return this.reparoRepository.endRepair(id, dataAtual);

    }

    /**
     * Retorna a quantidade de reparos feitos dentro do prazo e caso não exista, retorna 0;
     *
     * @return double quantidade de reparos ou 0;
    */
    public double retornarReparosNoPrazo(){
        Optional<Double> qtdReparos = this.reparoRepository.retornarReparosNoPrazo();
        return qtdReparos.orElse(0.0);
    }

    /**
     * Retorna a quantidade total de reparos no sistema.
     *
     * @return Lista de Reparo;
    */
    public List<Reparo> returnAll(List<Integer> status){
        return this.reparoRepository.returnAll(status);
    }

}
