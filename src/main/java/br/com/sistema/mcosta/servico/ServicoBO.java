package br.com.sistema.mcosta.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.repositorio.IServicoRepositorio;

@Service
public class ServicoBO {

    @Autowired
    private IServicoRepositorio servicoRepositorio;

    public void salvar(Servico servico) {
        servicoRepositorio.save(servico);
    }

    public void excluir(Long id) {
        excluir(new Servico(id));
    }

    public void excluir(Servico servico) {
        servicoRepositorio.delete(servico);
    }

    public List<Servico> buscarTodos() {
        return servicoRepositorio.findAll();
    }
}
