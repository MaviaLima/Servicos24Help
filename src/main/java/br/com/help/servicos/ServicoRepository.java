package br.com.help.servicos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

	//@Query("select s from Servico s, Categoria c where s.Servico.id = c.idServico = s.id order by s.descricao")
	//public List<Servico> findByCategoria(Integer idCategoria);
	
	public boolean existsByDescricao(String descricao);

//	public List<Servico> findByCategoria(ECategoria cat);

	@Query("select s from Servico s where s.categoria = :categoria")// order by s.descricao")
	public List<Servico> findByCategoria(ECategoria categoria);

	@Query("select s from Servico s where s.descricao like %:descricao% order by s.descricao")
	public List<Servico> findByDescricao(String descricao);
	
	
}
