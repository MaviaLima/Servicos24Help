package br.com.help.servicos;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/servicos")
public class ServicoController {
	@Autowired
	private ServicoService service;
	@Autowired
	private CategoriaService categoriaService;
			
	@GetMapping("/")
	public ModelAndView listarServicos() {
		ModelAndView mv = new ModelAndView("servico/servico-list");
		
		mv.addObject("lista", service.listarTodos());
		mv.addObject("listaCategorias", categoriaService.listarTodas());

//		mv.addObject("listaServicos",service.buscarPorCategoria(categoria.nome, categoria.id));
		return mv;
	}

	@GetMapping("/novo")
	public String exibirForm(@ModelAttribute Servico servico) {
		return "servico/servico-form";
	}

	@PostMapping("/salvar")
	public String salvarServico(@Valid @ModelAttribute Servico servico, 
			Errors result) {
		if(result.hasErrors()) {
			return "servico/servico-form";
		}
		service.salvarServico(servico);
		return "redirect:/servicos/";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") int id) {
		Servico s = service.buscarPorId(id);
		ModelAndView mv = new ModelAndView("servico/servico-form");
		mv.addObject("servico", s);
		return mv;
	}

	@GetMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") int id) {
		service.excluir(id);
		return listarServicos();
	}

}
