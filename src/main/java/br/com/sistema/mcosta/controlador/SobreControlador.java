package br.com.sistema.mcosta.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SobreControlador {

	@GetMapping("/administracao/sobre/novo")
	public ModelAndView cadastro() {
		return new ModelAndView("CadastroSobre");
	}

}
