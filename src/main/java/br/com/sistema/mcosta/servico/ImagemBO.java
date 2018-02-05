package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.Imagem;
import br.com.sistema.mcosta.entidade.ImagemItemServico;
import br.com.sistema.mcosta.repositorio.IImagemItemServicoRepositorio;
import br.com.sistema.mcosta.repositorio.IImagemRepositorio;

@Service
public class ImagemBO {

	@Autowired
	private IImagemRepositorio imagemRepositorio;

	@Autowired
	private IImagemItemServicoRepositorio imagemItemServicoRepositorio;
	
	public Imagem salvar(Imagem imagem) {
		return imagemRepositorio.save(imagem);
	}

	public void excluir(Long id) {
		imagemRepositorio.delete(id);
	}

	@Transactional(readOnly = true)
	public List<Imagem> buscarTodos() {
		return imagemRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Imagem buscarPorId(Long id) {
		return imagemRepositorio.findOne(id);
	}
	
	public void salvar(ImagemItemServico imagemItemServico) {
		imagemItemServicoRepositorio.save(imagemItemServico);
	}
	
	public void excluirImagemItemServico(Long id) {
		imagemItemServicoRepositorio.delete(id);
	}
	
	@Transactional(readOnly = true)
	public ImagemItemServico buscarPorId(Long idItem, Long idImagem) {
		return imagemItemServicoRepositorio.buscarPor(idItem, idImagem);
	}
	
}
