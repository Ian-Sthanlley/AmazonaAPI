package br.com.amazona.model;

public enum Estado {
	NOVO("Novo"),
	USADO("Usado");
	
	private String descricao;

	Estado(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
