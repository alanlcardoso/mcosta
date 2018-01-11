package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.mcosta.entidade.Contato;
import br.com.sistema.mcosta.repositorio.IContatoRepositorio;

@Service
public class ContatoBO {

    @Autowired
    private IContatoRepositorio contatoRepositorio;

    public void salvar(Contato contato) {
    	contatoRepositorio.save(contato);
    }

    public void excluir(Long id) {
    	contatoRepositorio.delete(id);
    }

    public void excluir(Contato contato) {
    	contatoRepositorio.delete(contato);
    }

    @Transactional(readOnly = true)
    public List<Contato> buscarTodos() {
        return contatoRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public Contato buscarPorId(Long id) {
        return contatoRepositorio.findOne(id);
    }
}
