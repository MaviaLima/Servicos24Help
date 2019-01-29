package br.com.help.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repositorio;

	public Categoria salvar(Categoria categoria) throws Exception {
		if (categoria.getId() == null && repositorio.existsByNome(categoria.getNome())) {
			throw new Exception("Já existe categoria com este nome");
		}
		return repositorio.saveAndFlush(categoria);
	}

	public Categoria buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public List<Categoria> listarTodas() {
		return repositorio.findAll(Sort.by("nome"));
	}
	
	public Categoria excluir(Integer id) {
		Categoria cat = buscarPorId(id);
		repositorio.deleteById(id);
		return cat;
	}

	
	
}
