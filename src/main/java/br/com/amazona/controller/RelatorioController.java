package br.com.amazona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amazona.model.Relatorio;
import br.com.amazona.service.RelatorioService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/datainicial/{dataInicial}/datafinal/{dataFinal}")
	public ResponseEntity<Relatorio> relatorioGeral(@PathVariable String dataInicial, @PathVariable String dataFinal) {
		return relatorioService.relatorioGeral(dataInicial, dataFinal);
	}
}
