package br.com.sistema.mcosta.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Servico")
public class Servico implements Serializable {

	private static final long serialVersionUID = 201711261509l;

	@Id
	@GenericGenerator(name = "generator_servico", strategy = "increment")
	@GeneratedValue(generator = "generator_servico")
	private Long id;

	@NotBlank(message = "Nome é obrigatório.")
	@Size(max = 50, message = "O nome não pode conter mais de 50 caracteres")
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória.")
	@Size(max = 500, message = "A descrição não pode conter mais de 500 caracteres")
	private String descricao;

	@NotBlank(message = "Ícone é obrigatório.")
	@Size(max = 50, message = "O ícone não pode conter mais de 50 caracteres")
	private String imagem;

	public Servico() {
	}

	public Servico(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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
}
