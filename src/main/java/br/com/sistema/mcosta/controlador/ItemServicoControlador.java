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

import br.com.sistema.mcosta.entidade.Imagem;
import br.com.sistema.mcosta.entidade.ImagemItemServico;
import br.com.sistema.mcosta.entidade.ItemServico;
import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.servico.ImagemBO;
import br.com.sistema.mcosta.servico.ItemServicoBO;
import br.com.sistema.mcosta.servico.ServicoBO;
import br.com.sistema.mcosta.util.Util;

@Controller
@RequestMapping("/administracao/servico")
public class ItemServicoControlador extends AbstractControlador {

	private static final String CADASTRO = "servico/novoItemServico";
	private static final String UPLOAD = "servico/imagem";

	private ItemServico itemServico;

	@Autowired
	private ServicoBO servicoBO;
	
	@Autowired
	private ItemServicoBO itemServicoBO;

	@Autowired
	private ImagemBO imagemBO;
	
	private Long idEntidade;
	
	@GetMapping("/{id}/novo/item")
	public ModelAndView cadastro(@PathVariable String id) {
		ModelAndView mv = new ModelAndView(CADASTRO);
		this.itemServico = new ItemServico();
		mv.addObject(this.itemServico);
		mv.addObject("idServico", id);
		return mv;
	}

	@PostMapping("/{id}/item")
	public ModelAndView salvar(@PathVariable String id, @Validated ItemServico itemServico, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(CADASTRO);
		
		Servico servico = servicoBO.buscarPorId(super.decodificarBase64Long(id));
		itemServico.setServico(servico);

		mv.addObject("idServico", id);
		if (errors.hasErrors()) {
			return mv;
		}

		itemServicoBO.salvar(itemServico);
		mv.addObject("mensagem", "Salvo com sucesso!");
		mv.addObject(new ItemServico());
		return mv;
	}

	@GetMapping("/{id}/item")
	public ModelAndView edicao(@PathVariable String id) {
		this.itemServico = itemServicoBO.buscarPorId(super.decodificarBase64Long(id));
		ModelAndView mv = new ModelAndView(CADASTRO);
		mv.addObject(this.itemServico);
		mv.addObject("idServico", this.itemServico.getServico().getId());
		return mv;
	}

	@PostMapping(value = "/excluir/item", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> excluir(@RequestBody @Valid String id) {
		if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Favor selecionar um Item.");
		}
		itemServicoBO.excluir(super.decodificarBase64Long(id));
		return ResponseEntity.ok("Registro excluído com sucesso.");
	}
	
	@GetMapping("/{id}/item/upload")
	public ModelAndView upload(@PathVariable String id) {
		this.idEntidade = super.decodificarBase64Long(id);		
		ModelAndView mv = new ModelAndView(UPLOAD);
		mv.addObject(new Imagem());
		mv.addObject("idEntidade", super.decodificarBase64Long(id));
		mv.addObject("imagensItemServico", itemServicoBO.buscarPorId(super.decodificarBase64Long(id)).getImagensItemServico());
		return mv;
	}
	
	@PostMapping(value = "/{id}/item/upload")
	public ModelAndView tratarArquivoUpload(@RequestParam("file") MultipartFile file, @PathVariable String id) {
		ModelAndView mv = new ModelAndView(UPLOAD);
		
		mv.addObject(new Imagem());
		mv.addObject("idEntidade", super.decodificarBase64Long(id));
		mv.addObject("imagensItemServico", itemServicoBO.buscarPorId(super.decodificarBase64Long(id)).getImagensItemServico());
		
		try {

			if (file.isEmpty() || !(file.getOriginalFilename().endsWith("jpg") || file.getOriginalFilename().endsWith("png")
					|| file.getOriginalFilename().endsWith("jpeg"))) {
				mv.addObject("imagem", new Imagem());
				mv.addObject("idEntidade", super.decodificarBase64Long(id));
				mv.addObject("mensagemErro", "Logo inválido.");
				return mv;
			}

			if (file.getBytes().length >= 65535) {
				mv.addObject("imagem", new Imagem());
				mv.addObject("idEntidade", super.decodificarBase64Long(id));
				mv.addObject("mensagemErro", "Máximo permitido 65KB!");
				return mv;
			}
			
			Imagem imagem = new Imagem();
			imagem.setFoto(Util.toObjects(file.getBytes()));
			imagem = imagemBO.salvar(imagem);
			
			ItemServico itemServico = itemServicoBO.buscarPorId(super.decodificarBase64Long(id));
			
			ImagemItemServico imagemItemServico = new ImagemItemServico();
			imagemItemServico.setImagem(imagem);
			imagemItemServico.setItemServico(itemServico);
			imagemBO.salvar(imagemItemServico);

		} catch (IOException e) {
			System.out.println("Erro ao tentar ler arquivo para verificar se é imagem.");
		}

		return mv;
	}
	
	@GetMapping("/item/{idItem}/imagem/{idImagem}/excluir")
	public ModelAndView excluir(@PathVariable Long idItem, @PathVariable Long idImagem) {
		ImagemItemServico imagemItemServico = imagemBO.buscarPorId(idItem, idImagem);
		imagemBO.excluir(idImagem);
		imagemBO.excluirImagemItemServico(imagemItemServico.getId());		
		return upload(super.codificarBase64(this.idEntidade));
	}

}