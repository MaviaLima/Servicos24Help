package br.com.help.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repositorio;

//	@Autowired
//	private CategoriaRepository categoriaRepositorio;
	
	public Servico salvarServico(Servico servico) {
		
		//if (entity.getId() == null ) {
			//throw new Exception("Já existe servico com esta descrição");
		//}
		return repositorio.saveAndFlush(servico);
	}


	public List<Servico> listarTodos() {
		return repositorio.findAll();
	}

	/*
	 * public List<Servico> buscarPorCategoria(ECategoria categoria) { return
	 * repositorio.findByCategoria(categoria); }
	 * 
	 * public List<Servico> buscarPorDescricaoCategoria(String descricao, ECategoria
	 * categoria) { return repositorio.findByDescricaoCategoria(descricao,
	 * categoria); }
	 */	
	public void excluir(Integer id) {
		repositorio.deleteById(id);
	}

	public Servico buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<Servico> buscarPorCategoria(Categoria cat) {
		return repositorio.findByCategoria(cat);
	}

	public List<Servico> buscarPorDescricao(String descricao) {
		return repositorio.findByDescricao(descricao);
	}

	
}
