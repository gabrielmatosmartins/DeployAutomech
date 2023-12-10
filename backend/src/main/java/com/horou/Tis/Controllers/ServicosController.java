package com.horou.Tis.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.horou.Tis.Models.Servicos;
import com.horou.Tis.Services.ServicoServices;


@RestController
@RequestMapping("/Servicos")
@Validated
public class ServicosController {
    
    @Autowired
    private ServicoServices servicoServices;

    @GetMapping("/all")
    public ResponseEntity<List<Servicos>> findAll(){
        List<Servicos> s= this.servicoServices.findAll();
        return ResponseEntity.ok().body(s);
    }

    @GetMapping("/{id}")
   public ResponseEntity<Servicos> findById(@PathVariable Integer id){
    Servicos s=this.servicoServices.findById(id);
    return ResponseEntity.ok().body(s);
   }
   @PostMapping
   public ResponseEntity<Servicos> create(@RequestBody Servicos obj){
    obj=this.servicoServices.create(obj);
    URI uri=ServletUriComponentsBuilder.fromCurrentRequest().
    path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).body(obj);
   }
   @PutMapping("/{id}")
   public ResponseEntity<Void> update(@RequestBody Servicos obj,@PathVariable Integer id){
    obj.setId(id);
    this.servicoServices.update(obj);
    return ResponseEntity.noContent().build();
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.servicoServices.delete(id);
        return ResponseEntity.noContent().build();

    
   }
    
}
