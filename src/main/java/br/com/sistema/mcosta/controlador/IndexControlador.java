package br.com.sistema.mcosta.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.servico.ClienteBO;
import br.com.sistema.mcosta.servico.ServicoBO;
import br.com.sistema.mcosta.servico.SobreBO;

@Controller
public class IndexControlador {

	@Autowired
	private SobreBO sobreBO;
	
	@Autowired
	private ClienteBO clienteBO;
	
	@Autowired
	private ServicoBO servicoBO;

	@GetMapping("/")
	public ModelAndView index() {
		
		List<Sobre> sobres = sobreBO.buscaDetalheSobre();		
		Sobre sobre = null;
		if (!sobres.isEmpty()) {
			sobre = sobres.get(0);
		}
		
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("sobre", sobre);
		mv.addObject("totalCliente", clienteBO.buscarTotalCliente());
		mv.addObject("totalServico", servicoBO.buscarTodos());
		mv.addObject("contato", null);
		return mv;
	}

}
