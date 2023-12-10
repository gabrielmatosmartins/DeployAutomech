package com.horou.Tis.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.horou.Tis.Models.Oficina;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Integer> {
    
    @Query(value = "SELECT * FROM oficinas where nome like %:nome%", nativeQuery = true)
    Optional<List<Oficina>> findByNome(@Param("nome") String nome);

    @Override
    @Query(value = "SELECT * FROM oficinas WHERE idoficinas = :idoficinas", nativeQuery = true)
    Optional<Oficina> findById(@Param("idoficinas") Integer idoficinas);

    @Query(value = "SELECT nomeEspecialidade FROM especialidades e join oficinas_especialidades oe on oe.especialidades_idespecialidades = e.idespecialidades join oficinas o on o.idoficinas = oe.oficinas_idoficinas where idoficinas = :id", nativeQuery = true)
    List<String> returnEspecialidades(@Param("id") Integer id);

    @Query(value = "SELECT * FROM oficinas o join oficinas_especialidades oe on oe.oficinas_idoficinas = o.idoficinas join especialidades e on e.idespecialidades = oe.especialidades_idespecialidades  WHERE e.nomeespecialidade like %:nome%", nativeQuery = true)
    Optional<List<Oficina>> findByEspecialidade(@Param("nome") String nome);

}
