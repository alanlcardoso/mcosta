package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.mcosta.entidade.ImagemItemServico;

public interface IImagemItemServicoRepositorio extends JpaRepository<ImagemItemServico, Long> {
	
	@Query("FROM ImagemItemServico i WHERE i.itemServico.id = ? AND imagem.id = ?")
	ImagemItemServico buscarPor(Long idItem, Long idImagem);
	
}