package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.mcosta.entidade.SobreDetalhe;
import br.com.sistema.mcosta.repositorio.ISobreDetalheRepositorio;

@Service
public class SobreDetalheBO {

	@Autowired
	private ISobreDetalheRepositorio sobreDetalheRepositorio;
	
	public SobreDetalhe salvar(SobreDetalhe sobreDetalhe) {
		return sobreDetalheRepositorio.save(sobreDetalhe);
	}
	
	public List<SobreDetalhe> buscarTodos() {
		return sobreDetalheRepositorio.findAll();
	}
	
}
