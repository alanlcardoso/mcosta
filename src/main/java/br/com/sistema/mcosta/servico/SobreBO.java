package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.IdentificacaoSobreDetalhe;
import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.entidade.SobreDetalhe;
import br.com.sistema.mcosta.repositorio.IIdentificacaoSobreDetalheRepositorio;
import br.com.sistema.mcosta.repositorio.ISobreDetalheRepositorio;
import br.com.sistema.mcosta.repositorio.ISobreRepositorio;

@Service
public class SobreBO {

	@Autowired
	private ISobreRepositorio sobreRepositorio;

	@Autowired
	private ISobreDetalheRepositorio sobreDetalheRepositorio;

	@Autowired
	private IIdentificacaoSobreDetalheRepositorio identificacaoSobreDetalheRepositorio;

	public SobreDetalhe salvar(SobreDetalhe sobreDetalhe) {
		return sobreDetalheRepositorio.save(sobreDetalhe);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Sobre salvar(Sobre sobre) {

		sobre = sobreRepositorio.save(sobre);

		SobreDetalhe sobreDetalhe = salvarSobreDetalhe(sobre.getSobreDetalhe1().getNome(),
				sobre.getSobreDetalhe1().getDescricao(), sobre.getSobreDetalhe1().getIcone());
		salvarIdentificacaoSobreDetalhe(new IdentificacaoSobreDetalhe(sobre, sobreDetalhe));

		sobreDetalhe = salvarSobreDetalhe(sobre.getSobreDetalhe2().getNome(), sobre.getSobreDetalhe2().getDescricao(),
				sobre.getSobreDetalhe2().getIcone());
		salvarIdentificacaoSobreDetalhe(new IdentificacaoSobreDetalhe(sobre, sobreDetalhe));

		sobreDetalhe = salvarSobreDetalhe(sobre.getSobreDetalhe3().getNome(), sobre.getSobreDetalhe3().getDescricao(),
				sobre.getSobreDetalhe3().getIcone());
		salvarIdentificacaoSobreDetalhe(new IdentificacaoSobreDetalhe(sobre, sobreDetalhe));

		return sobreRepositorio.save(sobre);
	}

	private IdentificacaoSobreDetalhe salvarIdentificacaoSobreDetalhe(
			IdentificacaoSobreDetalhe identificacaoSobreDetalhe) {
		return identificacaoSobreDetalheRepositorio.save(identificacaoSobreDetalhe);
	}

	private SobreDetalhe salvarSobreDetalhe(String nome, String descricao, String icone) {
		return sobreDetalheRepositorio.save(new SobreDetalhe(nome, descricao, icone));
	}

	@Transactional(readOnly = true)
	public List<SobreDetalhe> buscarTodosSobreDetalhe() {
		return sobreDetalheRepositorio.findAll();
	}

	@Transactional(readOnly = true)
	public List<Sobre> buscarTodos() {
		return sobreRepositorio.findAll();
	}

	@Transactional(readOnly = true)
	public List<IdentificacaoSobreDetalhe> buscarTodosIdentificacao() {
		return identificacaoSobreDetalheRepositorio.findAll();
	}

	@Cacheable("paginaInicial")
	public Sobre buscaDetalheSobre() {
		Object[][] obj = sobreRepositorio.buscaDetalheSobre();

		if (obj.length == 0) {
			return null;
		}

		Sobre sobre = new Sobre();

		sobre.setTitulo(obj[0][0].toString());
		sobre.setDescricao(obj[0][1].toString());

		SobreDetalhe sobreDetalhe = new SobreDetalhe(obj[0][2].toString(), obj[0][3].toString(), obj[0][4].toString());
		sobre.setSobreDetalhe1(sobreDetalhe);

		sobreDetalhe = new SobreDetalhe(obj[0][5].toString(), obj[0][6].toString(), obj[0][7].toString());
		sobre.setSobreDetalhe2(sobreDetalhe);

		sobreDetalhe = new SobreDetalhe(obj[0][8].toString(), obj[0][9].toString(), obj[0][10].toString());
		sobre.setSobreDetalhe3(sobreDetalhe);

		return sobre;
	}
}
