package br.com.sistema.mcosta.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "sobre_detalhe")
public class SobreDetalhe implements Serializable {

	private static final long serialVersionUID = 8684062315905211824L;
	
	@Id
	@GenericGenerator(name = "generator_sobre_detalhe", strategy = "increment")
	@GeneratedValue(generator = "generator_sobre_detalhe")
	private Long id;

	@NotBlank(message = "Nome é obrigatório.")
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória.")
	private String descricao;
	
	@NotBlank(message = "Ícone é obrigatório.")
	private String icone;
	
	@OneToMany(mappedBy="sobreDetalhe")
	private List<IdentificacaoSobreDetalhe> detalhes;
	
	public SobreDetalhe() {
	}
	
	public SobreDetalhe(String nome, String descricao, String icone) {
		this.nome = nome;
		this.descricao = descricao;
		this.icone = icone;
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

	public String getIcone() {
		return icone;
	}
	
	public void setIcone(String icone) {
		this.icone = icone;
	}
	
	public List<IdentificacaoSobreDetalhe> getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(List<IdentificacaoSobreDetalhe> detalhes) {
		this.detalhes = detalhes;
	}
	
}
