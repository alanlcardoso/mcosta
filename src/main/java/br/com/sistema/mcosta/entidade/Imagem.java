package br.com.sistema.mcosta.entidade;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.sistema.mcosta.util.Util;

@Entity
@Table(name = "imagem")
public class Imagem extends AbstractEntidade implements Serializable {

	private static final long serialVersionUID = 2590326424009104950L;

	@Id
	@GenericGenerator(name = "generator_imagem", strategy = "increment")
	@GeneratedValue(generator = "generator_imagem")
	private Long id;

	@Column(name = "foto", nullable = true, unique = false)
	private Byte[] foto;
	
	@OneToMany(mappedBy="imagem")
	private List<ImagemItemServico> imagensItemServico;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}
	
	public String getFotoBase64() {
		return Base64.getEncoder().encodeToString(Util.toPrimitives(this.foto));
	}
	
	public List<ImagemItemServico> getImagensItemServico() {
		return imagensItemServico;
	}
	
	public void setImagensItemServico(List<ImagemItemServico> imagensItemServico) {
		this.imagensItemServico = imagensItemServico;
	}
}
