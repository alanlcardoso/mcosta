package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.IdentificacaoSobreDetalhe;

public interface IIdentificacaoSobreDetalheRepositorio extends JpaRepository<IdentificacaoSobreDetalhe, Long> {
	
}