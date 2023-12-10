package com.horou.Tis.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.horou.Tis.Models.Orcamento;
import com.horou.Tis.Services.OrcamentoServices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/Orcamentos")
@Validated
public class OrcamentoController {

    @Autowired
    private OrcamentoServices orcamentoServices;

    @GetMapping("/all")
    public ResponseEntity<List<Orcamento>> findAll(){
        List<Orcamento> o=this.orcamentoServices.findAll();
        return ResponseEntity.ok().body(o);
        
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> findById(@PathVariable Integer id){
        Orcamento o = this.orcamentoServices.findById(id);
        return ResponseEntity.ok().body(o);

    }
    @PostMapping("/Create")
    public ResponseEntity<Orcamento> create(@RequestBody Orcamento obj){
        obj=this.orcamentoServices.create(obj);
        URI uri=ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(obj.getIdorcamento()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

   @PutMapping("/{id}")
   public ResponseEntity<Void> update(@RequestBody Orcamento obj,@PathVariable Integer id){
    obj.setIdorcamento(id);
    this.orcamentoServices.update(obj);
    return ResponseEntity.noContent().build();
   }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.orcamentoServices.delete(id);
        return ResponseEntity.noContent().build();
   }

   @GetMapping("/cliente/{clienteid}")
    public ResponseEntity<List<Orcamento>> findOrcamentosByCliente(@PathVariable Integer clienteid) {
        List<Orcamento> orcamentos = orcamentoServices.findOrcamentosByCliente(clienteid);
        return ResponseEntity.ok().body(orcamentos);
    }
}
