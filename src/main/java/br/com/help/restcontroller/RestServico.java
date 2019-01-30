package br.com.help.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.help.servicos.Servico;
import br.com.help.servicos.ServicoService;

@RestController
@RequestMapping("/rest/servicos")
public class RestServico {

	@Autowired
	private ServicoService service;
	
	@GetMapping("/")
	public List<Servico> listarServicos() {
		return  service.listarTodos();
	}
	
	/*
	 * @GetMapping("/{id}") public ModelAndView exibirEdicao(@PathVariable("id") int
	 * id) { Servico s = service.buscarPorId(id); ModelAndView mv = new
	 * ModelAndView("servico/servico-form"); mv.addObject("servico", s); return mv;
	 * }
	 * 
	 * @GetMapping("/salvar/{id}") public String
	 * salvarServico(@Valid @ModelAttribute Integer id, Errors result) throws
	 * Exception { Servico s = service.buscarPorId(id); if(result.hasErrors()) {
	 * return "servico/servico-form"; } service.salvarServico(s); return
	 * "redirect:/servicos/"; }
	 */	

	@GetMapping("/remover/{id}")
	public void remover(@PathVariable("id") int id) {
		service.excluir(id);
	}
	
}
