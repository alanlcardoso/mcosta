package br.com.sistema.mcosta.enums;

public enum TipoLogradouro {
	
	AVENIDA("Avenida"),
	ALAMEDA("Alameda"),
	RUA("Rua");

	private String descricao;
	
	private TipoLogradouro(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
