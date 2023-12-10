package com.horou.Tis.Controllers;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.horou.Tis.Models.Cliente;
import com.horou.Tis.Models.Reparo;
import com.horou.Tis.Services.ReparoService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/Reparo")
@Validated
public class ReparoController {
    
    @Autowired
    private ReparoService reparoService;

    @GetMapping("/Detalhes/{id}")
    public ResponseEntity<Reparo> findById(@PathVariable Integer id){
        Reparo objModel = this.reparoService.findById(id);
        return ResponseEntity.ok().body(objModel);
    }

    @GetMapping("/Pesquisar")
    public ResponseEntity<List<Reparo>> findByOrcamento(@RequestParam Integer idOrcamento){
        List<Reparo> objModel = this.reparoService.findByOrcamento(idOrcamento);
        return ResponseEntity.ok().body(objModel);
    }

    public Integer retornarTotalReparos(List<Integer> status){
        List<Reparo> totalReparos = this.reparoService.returnAll(status);
        return totalReparos.size();
    }

    @PostMapping("/FinalizarReparo")
    public boolean endRepair(@RequestParam List<Integer> idReparo)
    throws MessagingException{
        int updateSuccess = this.reparoService.endRepair(idReparo);
        return updateSuccess > 0;
    }

    @GetMapping(value ="/Indicadores")
    public String reparosNoPrazo(){
        List<Integer> status = List.of(1,2);
        double reparosDentroDoPrazo = this.reparoService.retornarReparosNoPrazo();
        int totalReparos = retornarTotalReparos(status);
        if(reparosDentroDoPrazo == 0){
            return "0%";
        }else{
            double reparos = (reparosDentroDoPrazo/totalReparos);
            String textReturn = "{\"valor\":" + reparos*100  + "}";
            return textReturn;
        }   
    }

}
