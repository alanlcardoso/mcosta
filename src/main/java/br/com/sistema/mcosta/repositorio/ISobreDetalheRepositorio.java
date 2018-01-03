package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.SobreDetalhe;

public interface ISobreDetalheRepositorio extends JpaRepository<SobreDetalhe, Long> {
	
}