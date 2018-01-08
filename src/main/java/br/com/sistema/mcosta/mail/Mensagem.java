package br.com.sistema.mcosta.mail;

import java.util.List;

public class Mensagem {
	
	private String nome;
	private String remetente;
	private List<String> destinatarios;
	private String assunto;
	private String corpo;
	
	public Mensagem() {
	}
	
	public Mensagem(String nome, String remetente, List<String> destinatarios, String assunto, String corpo) {
		this.nome = nome;
		this.remetente = remetente;
		this.destinatarios = destinatarios;
		this.assunto = assunto;
		this.corpo = corpo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public List<String> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
}
