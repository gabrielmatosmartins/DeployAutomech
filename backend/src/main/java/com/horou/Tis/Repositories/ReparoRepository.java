package com.horou.Tis.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.horou.Tis.Models.Reparo;

public interface ReparoRepository  extends JpaRepository<Reparo, Integer> {
    
    @Modifying
    @Query(value = "UPDATE reparo SET statusReparo = 2, dataReparo = :dataAtual where idReparo in (:id)", nativeQuery = true)
    int endRepair(@Param("id") List<Integer> id, @Param("dataAtual") LocalDate dataAtual);

    @Query(value = "SELECT * FROM reparo r join orcamento_reparo orr on orr.reparo_idreparo = r.idreparo join orcamento o on o.idorcamento = orr.orcamento_idorcamento where o.idOrcamento = :idOrcamento", nativeQuery = true)
    Optional<List<Reparo>> findByOrcamento(@Param("idOrcamento") Integer idOrcamento);

    @Query(value = "SELECT * FROM reparo where datareparo < prazoreparo", nativeQuery = true)
    Optional<Double> retornarReparosNoPrazo();

    @Query(value = "SELECT * FROM reparo where statusReparo in (:status)", nativeQuery = true)
    List<Reparo> returnAll(@Param("status") List<Integer> status);
    
}
