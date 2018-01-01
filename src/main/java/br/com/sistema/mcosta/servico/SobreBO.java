package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.repositorio.ISobreRepositorio;

@Service
public class SobreBO {

	@Autowired
	private ISobreRepositorio sobreRepositorio;
	
	public void salvar(Sobre sobre) {
		sobreRepositorio.save(sobre);
	}
	
	public List<Sobre> buscarTodos() {
		return sobreRepositorio.findAll();
	}
	
}
