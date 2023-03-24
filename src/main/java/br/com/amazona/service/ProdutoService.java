package br.com.amazona.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.amazona.model.EstadoVenda;
import br.com.amazona.model.GastoAdicional;
import br.com.amazona.model.Produto;
import br.com.amazona.repository.GastoAdicionalRepository;
import br.com.amazona.repository.ProdutoRepository;
import br.com.amazona.util.Data;
import br.com.amazona.util.Taxas;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private GastoAdicionalRepository gastoAdicionalRepository;

	private Taxas taxa = new Taxas();
	private Data data = new Data();

	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}
	
	public List<Produto> getAllVenda() {
		return produtoRepository.findAllByEstadoVenda(EstadoVenda.VENDA);
	}

	public List<Produto> getAllVendido() {
		return produtoRepository.findAllByEstadoVenda(EstadoVenda.VENDIDO);
	}
	
	
	public List<Produto> getAllRangeData(String dataInicial, String dataFinal) {
		List<String> datas = data.rodaDatas(LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		List<Produto> produtos = new ArrayList<>();
		for (int x = 0; x < datas.size(); x++) {
			produtos.addAll(produtoRepository.findAllByDataEntrada(datas.get(x)));
		}
		return produtos;
	}
	
	public ResponseEntity<Produto> getById(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(produto.get());
		}
		return ResponseEntity.notFound().build();
	}
	public ResponseEntity<Produto> getByCodigo(Long codigo) {
		Optional<Produto> produto = produtoRepository.findByCodigo(codigo);
		if (produto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(produto.get());
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Produto> deleteById(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			produtoRepository.delete(produto.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Produto> updateById(Long id, Produto produtoEditado) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {

			produtoEditado.setId(id);
			produtoEditado.setCodigo(produto.get().getCodigo());

			if (produtoEditado.getNome() == null) {
				produtoEditado.setNome(produto.get().getNome());
			}
			if (produtoEditado.getValorPago() == null) {
				produtoEditado.setValorPago(produto.get().getValorPago());
			}
			if (produtoEditado.getDescricao() == null) {
				produtoEditado.setDescricao(produto.get().getDescricao());
			}
			if (produtoEditado.getDataEntrada() == null) {
				produtoEditado.setDataEntrada(produto.get().getDataEntrada());
			}
			if (produtoEditado.getDataSaida() == null) {
				produtoEditado.setDataSaida(produto.get().getDataSaida());
			}
			if (produtoEditado.getValorPago() == null) {
				produtoEditado.setValorPago(produto.get().getValorPago());
			}
			if (produtoEditado.getAnotacaoSaida() == null) {
				produtoEditado.setAnotacaoSaida(produto.get().getAnotacaoSaida());
			}
			if (produtoEditado.getValor5Vezes() == null) {
				produtoEditado.setValor5Vezes(produto.get().getValor5Vezes());
			}
			if (produtoEditado.getValor10Vezes() == null) {
				produtoEditado.setValor10Vezes(produto.get().getValor10Vezes());
			}
			

			if (produtoEditado.getValorAvista() == null
					|| produtoEditado.getValorAvista() != produto.get().getValorAvista()) {
				if (produtoEditado.getValorAvista() == null) {
					produtoEditado.setValorAvista(produto.get().getValorAvista());
				} else if (produtoEditado.getValorAvista() != produto.get().getValorAvista()) {
					produtoEditado.setValor5Vezes(taxa.taxa5Vezes(produtoEditado.getValorAvista()));
					produtoEditado.setValor10Vezes(taxa.taxa10Vezes(produtoEditado.getValorAvista()));
				}

			}
			if (produtoEditado.getEstado() == null) {
				produtoEditado.setEstado(produto.get().getEstado());
			}
			if (produtoEditado.getEstadoVenda() == null) {
				produtoEditado.setEstadoVenda(produto.get().getEstadoVenda());
			}

			if (produtoEditado.getGastosAdicionais().isEmpty()) {
				produtoEditado.setGastosAdicionais(produto.get().getGastosAdicionais());
			}

			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoEditado));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Produto> postNew(Produto produto) {
		if (produto.getValorAvista() != null) {
			produto.setDataEntrada(data.getData());
			produto.setEstadoVenda(EstadoVenda.VENDA);
			produto.setValor5Vezes(taxa.taxa5Vezes(produto.getValorAvista()));
			produto.setValor10Vezes(taxa.taxa10Vezes(produto.getValorAvista()));
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	public ResponseEntity<GastoAdicional> postNewGasto(Long id, GastoAdicional gastoAdicional) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			gastoAdicional.setProduto(produto.get());
			gastoAdicional.setData(data.getData());
			gastoAdicionalRepository.save(gastoAdicional);
			return ResponseEntity.status(HttpStatus.CREATED).body(gastoAdicional);
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<GastoAdicional> editaGastoAdicional(Long id, GastoAdicional gastoAdicional) {
		Optional<GastoAdicional> gasto = gastoAdicionalRepository.findById(id);
		if (gasto.isPresent()) {
			gastoAdicional.setId(gasto.get().getId());
			gastoAdicional.setProduto(gasto.get().getProduto());
			gastoAdicional.setData(gasto.get().getData());
			if(gastoAdicional.getDescricao() == null) {
				gastoAdicional.setDescricao(gasto.get().getDescricao());
			}
			if(gastoAdicional.getValor() == null ) {
				gastoAdicional.setValor(gasto.get().getValor());
			}
			return ResponseEntity.status(HttpStatus.OK).body(gastoAdicionalRepository.save(gastoAdicional));
			
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Produto> setVendido(Long id, Produto produtoVendido) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			produto.get().setDataSaida(data.getData());
			produto.get().setAnotacaoSaida(produtoVendido.getAnotacaoSaida());
			produto.get().setEstadoVenda(EstadoVenda.VENDIDO);
			
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
}

//||
