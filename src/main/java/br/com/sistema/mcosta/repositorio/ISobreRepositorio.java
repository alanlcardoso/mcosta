package br.com.sistema.mcosta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.mcosta.entidade.Sobre;

public interface ISobreRepositorio extends JpaRepository<Sobre, Long> {
	
	public static final String QUERY = 
			"select " +
				"s.id, " +
				"s.titulo, " +
				"s.descricao, " +
				"(select sd.id from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 0) as id_detalhe_1, " +
				"(select sd.nome from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 0) as nome_detalhe_1, " +
				"(select sd.descricao from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 0) as descricao_detalhe_1, " +
			    "(select sd.icone from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 0) as icone_detalhe_1, " +
			    "(select sd.id from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 1) as id_detalhe_2, " +
			    "(select sd.nome from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 1) as nome_detalhe_2, " +
			    "(select sd.descricao from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 1) as descricao_detalhe_2, " +
			    "(select sd.icone from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 1) as icone_detalhe_2, " +
			    "(select sd.id from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 2) as id_detalhe_3, " +
			    "(select sd.nome from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 2) as nome_detalhe_3, " +
			    "(select sd.descricao from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 2) as descricao_detalhe_3, " +
			    "(select sd.icone from sobre_detalhe sd where sd.id = (select i.id_sobre_detalhe from identificacao_sobre_detalhe i where i.id_sobre = s.id and i.id_sobre_detalhe = sd.id) limit 1 offset 2) as icone_detalhe_3 " +
		    "from sobre s";
	@Query(value = QUERY, nativeQuery = true)
	Object[][] buscaDetalheSobre();
	
	@Query(value = QUERY + " where s.id = ?", nativeQuery = true)
	Object[][] buscaDetalheSobrePorId(Long id);
	
}