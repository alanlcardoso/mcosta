package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.Servico;

public interface IServicoRepositorio extends JpaRepository<Servico, Long> {

}