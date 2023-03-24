package br.com.amazona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.amazona.model.GastoAdicional;

@Repository
public interface GastoAdicionalRepository extends JpaRepository<GastoAdicional, Long>{

}
