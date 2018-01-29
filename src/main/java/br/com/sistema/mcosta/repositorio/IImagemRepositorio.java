package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.Imagem;

public interface IImagemRepositorio extends JpaRepository<Imagem, Long> {

}