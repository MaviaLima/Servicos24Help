package br.com.help.servicos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository 
		extends JpaRepository<Categoria, Integer> {

	public boolean existsByNome(String nome);
	
	//@Query("select d from Servico d where d.categoria.id = :idCategoria order by d.descricao")
}
