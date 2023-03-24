package br.com.amazona.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {
	private LocalDate data = LocalDate.now();
	
	public String getData() {
		return data.toString();
	}

	public List<String> rodaDatas(LocalDate dataInicial, LocalDate dataFinal) {
		List<String> datas = new ArrayList<>();
		
		for (LocalDate dataAtual = dataInicial; !dataAtual.isAfter(dataFinal); dataAtual = dataAtual.plusDays(1)) {
			datas.add(dataAtual.toString());
		}
		return datas;
	}

}
