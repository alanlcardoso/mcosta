package br.com.sistema.mcosta.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.servico.ItemServicoBO;
import br.com.sistema.mcosta.servico.ServicoBO;
import br.com.sistema.mcosta.util.Validacao;

@Controller
@RequestMapping("/administracao/servico")
public class ServicoControlador extends AbstractControlador {

	private static final String CADASTRO = "servico/novo";
	private static final String PESQUISAR = "servico/pesquisar";

	private Servico servico;

	@Autowired
	private ServicoBO servicoBO;
	
	@Autowired
	private ItemServicoBO itemServicoBO;

	@GetMapping("/novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO);
		this.servico = new Servico();
		mv.addObject(this.servico);
		return mv;
	}

	@PostMapping()
	public ModelAndView salvar(@Validated Servico servico, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(CADASTRO);

		if (errors.hasErrors()) {
			return mv;
		}

		if (!Validacao.iconeValido(servico.getImagem())) {
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
	public ModelAndView edicao(@PathVariable String id) {
		this.servico = servicoBO.buscarPorId(super.decodificarBase64Long(id));
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(this.servico);
		mv.addObject("itensServico", itemServicoBO.buscarPorIdServico(super.decodificarBase64Long(id)));
		return mv;
	}

	@PostMapping(value = "/excluir", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> excluir(@RequestBody @Valid String id) {
		if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Favor selecionar um Item.");
		}
		servicoBO.excluir(super.decodificarBase64Long(id));
		return ResponseEntity.ok("Registro excluído com sucesso.");
	}

}