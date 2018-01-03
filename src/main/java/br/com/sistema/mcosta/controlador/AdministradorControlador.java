package br.com.sistema.mcosta.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistema.mcosta.entidade.IdentificacaoSobreDetalhe;
import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.entidade.SobreDetalhe;
import br.com.sistema.mcosta.servico.IdentificacaoSobreDetalheBO;
import br.com.sistema.mcosta.servico.SobreBO;
import br.com.sistema.mcosta.servico.SobreDetalheBO;

@Controller
@RequestMapping("/administracao")
public class AdministradorControlador {
	
	@Autowired
	private SobreBO sobreBO;
	
	@Autowired
	private SobreDetalheBO sobreDetalheBO;
	
	@Autowired
	private IdentificacaoSobreDetalheBO identificacaoSobreDetalheBO;

	@GetMapping("/sobre/novo")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("cadastroSobre");
		mv.addObject(new Sobre());
		return mv;
	}
	
	@PostMapping(value = "/sobre")
	public String salvar(@Validated Sobre sobre, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "cadastroSobre";
		}
		
		Sobre sobreSalvo = sobreBO.salvar(sobre);
		
		SobreDetalhe sobreDetalhe = salvarSobreDetalhe(sobre.getSobreDetalhe1().getNome(), sobre.getSobreDetalhe1().getDescricao(), sobre.getSobreDetalhe1().getIcone());
		salvarIdentificacaoSobreDetalhe(sobreSalvo, sobreDetalhe);

		sobreDetalhe = salvarSobreDetalhe(sobre.getSobreDetalhe2().getNome(), sobre.getSobreDetalhe2().getDescricao(), sobre.getSobreDetalhe2().getIcone());
		salvarIdentificacaoSobreDetalhe(sobreSalvo, sobreDetalhe);
		
		sobreDetalhe = salvarSobreDetalhe(sobre.getSobreDetalhe3().getNome(), sobre.getSobreDetalhe3().getDescricao(), sobre.getSobreDetalhe3().getIcone());
		salvarIdentificacaoSobreDetalhe(sobreSalvo, sobreDetalhe);
		
		attributes.addFlashAttribute("mensagem", "Sobre salvo com sucesso!");
		return "redirect:/administracao/sobre/novo";
	}

	private void salvarIdentificacaoSobreDetalhe(Sobre sobre, SobreDetalhe sobreDetalhe) {
		IdentificacaoSobreDetalhe detalhe = new IdentificacaoSobreDetalhe(sobre, sobreDetalhe);
		identificacaoSobreDetalheBO.salvar(detalhe);
	}

	private SobreDetalhe salvarSobreDetalhe(String nome, String descricao, String icone) {
		SobreDetalhe sobreDetalhe = new SobreDetalhe(nome, descricao, icone);
		return sobreDetalheBO.salvar(sobreDetalhe);
	}

}