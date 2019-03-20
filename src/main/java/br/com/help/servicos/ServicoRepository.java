package br.com.help.servicos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

	public boolean existsByDescricao(String descricao);

	@Query("select s from Servico s where s.categoria = :categoria")
	public List<Servico> findByCategoria(Categoria categoria);

	@Query("select s from Servico s where s.descricao like %:descricao% order by s.descricao")
	public List<Servico> findByDescricao(String descricao);
	
	
}
