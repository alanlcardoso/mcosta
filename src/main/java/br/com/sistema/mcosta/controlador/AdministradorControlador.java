package br.com.sistema.mcosta.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.servico.SobreBO;

@Controller
@RequestMapping("/administracao")
public class AdministradorControlador {
	
	private static final String CADASTRO_SOBRE = "cadastroSobre";
	
	@Autowired
	private SobreBO sobreBO;
	
	@GetMapping("/sobre/novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO_SOBRE);
		mv.addObject(new Sobre());
		return mv;
	}
	
	@PostMapping(value = "/sobre")
	public ModelAndView salvar(@Validated Sobre sobre, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(CADASTRO_SOBRE);
		
		if (errors.hasErrors()) {
			return mv;
		}
		
		if(!iconeValido(sobre.getSobreDetalhe1().getIcone())) {
			mv.addObject("mensagemErro", "Ícone do 1º detalhe com formato inválido!");
			return mv;
		}
		
		if(!iconeValido(sobre.getSobreDetalhe2().getIcone())) {
			mv.addObject("mensagemErro", "Ícone do 2º detalhe com formato inválido!");
			return mv;
		}
		
		if(!iconeValido(sobre.getSobreDetalhe3().getIcone())) {
			mv.addObject("mensagemErro", "Ícone do 3º detalhe com formato inválido!");
			return mv;
		}
		
		sobreBO.salvarSobre(sobre);
		mv.addObject("mensagem", "Sobre salvo com sucesso!");
		return mv;
	}
	
	private boolean iconeValido(String icone) {
		return icone.startsWith("fa-");
	}
	
	@GetMapping("/sobre")
	public ModelAndView pesquisar() {
		List<Sobre> sobres = sobreBO.buscaDetalheSobre();
		
		ModelAndView mv= new ModelAndView("pesquisaSobre");
		mv.addObject("todosSobre", sobres);
		return mv;
	}

	@GetMapping("/sobre/{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		Sobre sobre = sobreBO.buscaDetalheSobrePorId(id);
		
		ModelAndView mv = new ModelAndView(CADASTRO_SOBRE);
		mv.addObject(sobre);
		return mv;
	}

}