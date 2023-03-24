package br.com.amazona.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.amazona.model.Produto;
import br.com.amazona.model.Relatorio;
import br.com.amazona.repository.ProdutoRepository;
import br.com.amazona.repository.RelatorioRepository;
import br.com.amazona.util.Data;

@Service
public class RelatorioService {
	
	@Autowired
	private RelatorioRepository relatorioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	private Data data = new Data();
	
	public ResponseEntity<Relatorio> relatorioGeral(String dataInicial, String dataFinal){
		List<String> datas = data.rodaDatas(LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		List<Produto> produtos = new ArrayList<>();
		Relatorio relatorio = new Relatorio();
		
		Double totalBruto = 0.0;
		Double totalGasto = 0.0;
		
		
		for (int x = 0; x < datas.size(); x++) {
			produtos.addAll(produtoRepository.findAllByDataEntrada(datas.get(x)));
		}
		for (int x = 0; x < produtos.size(); x++) {
			totalGasto = totalGasto + produtos.get(x).getValorPago();
			totalBruto = totalBruto + produtos.get(x).getValorAvista();
		}
		Double totalLiquido = totalBruto - totalGasto;
		relatorio.setDataInicial(dataInicial);
		relatorio.setDataFinal(dataFinal);
		relatorio.setTotalGasto(totalGasto);
		relatorio.setTotalBruto(totalBruto);
		relatorio.setTotalLiquido(totalLiquido);
		relatorio.setDataGerado(data.getData());
		
		return ResponseEntity.status(HttpStatus.OK).body(relatorioRepository.save(relatorio));
	}
}
