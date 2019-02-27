package br.com.help.restcontroller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.help.security.Usuario;
import br.com.help.security.UsuarioService;
import br.com.help.servicos.Categoria;
import br.com.help.servicos.CategoriaService;
import br.com.help.servicos.Servico;
import br.com.help.servicos.ServicoService;

@RestController

@RequestMapping("/rest/")
public class RestServico {

	@Autowired
	private ServicoService service;
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/servicos")
	public List<Servico> listarServicos() {
		return  service.listarTodos();
	}
	
	@GetMapping("/categorias")
	public List<Categoria> listarCategorias() {
		return  categoriaService.listarTodas();
	}

	@GetMapping("/usuarios")
	public List<Usuario> listarUsuarios() {
		 return usuarioService.listarTodos();	
	}
	
	@PostMapping("/servico") 
	public Servico newServico(@RequestBody Servico newServico) { 
	   return service.salvarServico(newServico); 
	} // Single item 

	
}
