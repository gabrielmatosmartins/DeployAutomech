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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.horou.Tis.Models.Oficina;
import com.horou.Tis.Services.OficinaService;

@RestController
@RequestMapping("/Oficina")
@Validated
public class OficinaController {
    
    @Autowired
    private OficinaService oficinaService;

    @GetMapping("/Detalhes/{id}")
    public ResponseEntity<Oficina> findById(@PathVariable Integer id){
        Oficina objModel = this.oficinaService.findById(id);
        return ResponseEntity.ok().body(objModel);
    }

    @PostMapping("/Create")
    public ResponseEntity<Void> create(@RequestBody Oficina objModel){
        this.oficinaService.create(objModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                  .path("/{id}").buildAndExpand(objModel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/Alterar/{id}")
    public ResponseEntity<Void> update(@RequestBody Oficina objModel, @PathVariable Integer id){
        objModel.setId(id);
        this.oficinaService.update(objModel);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.oficinaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Pesquisar")
    public ResponseEntity<List<Oficina>> findByNome(@RequestParam String textoPesquisa){
        List<Oficina> objModel = this.oficinaService.findByName(textoPesquisa);
        if(objModel.size() == 0){
           objModel = this.oficinaService.findByEspecialidade(textoPesquisa);
        }
        return ResponseEntity.ok().body(objModel);
    }

    @GetMapping("/RetornarEspecialidades")
    public List<String> returnEspecialidades(@RequestParam Integer idOficina){
        List<String> especialidades = this.oficinaService.returnEspecialidades(idOficina);
        return especialidades;
    }

}
