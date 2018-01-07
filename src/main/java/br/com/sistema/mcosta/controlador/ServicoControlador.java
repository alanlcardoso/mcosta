package br.com.sistema.mcosta.controlador;

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

import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.servico.ServicoBO;
import br.com.sistema.mcosta.util.Validacao;

@Controller
@RequestMapping("/administracao/servico")
public class ServicoControlador {

	private static final String CADASTRO = "servico/novo";
	private static final String PESQUISAR = "servico/pesquisar";
	
	private Servico servico;
	
	@Autowired
	private ServicoBO servicoBO;

	@GetMapping("/novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO);
		this.servico  = new Servico();
		mv.addObject(this.servico);
		return mv;
	}

	@PostMapping()
	public ModelAndView salvar(@Validated Servico servico, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(CADASTRO);
		
		if (errors.hasErrors()) {
			return mv;
		}
		
		if(!Validacao.iconeValido(servico.getImagem())) {
			mv.addObject("mensagemErro", "Ícone com formato inválido!");
			return mv;
		}
		
		servicoBO.salvar(servico);
		mv.addObject("mensagem", "Salvo com sucesso!");
		mv.addObject(new Servico());
		return mv;
	}

	@GetMapping()
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(PESQUISAR);
		mv.addObject("servicos", servicoBO.buscarTodos());
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		this.servico = servicoBO.buscarPorId(id);
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(this.servico);
		return mv;
	}

	@GetMapping("excluir/{id}")
	public ModelAndView excluir(@PathVariable Long id) {
		servicoBO.excluir(id);
		return pesquisar();
	}
	
}