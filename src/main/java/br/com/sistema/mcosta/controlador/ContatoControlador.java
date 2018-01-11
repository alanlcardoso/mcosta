package br.com.sistema.mcosta.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistema.mcosta.entidade.Contato;
import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.enums.Estado;
import br.com.sistema.mcosta.enums.TipoLogradouro;
import br.com.sistema.mcosta.servico.ContatoBO;

@Controller
@RequestMapping("/administracao/contato")
public class ContatoControlador {

	private static final String CADASTRO = "contato/novo";
	private static final String PESQUISAR = "contato/pesquisar";
	
	private Contato contato;
	
	@Autowired
	private ContatoBO contatoBO;

	@GetMapping("/novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO);
		this.contato = new Contato();
		mv.addObject(this.contato);
		return mv;
	}

	@PostMapping()
	public ModelAndView salvar(@Validated Contato contato, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(CADASTRO);
		
		if (errors.hasErrors()) {
			return mv;
		}
		
		contatoBO.salvar(contato);
		mv.addObject("mensagem", "Salvo com sucesso!");
		mv.addObject(new Servico());
		return mv;
	}

	@GetMapping()
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(PESQUISAR);
		mv.addObject("contatos", contatoBO.buscarTodos());
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		this.contato = contatoBO.buscarPorId(id);
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(this.contato);
		return mv;
	}
	
	@ModelAttribute("tiposLogradouro")
	public List<TipoLogradouro> tiposLogradouro() {
		return Arrays.asList(TipoLogradouro.values());
	}
	
	@ModelAttribute("estados")
	public List<Estado> estados() {
		return Arrays.asList(Estado.values());
	}
	
}