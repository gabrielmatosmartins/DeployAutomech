package com.horou.Tis.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.horou.Tis.Models.Cliente;
import com.horou.Tis.Models.Usuario;
import com.horou.Tis.Repositories.ClienteRepository;


@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired 
    private UsuarioService usuarioService;

    public List<Cliente> getAll(){
        List<Cliente> c = this.clienteRepository.findAll();
        return c;
    } 

    @Transactional
    public Cliente create(Cliente c){
        Usuario u = this.usuarioService.findById(c.getUserId());

        c.setId(null);
        c.setUsuario(u);
        c = this.clienteRepository.save(c);
        return c;
    }

    public Cliente findByUserId(Integer id){
        Optional<Cliente> c = this.clienteRepository.findByUsuario_Id(id);
        return c.orElseThrow(() -> new RuntimeException(
            "Cliente não encontrado"
        ));
    }

    public Cliente findByEmail(String email){
        Optional<Cliente> c = this.clienteRepository.findByEmail(email);
        return c.orElseThrow(() -> new RuntimeException(
            "Cliente não encontrado"
        ));
    }

    @Transactional
    public Cliente update(Cliente c){
        Cliente newCliente = findByUserId(c.getUserId());
        newCliente.setSenha(c.getSenha());
        newCliente.setEmail(c.getEmail());
        newCliente.setEndereco(c.getEndereco());
        newCliente.setTelefone(c.getTelefone());

        return this.clienteRepository.save(newCliente);
    }

    public void delete(Integer id){
        Cliente c = findByUserId(id);

        try{
            this.clienteRepository.deleteById(c.getUserId());
        }catch(Exception e){
            throw new RuntimeException("Não é possível excluir, pois possui entidades relacionadas");
        }
    }

    public ArrayList<Double> getPeriodo(){
        List<Cliente> c = this.clienteRepository.findAll();
        ArrayList<Double> taxas= new ArrayList<Double>();
        for(int i = 1; i <= 4; i++){
            LocalDate now = LocalDate.now();
            LocalDate trimestreFinal = LocalDate.of(now.getYear(),  i * 3, 30);
            LocalDate trimestreInicio = LocalDate.of(now.getYear(), i * 3 - 2, 1);
            LocalDate trimestreAnteriorInicio = LocalDate.of(trimestreInicio.minusMonths(3).getYear(), trimestreInicio.minusMonths(3).getMonthValue(), 1);

            double antes = c.stream().filter(x-> x.getData().isAfter(trimestreAnteriorInicio) && x.getData().isBefore(trimestreInicio)).count();
            double depois = c.stream().filter(x-> x.getData().isAfter(trimestreInicio) && x.getData().isBefore(trimestreFinal)).count();

            double denominador = antes == 0d ? 1:antes;

            double crescimento = (depois-antes)*100/denominador;
            taxas.add(i-1, crescimento);
        }

        return taxas;
    }
}
