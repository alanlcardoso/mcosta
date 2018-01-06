package br.com.sistema.mcosta.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "sobre")
public class Sobre implements Serializable {

	private static final long serialVersionUID = 8684062315905211824L;
	
	@Id
	@GenericGenerator(name = "generator_sobre", strategy = "increment")
	@GeneratedValue(generator = "generator_sobre")
	private Long id;

	@NotBlank(message = "Título é obrigatório.")
	private String titulo;
	
	@NotBlank(message = "Descrição é obrigatória.")
	private String descricao;
	
	@NotNull(message = "Quantidade de projetos é obrigatória.")
	@NumberFormat(pattern = "#,##0")
	private Integer qtdProjetos;
	
	@NotNull(message = "Horas de trabalho é obrigatória.")
	@NumberFormat(pattern = "#,##0")
	private Integer horasTrabalho;
	
	@OneToMany(mappedBy="sobre")
	private List<IdentificacaoSobreDetalhe> detalhes;
	
	@Transient
	private SobreDetalhe sobreDetalhe1;
	
	@Transient
	private SobreDetalhe sobreDetalhe2;
	
	@Transient
	private SobreDetalhe sobreDetalhe3;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getQtdProjetos() {
		return qtdProjetos;
	}
	
	public void setQtdProjetos(Integer qtdProjetos) {
		this.qtdProjetos = qtdProjetos;
	}
	
	public Integer getHorasTrabalho() {
		return horasTrabalho;
	}
	
	public void setHorasTrabalho(Integer horasTrabalho) {
		this.horasTrabalho = horasTrabalho;
	}
	
	public List<IdentificacaoSobreDetalhe> getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(List<IdentificacaoSobreDetalhe> detalhes) {
		this.detalhes = detalhes;
	}
	
	public SobreDetalhe getSobreDetalhe1() {
		return sobreDetalhe1;
	}
	
	public void setSobreDetalhe1(SobreDetalhe sobreDetalhe1) {
		this.sobreDetalhe1 = sobreDetalhe1;
	}
	
	public SobreDetalhe getSobreDetalhe2() {
		return sobreDetalhe2;
	}
	
	public void setSobreDetalhe2(SobreDetalhe sobreDetalhe2) {
		this.sobreDetalhe2 = sobreDetalhe2;
	}
	
	public SobreDetalhe getSobreDetalhe3() {
		return sobreDetalhe3;
	}
	
	public void setSobreDetalhe3(SobreDetalhe sobreDetalhe3) {
		this.sobreDetalhe3 = sobreDetalhe3;
	}

}
