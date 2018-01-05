package br.com.sistema.mcosta.servico;

import java.util.ArrayList;
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
	public List<Sobre> buscaDetalheSobre() {
		Object[][] obj = sobreRepositorio.buscaDetalheSobre();

		if (obj.length == 0) {
			return null;
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

		SobreDetalhe sobreDetalhe = new SobreDetalhe(obj[i][3].toString(), obj[i][4].toString(), obj[i][5].toString());
		sobre.setSobreDetalhe1(sobreDetalhe);

		sobreDetalhe = new SobreDetalhe(obj[i][6].toString(), obj[i][7].toString(), obj[i][8].toString());
		sobre.setSobreDetalhe2(sobreDetalhe);

		sobreDetalhe = new SobreDetalhe(obj[i][9].toString(), obj[i][10].toString(), obj[i][11].toString());
		sobre.setSobreDetalhe3(sobreDetalhe);
		
		return sobre;
	}
}
