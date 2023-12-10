package com.horou.Tis.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.horou.Tis.Models.Servicos;

@Repository
public interface ServicosRepository extends JpaRepository<Servicos,Integer> {
    
}
