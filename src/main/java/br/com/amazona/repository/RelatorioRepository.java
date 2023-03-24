package br.com.amazona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amazona.model.Relatorio;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

}
