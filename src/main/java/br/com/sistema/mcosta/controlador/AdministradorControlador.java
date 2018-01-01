package br.com.sistema.mcosta.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.servico.SobreBO;

@Controller
@RequestMapping("/administracao")
public class AdministradorControlador {
	
	@Autowired
	private SobreBO sobreBO;

	@GetMapping("/sobre/novo")
	public ModelAndView cadastro() {
		return new ModelAndView("cadastroSobre");
	}
	
	@PostMapping(value = "/sobre")
	public ModelAndView salvar(Sobre sobre) {
		sobreBO.salvar(sobre);
		
		ModelAndView mv = new ModelAndView("cadastroSobre");
		mv.addObject("mensagem", "Sobre salvo com sucesso!");
		return mv;
	}

}