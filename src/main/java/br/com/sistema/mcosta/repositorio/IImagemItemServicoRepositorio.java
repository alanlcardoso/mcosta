package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.mcosta.entidade.ImagemItemServico;

public interface IImagemItemServicoRepositorio extends JpaRepository<ImagemItemServico, Long> {
	
}