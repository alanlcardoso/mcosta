package br.com.sistema.mcosta.visao;

import java.util.ArrayList;
import java.util.List;

import br.com.sistema.mcosta.entidade.ImagemItemServico;
import br.com.sistema.mcosta.entidade.ItemServico;

public class ServicoClienteVisao {

	private String nome;
	
	private String descricao;
	
	private String imagem;
	
	private List<ItemServico> itens = new ArrayList<>();
	
	private List<ImagemItemServico> imagensItemServico = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public List<ItemServico> getItens() {
		return itens;
	}

	public void setItens(List<ItemServico> itens) {
		this.itens = itens;
	}

	public List<ImagemItemServico> getImagensItemServico() {
		return imagensItemServico;
	}

	public void setImagensItemServico(List<ImagemItemServico> imagensItemServico) {
		this.imagensItemServico = imagensItemServico;
	}
	
	
}
