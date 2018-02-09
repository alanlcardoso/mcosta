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
@Table(name = "identificacao_sobre_detalhe")
public class IdentificacaoSobreDetalhe extends AbstractEntidade implements Serializable {

	private static final long serialVersionUID = 8684062315905211824L;
	
	@Id
	@GenericGenerator(name = "generator_identificacao_sobre_detalhe", strategy = "increment")
	@GeneratedValue(generator = "generator_identificacao_sobre_detalhe")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_sobre")
	private Sobre sobre;
	
	@ManyToOne
	@JoinColumn(name="id_sobre_detalhe")
	private SobreDetalhe sobreDetalhe;
	
	public IdentificacaoSobreDetalhe() {
	}
	
	public IdentificacaoSobreDetalhe(Sobre sobre, SobreDetalhe sobreDetalhe) {
		this.sobre = sobre;
		this.sobreDetalhe = sobreDetalhe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sobre getSobre() {
		return sobre;
	}
	
	public void setSobre(Sobre sobre) {
		this.sobre = sobre;
	}
	
	public SobreDetalhe getSobreDetalhe() {
		return sobreDetalhe;
	}
	
	public void setSobreDetalhe(SobreDetalhe sobreDetalhe) {
		this.sobreDetalhe = sobreDetalhe;
	}

}
