package br.com.amazona.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Relatorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dataInicial;
	private String dataFinal;
	private Double totalBruto;
	private Double totalLiquido;
	private Double totalGasto;
	private String dataGerado;

	public Relatorio() {
	}

	public Long getId() {
		return id;
	}

	public Relatorio(Long id, String dataInicial, String dataFinal, Double totalBruto, Double totalLiquido,
			Double totalGasto, String dataGerado) {
		this.id = id;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.totalBruto = totalBruto;
		this.totalLiquido = totalLiquido;
		this.totalGasto = totalGasto;
		this.dataGerado = dataGerado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Double getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto(Double totalBruto) {
		this.totalBruto = totalBruto;
	}

	public Double getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(Double totalLiquido) {
		this.totalLiquido = totalLiquido;
	}

	public Double getTotalGasto() {
		return totalGasto;
	}

	public void setTotalGasto(Double totalGasto) {
		this.totalGasto = totalGasto;
	}

	public String getDataGerado() {
		return dataGerado;
	}

	public void setDataGerado(String dataGerado) {
		this.dataGerado = dataGerado;
	}
}
