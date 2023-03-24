package br.com.amazona.controller;

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

import br.com.amazona.model.GastoAdicional;
import br.com.amazona.model.Produto;
import br.com.amazona.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> getAll() {
		return produtoService.getAll();
	}
	
	@GetMapping("/venda")
	public List<Produto> getAllVenda() {
		return produtoService.getAllVenda();
	}
	
	@GetMapping("/vendido")
	public List<Produto> getAllVendido() {
		return produtoService.getAllVendido();
	}
	
	@GetMapping("/datainicial/{dataInicial}/datafinal/{dataFinal}")
	public List<Produto> getAllRangeData(@PathVariable String dataInicial,@PathVariable String dataFinal) {
		return produtoService.getAllRangeData(dataInicial, dataFinal);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoService.getById(id);
	}
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Produto> getByCodigo(@PathVariable Long codigo){
		return produtoService.getByCodigo(codigo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateById(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoService.updateById(id, produto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deleteById(@PathVariable Long id) {
		return produtoService.deleteById(id);
	}

	@PostMapping
	public ResponseEntity<Produto> postNew(@RequestBody Produto produto) {
		return produtoService.postNew(produto);
	}

	@PostMapping("/{id}")
	public ResponseEntity<GastoAdicional> postNewGasto(@PathVariable Long id,
			@RequestBody GastoAdicional gastoAdicional) {
		return produtoService.postNewGasto(id, gastoAdicional);
	}
	
	@PutMapping("/gastos/{id}")
	public ResponseEntity<GastoAdicional> editGastoAdicional(@PathVariable Long id, @RequestBody GastoAdicional gastoAdicional) {
		return produtoService.editaGastoAdicional(id, gastoAdicional);
	}
	
	@PutMapping("/{id}/vendido")
	public ResponseEntity<Produto> setVendido(@PathVariable Long id, @RequestBody Produto produtoVendido) {
		return produtoService.setVendido(id, produtoVendido);
	}
}
