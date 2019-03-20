package br.com.help.restcontroller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/entityCategorias")
	public ResponseEntity<List<Categoria>> categorias(){ 
	    	List<Categoria> categorias = categoriaService.listarTodas();
		
		if (categorias != null && !categorias.isEmpty()) {
			return ResponseEntity.ok(categorias);
				
		} else {
				return ResponseEntity.notFound().build();
		}

	}
	
	@GetMapping("/entityServicos")
	public ResponseEntity<List<Servico>> servicos(){ 
    	List<Servico> servicos = service.listarTodos();
	
    	if (servicos != null && !servicos.isEmpty()) {
    		return ResponseEntity.ok(servicos);
			
    	} else {
			return ResponseEntity.notFound().build();
    	}

	}

	
	@PostMapping("/servico/novo") 
	public ResponseEntity<String> novoServico(@RequestBody Servico newServico) { 
		
		if (newServico != null) {
			List<Servico> serv = service.buscarPorDescricao(newServico.getDescricao());
			if (serv == null) {
				if (service.salvarServico(newServico)!= null) {
					return new ResponseEntity<String>(HttpStatus.OK);
				}
			}else {
				return ResponseEntity.badRequest()
			            .body("Serviço já existente!");
			}
		} 
		
		return null;
	
	}
	

}
