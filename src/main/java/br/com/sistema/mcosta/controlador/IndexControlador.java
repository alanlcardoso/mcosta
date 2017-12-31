package br.com.sistema.mcosta.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexControlador {

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

}
