package br.com.sistema.mcosta.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistema.mcosta.entidade.Contato;
import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.entidade.Sobre;
import br.com.sistema.mcosta.mail.Mailer;
import br.com.sistema.mcosta.mail.Mensagem;
import br.com.sistema.mcosta.servico.ClienteBO;
import br.com.sistema.mcosta.servico.ContatoBO;
import br.com.sistema.mcosta.servico.ServicoBO;
import br.com.sistema.mcosta.servico.SobreBO;

@Controller
public class IndexControlador {
	
	private static final String INDEX = "index";
	private static final String SERVICO_DETALHE = "servicoDetalhe";

	@Autowired
	private SobreBO sobreBO;
	
	@Autowired
	private ClienteBO clienteBO;
	
	@Autowired
	private ServicoBO servicoBO;
	
	@Autowired
	private ContatoBO contatoBO;

	@GetMapping("/")
	public ModelAndView index() {
		
		List<Sobre> sobres = sobreBO.buscaDetalheSobre();		
		Sobre sobre = null;
		if (!sobres.isEmpty()) {
			sobre = sobres.get(0);
		}
		
		Page<Servico> servicos = servicoBO.buscarClientesPorPagina(6);
		servicos.forEach(servico -> {
			String descricao = servico.getDescricao();
			int tamanho = 110;
			if (descricao.length() >= tamanho) {
				servico.setDescricao(descricao.substring(0, tamanho) + "...");
			}
		});
		
		ModelAndView mv = new ModelAndView(INDEX);
		mv.addObject("sobre", sobre);
		mv.addObject("totalCliente", clienteBO.buscarTotalCliente());
		mv.addObject("clientes", clienteBO.buscarClientesPorPagina(12));
		mv.addObject("totalServico", servicos);
		
		List<Contato> contatos = contatoBO.buscarTodos();		
		mv.addObject("contato", contatos.isEmpty() ? null : contatos.get(0));
		mv.addObject(new Mensagem());
		return mv;
	}
	
	@PostMapping("/enviar")
	public ModelAndView enviar(@Validated Mensagem mensagem, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(INDEX);
		
		if (errors.hasErrors()) {
			return mv;
		}
		
		String corpo = "Olá!\n\n"
				+ mensagem.getNome() + "\n"
				+ mensagem.getRemetente() + "\n\n"
				+ mensagem.getCorpo();
		
		mensagem.setCorpo(corpo);
		mensagem.setDestinatarios(Arrays.asList("pedrojr.19@hotmail.com"));
		
		Mailer mailer = new Mailer();
		mailer.enviar(mensagem);
		
		mv.addObject("mensagem", "Salvo com sucesso!");
		return mv;
	}
	
	@GetMapping("/servico/{id}")
	public ModelAndView detalheServico(@PathVariable Long id) {
		Servico servico = servicoBO.buscarPorId(id);
		
		ModelAndView mv = new ModelAndView(SERVICO_DETALHE);
		mv.addObject("servicos", servico);
		return mv;
	}
	
	@GetMapping("/servico")
	public ModelAndView detalheServico() {
		List<Servico> servicos = servicoBO.buscarTodos();
		
		ModelAndView mv = new ModelAndView(SERVICO_DETALHE);
		mv.addObject("servicos", servicos);
		return mv;
	}

}
