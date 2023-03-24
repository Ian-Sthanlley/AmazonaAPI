package br.com.amazona.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.amazona.model.EstadoVenda;
import br.com.amazona.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Optional<Produto> findByCodigo(Long codigo);
	List<Produto> findAllByEstadoVenda(EstadoVenda estadoVenda);
	List<Produto> findAllByDataSaida(String data);
	List<Produto> findAllByDataEntrada(String data);
}
