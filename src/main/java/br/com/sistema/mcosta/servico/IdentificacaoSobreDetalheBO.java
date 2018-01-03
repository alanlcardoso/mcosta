package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.mcosta.entidade.IdentificacaoSobreDetalhe;
import br.com.sistema.mcosta.repositorio.IIdentificacaoSobreDetalheRepositorio;

@Service
public class IdentificacaoSobreDetalheBO {

	@Autowired
	private IIdentificacaoSobreDetalheRepositorio identificacaoSobreDetalheBO;
	
	public void salvar(IdentificacaoSobreDetalhe identificacaoSobreDetalhe) {
		identificacaoSobreDetalheBO.save(identificacaoSobreDetalhe);
	}
	
	public List<IdentificacaoSobreDetalhe> buscarTodos() {
		return identificacaoSobreDetalheBO.findAll();
	}
	
}
