package com.horou.Tis.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.horou.Tis.Models.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    
    Optional<List<Veiculo>> findByCliente_Usuario_id(Integer id);
    Optional<List<Veiculo>> getByStatus(int status);
}
