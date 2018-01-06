package br.com.sistema.mcosta.controlador;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistema.mcosta.entidade.Cliente;
import br.com.sistema.mcosta.servico.ClienteBO;

@Controller
@RequestMapping("/administracao/cliente")
public class ClienteControlador {

	private static final String CADASTRO = "cliente/novo";
	private static final String PESQUISAR = "cliente/pesquisar";
	private static final String UPLOAD = "cliente/logo";
	private Cliente cliente;
	@Autowired
	private ClienteBO clienteBO;

	@GetMapping("novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO);
		this.cliente = new Cliente();
		mv.addObject(this.cliente);
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
	public ModelAndView edicao(@PathVariable Long id) {
		this.cliente = clienteBO.buscaPorId(id);
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(this.cliente);
		return mv;
	}

	@GetMapping("/upload/{id}")
	public ModelAndView upload(@PathVariable Long id) {
		this.cliente = clienteBO.buscaPorId(id);
		ModelAndView mv = new ModelAndView(UPLOAD);
		mv.addObject(this.cliente);
		return mv;
	}

	@GetMapping("excluir/{id}")
	public ModelAndView excluir(@PathVariable Long id) {
		clienteBO.excluir(id);
		return pesquisar();
	}

	@PostMapping(value = "/upload")
	public ModelAndView tratarArquivoUpload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) Long clienteId,
			ModelAndView mv) {

		try {

			mv.addObject(this.cliente);
			if (file.isEmpty() || !(file.getOriginalFilename().endsWith("jpg") || file.getOriginalFilename().endsWith("png")
					|| file.getOriginalFilename().endsWith("jpeg"))) {
				mv.addObject("mensagemErro", "Logo inválido.");
				mv.setViewName(UPLOAD);
				return mv;
			}

			byte[] bytes = file.getBytes();
			Byte[] imagem = new Byte[bytes.length];
			Arrays.setAll(imagem, n -> bytes[n]);

			if (this.cliente == null) {
				this.cliente = new Cliente();
			}
			cliente.setLogo(imagem);
			clienteBO.salvarImagem(cliente);

		} catch (IOException e) {
			System.out.println("Erro ao tentar ler arquivo para verificar se é imagem.");
		}

		return pesquisar();
	}
}