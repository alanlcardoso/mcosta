package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.entidade.SobreDetalhe;
import br.com.sistema.mcosta.repositorio.ISobreRepositorio;

@Service
public class SobreBO {

	@Autowired
	private ISobreRepositorio sobreRepositorio;
	
	public Sobre salvar(Sobre sobre) {
		return sobreRepositorio.save(sobre);
	}
	
	public List<Sobre> buscarTodos() {
		return sobreRepositorio.findAll();
	}
	
	public Sobre buscaDetalheSobre() {
		Object[][] obj = sobreRepositorio.buscaDetalheSobre();
		Sobre sobre = new Sobre();;
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
