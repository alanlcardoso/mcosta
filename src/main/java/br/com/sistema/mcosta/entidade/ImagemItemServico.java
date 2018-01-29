package br.com.sistema.mcosta.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "imagem_item_servico")
public class ImagemItemServico implements Serializable {

	private static final long serialVersionUID = -4599296916868617909L;

	@Id
	@GenericGenerator(name = "generator_imagem_item_servico", strategy = "increment")
	@GeneratedValue(generator = "generator_imagem_item_servico")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_item_servico")
	private ItemServico itemServico;
	
	@ManyToOne
	@JoinColumn(name="id_imagem")
	private Imagem imagem;
	
	public ImagemItemServico() {
	}
	
	public ImagemItemServico(ItemServico itemServico, Imagem imagem) {
		super();
		this.itemServico = itemServico;
		this.imagem = imagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemServico getItemServico() {
		return itemServico;
	}

	public void setItemServico(ItemServico itemServico) {
		this.itemServico = itemServico;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	
}
