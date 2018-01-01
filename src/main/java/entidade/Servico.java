package entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória.")
	private String descricao;

	@Column(name = "imagem", nullable = true, unique = false)
	private Byte[] imagem;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Byte[] getImagem() {
		return imagem;
	}

	public void setImagem(Byte[] imagem) {
		this.imagem = imagem;
	}
}
