package br.com.sistema.mcosta.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "item_servico")
public class ItemServico extends AbstractEntidade implements Serializable {

	private static final long serialVersionUID = 2590326424009104950L;

	@Id
	@GenericGenerator(name = "generator_item_servico", strategy = "increment")
	@GeneratedValue(generator = "generator_item_servico")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;

	@NotBlank(message = "Descrição é obrigatória.")
	@Size(max = 200, message = "A descrição não pode conter mais de 200 caracteres")
	private String descricao;
	
	@OneToMany(mappedBy="itemServico")
	private List<ImagemItemServico> imagensItemServico;

	public ItemServico() {
	}

	public ItemServico(Servico servico, String descricao) {
		super();
		this.servico = servico;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}
	
	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<ImagemItemServico> getImagensItemServico() {
		return imagensItemServico;
	}
	
	public void setImagensItemServico(List<ImagemItemServico> imagensItemServico) {
		this.imagensItemServico = imagensItemServico;
	}
	
}
