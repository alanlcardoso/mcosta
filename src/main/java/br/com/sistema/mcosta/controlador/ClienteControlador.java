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

import br.com.sistema.mcosta.entidade.Cliente;
import br.com.sistema.mcosta.servico.ClienteBO;

@Controller
@RequestMapping("/administracao/cliente")
public class ClienteControlador {

	private static final String CADASTRO = "cliente/novo";
	private static final String PESQUISAR = "cliente/pesquisar";

	@Autowired
	private ClienteBO clienteBO;

	@GetMapping("novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(new Cliente());
		return mv;
	}

	@PostMapping(value = "/administracao/cliente")
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO;
		}

		clienteBO.salvar(cliente);
		attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
		return "redirect:/administracao/cliente";
	}

	@GetMapping()
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(PESQUISAR);
		mv.addObject("clientes", clienteBO.buscarTodosReduzido());
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		Cliente entidade = clienteBO.buscaPorId(id);
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(entidade);
		return mv;
	}

}