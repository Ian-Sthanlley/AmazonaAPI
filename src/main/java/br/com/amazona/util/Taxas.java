package br.com.amazona.util;

public class Taxas {

	private Double taxa5Vezes = (16.97 / 100);
	private Double taxa10Vezes = (21.97 / 100);

	public Double taxa5Vezes(Double valor) {
		Double novoValor = valor + (taxa5Vezes * valor);
		String valorFinal = String.format("%.2f", novoValor);
		return Double.parseDouble(valorFinal.replace(",", "."));
	}

	public Double taxa10Vezes(Double valor) {
		Double novoValor = valor + (taxa10Vezes * valor);
		String valorFinal = String.format("%.2f", novoValor);
		return Double.parseDouble(valorFinal.replace(",", "."));
	}

}