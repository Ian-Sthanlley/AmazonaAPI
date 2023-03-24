package br.com.amazona.model;

public enum EstadoVenda {
	VENDA("Venda"),
	VENDIDO("Vendido");
	
	private String descricao;

	EstadoVenda(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
