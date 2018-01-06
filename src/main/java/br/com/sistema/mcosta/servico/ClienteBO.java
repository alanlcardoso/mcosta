package br.com.sistema.mcosta.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.IdentificacaoSobreDetalhe;
import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.entidade.SobreDetalhe;
import br.com.sistema.mcosta.repositorio.IClienteRepositorio;
import br.com.sistema.mcosta.repositorio.IClienteServicoRepositorio;
import br.com.sistema.mcosta.repositorio.IIdentificacaoSobreDetalheRepositorio;

@Service
public class ClienteBO {

	@Autowired
	private IClienteRepositorio clienteRepositorio;

	@Autowired
	private IClienteServicoRepositorio clienteServicoRepositorio;

	@Autowired
	private IIdentificacaoSobreDetalheRepositorio identificacaoSobreDetalheRepositorio;

	@Transactional(propagation = Propagation.REQUIRED)
	public Sobre salvarSobre(Sobre sobre) {

	return null;
	}
	
	
}
