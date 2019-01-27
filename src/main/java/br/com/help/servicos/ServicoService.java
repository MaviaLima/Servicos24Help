package br.com.help.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repositorio;

	@Autowired
	private CategoriaRepository categoriaRepositorio;
	
	public <S extends Servico> S salvarServico(S entity) {
		return repositorio.save(entity);
	}
	
	public List<Servico> listarTodos() {
		return repositorio.findAll();
	}

//	public List<Servico> buscarPorCategoria(Integer idCategoria) {
//		return repositorio.findByCategoria(idCategoria);
//	}
//	
	public void excluir(Integer id) {
		repositorio.deleteById(id);
	}

	public Servico buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	
}
