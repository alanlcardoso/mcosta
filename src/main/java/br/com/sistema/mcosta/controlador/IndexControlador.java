package br.com.sistema.mcosta.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.servico.SobreBO;

@Controller
public class IndexControlador {
	
	@Autowired
	private SobreBO sobreBO;

	@GetMapping("/")
	public ModelAndView index() {
		Sobre sobre = sobreBO.buscaDetalheSobre();
		
		ModelAndView mv= new ModelAndView("index");
		mv.addObject("sobre", sobre);
		return mv;
	}

}
