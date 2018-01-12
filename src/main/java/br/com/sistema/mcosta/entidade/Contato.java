package br.com.sistema.mcosta.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.sistema.mcosta.enums.Estado;
import br.com.sistema.mcosta.enums.TipoLogradouro;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator_contato", strategy = "increment")
	@GeneratedValue(generator = "generator_contato")
	private Long id;

	@NotBlank(message = "Título é obrigatório.")
	private String titulo;
	
	@Enumerated(EnumType.STRING)
	private TipoLogradouro tipoLogradouro;
	
	@NotBlank(message = "Logradouro é obrigatório.")
	private String logradouro;
	
	@NotNull(message = "Número é obrigatório.")
	private Integer numero;
	
	@NotBlank(message = "Bairro é obrigatório.")
	private String bairro;
	
	@NotBlank(message = "Cidade é obrigatório.")
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@NotBlank(message = "CEP é obrigatório.")
	private String cep;
	
	@NotBlank(message = "Latitude é obrigatório.")
	private String latitude;
	
	@NotBlank(message = "Longitude é obrigatório.")
	private String longitude;
	
	@Email
	@NotBlank(message = "Email é obrigatório.")
	private String email;
	
	@NotBlank(message = "Telefone é obrigatório.")
	private String telefone;

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

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
