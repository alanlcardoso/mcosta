package br.com.sistema.mcosta.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

	@Transactional(propagation = Propagation.REQUIRED)
	@CacheEvict(value = "paginaInicial", allEntries = true)
	public Sobre salvarSobre(Sobre sobre) {

		if (sobre.getId() == null) {
			sobre = salvar(sobre);

			novo(sobre, sobre.getSobreDetalhe1().getNome(), sobre.getSobreDetalhe1().getDescricao(),
					sobre.getSobreDetalhe1().getIcone());
			novo(sobre, sobre.getSobreDetalhe2().getNome(), sobre.getSobreDetalhe2().getDescricao(),
					sobre.getSobreDetalhe2().getIcone());
			novo(sobre, sobre.getSobreDetalhe3().getNome(), sobre.getSobreDetalhe3().getDescricao(),
					sobre.getSobreDetalhe3().getIcone());

			return sobre;
		}

		salvar(sobre.getSobreDetalhe1());
		salvar(sobre.getSobreDetalhe2());
		salvar(sobre.getSobreDetalhe3());

		return salvar(sobre);
	}

	public Sobre salvar(Sobre sobre) {
		return sobreRepositorio.save(sobre);
	}

	public SobreDetalhe salvar(SobreDetalhe sobreDetalhe) {
		return sobreDetalheRepositorio.save(sobreDetalhe);
	}

	public IdentificacaoSobreDetalhe salvar(IdentificacaoSobreDetalhe identificacaoSobreDetalhe) {
		return identificacaoSobreDetalheRepositorio.save(identificacaoSobreDetalhe);
	}

	private void novo(Sobre sobre, String nome, String descricao, String icone) {
		SobreDetalhe sobreDetalhe = new SobreDetalhe(nome, descricao, icone);
		salvar(sobreDetalhe);

		IdentificacaoSobreDetalhe detalhe = new IdentificacaoSobreDetalhe(sobre, sobreDetalhe);
		salvar(detalhe);
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
	public List<Sobre> buscaDetalheSobre() {
		Object[][] obj = sobreRepositorio.buscaDetalheSobre();

		if (obj.length == 0) {
			return Collections.emptyList();
		}

		List<Sobre> listaSobre = new ArrayList<>();

		for (int i = 0; i < obj.length; i++) {
			Sobre sobre = montaSobre(obj, i);
			listaSobre.add(sobre);
		}

		return listaSobre;
	}

	public Sobre buscaDetalheSobrePorId(Long id) {
		Object[][] obj = sobreRepositorio.buscaDetalheSobrePorId(id);

		if (obj.length == 0) {
			return null;
		}

		return montaSobre(obj, 0);
	}

	private Sobre montaSobre(Object[][] obj, int i) {
		Sobre sobre = new Sobre();

		sobre.setId(Long.valueOf(obj[i][0].toString()));
		sobre.setTitulo(obj[i][1].toString());
		sobre.setDescricao(obj[i][2].toString());

		SobreDetalhe sobreDetalhe = new SobreDetalhe(Long.valueOf(obj[i][3].toString()), obj[i][4].toString(),
				obj[i][5].toString(), obj[i][6].toString());
		sobre.setSobreDetalhe1(sobreDetalhe);

		sobreDetalhe = new SobreDetalhe(Long.valueOf(obj[i][7].toString()), obj[i][8].toString(), obj[i][9].toString(),
				obj[i][10].toString());
		sobre.setSobreDetalhe2(sobreDetalhe);

		sobreDetalhe = new SobreDetalhe(Long.valueOf(obj[i][11].toString()), obj[i][12].toString(),
				obj[i][13].toString(), obj[i][14].toString());
		sobre.setSobreDetalhe3(sobreDetalhe);

		return sobre;
	}
}
