package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.Sobre;

public interface ISobreRepositorio extends JpaRepository<Sobre, Long> {
	
}
