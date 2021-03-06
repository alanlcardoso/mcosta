package br.com.sistema.mcosta.controlador;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistema.mcosta.entidade.Cliente;
import br.com.sistema.mcosta.servico.ClienteBO;
import br.com.sistema.mcosta.servico.ServicoBO;
import br.com.sistema.mcosta.util.Util;

@Controller
@RequestMapping("/administracao/cliente")
public class ClienteControlador extends AbstractControlador {

	private static final String CADASTRO = "cliente/novo";
	private static final String PESQUISAR = "cliente/pesquisar";
	private static final String UPLOAD = "cliente/logo";
	private Cliente cliente;

	@Autowired
	private ClienteBO clienteBO;

	@Autowired
	private ServicoBO servicoBO;

	@GetMapping("novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO);
		this.cliente = new Cliente();
		mv.addObject(this.cliente);
		mv.addObject("servicos", servicoBO.buscarTodos());
		return mv;
	}

	@PostMapping()
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
	public ModelAndView edicao(@PathVariable String id) {
		this.cliente = clienteBO.buscaPorId(super.decodificarBase64Long(id));
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(this.cliente);
		mv.addObject("servicos", servicoBO.buscarTodos());
		return mv;
	}

	@GetMapping("/upload/{id}")
	public ModelAndView upload(@PathVariable String id) {
		this.cliente = clienteBO.buscaPorId(super.decodificarBase64Long(id));
		ModelAndView mv = new ModelAndView(UPLOAD);
		mv.addObject(this.cliente);
		return mv;
	}

	@PostMapping(value = "/excluir", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> excluir(@RequestBody @Valid String id) {
		if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Favor selecionar um Item.");
		}
		clienteBO.excluir(Long.parseLong(id));
		return ResponseEntity.ok("Registro excluído com sucesso.");
	}

	@PostMapping(value = "/upload")
	public ModelAndView tratarArquivoUpload(@RequestParam("file") MultipartFile file, ModelAndView mv) {

		try {

			mv.addObject(this.cliente);
			if (file.isEmpty() || !(file.getOriginalFilename().endsWith("jpg") || file.getOriginalFilename().endsWith("png")
					|| file.getOriginalFilename().endsWith("jpeg"))) {
				mv.addObject("mensagemErro", "Logo inválido.");
				mv.setViewName(UPLOAD);
				return mv;
			}

			if (this.cliente == null) {
				this.cliente = new Cliente();
			}

			if (file.getBytes().length >= 16777215) {
				mv.addObject("mensagemErro", "Máximo permitido 16MB!");
				mv.setViewName(UPLOAD);
				return mv;
			}

			cliente.setLogo(Util.toObjects(file.getBytes()));
			clienteBO.salvarImagem(cliente);

		} catch (IOException e) {
			System.out.println("Erro ao tentar ler arquivo para verificar se é imagem.");
		}

		return upload(this.cliente.getIdBase64());
	}
}