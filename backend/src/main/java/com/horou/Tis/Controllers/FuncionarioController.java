package com.horou.Tis.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.horou.Tis.Models.Funcionario;
import com.horou.Tis.Models.Oficina;
import com.horou.Tis.Models.Usuario;
import com.horou.Tis.Services.FuncionarioService;
import com.horou.Tis.Services.OficinaService;
import com.horou.Tis.Services.UsuarioService;

@RestController
@RequestMapping("/Funcionario")
public class FuncionarioController {
    

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OficinaService oficinaService;

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll(){
        List<Funcionario> f = this.funcionarioService.getAll();
        return ResponseEntity.ok().body(f);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findByUserId(@PathVariable Integer id){
        Funcionario f = this.funcionarioService.findByUserId(id);
        return ResponseEntity.ok().body(f);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> create(@RequestBody Funcionario f, 
                                       @PathVariable Integer id){
        Usuario u = this.usuarioService.findById(id);
        Oficina o = this.oficinaService.findById(1);
        f.setOficina(o);
        f.setUsuario(u);
        this.funcionarioService.create(f);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(f.getUserId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Funcionario f, @PathVariable Integer id){
        Usuario u = this.usuarioService.findById(id);
        f.setUsuario(u);
        // f.(id);
        this.funcionarioService.update(f);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
