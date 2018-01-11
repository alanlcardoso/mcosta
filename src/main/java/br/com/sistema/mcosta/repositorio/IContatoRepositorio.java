package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.Contato;

public interface IContatoRepositorio extends JpaRepository<Contato, Long> {
}