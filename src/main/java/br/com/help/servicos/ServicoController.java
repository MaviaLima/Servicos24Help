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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/servicos")
public class ServicoController {
	@Autowired
	private ServicoService service;
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/")
	public ModelAndView pesquisar(Servico servico, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("cadastros/servicos-list");
		if (servico == null || servico.getId() == null) {
			mv.addObject("lista", service.listarTodos());
		}else {
			mv.addObject("listaPorDescricao", service.buscarPorDescricao(servico.getDescricao()));
		}
	    mv.addObject("listaCategorias", categoriaService.listarTodas());
		mv.addObject("servico", servico);
	 //setando mensagens de erro no template
	    mv.addObject("mensagemErro", ra.getFlashAttributes().get("mensagemErro"));
	    mv.addObject("mensagemSucesso", ra.getFlashAttributes().get("mensagemSucesso"));
	    return mv;
		
	}

	@GetMapping("/novo")
	public String exibirForm(@ModelAttribute Servico servico) {
		return "redirect:/servicos/";
	}

	@RequestMapping(value="/saveList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView salvarPesquisarServico(@Valid @ModelAttribute Servico servico, @RequestParam(value="action", 
		required=false) String action, Errors errors, RedirectAttributes ra) {
		
		if (action != null && action.equals("salvar")) {
			return salvar(servico, errors, ra);
		} else {
			return pesquisar(servico, ra);
		}
	}

	@PostMapping("/salvar")
	private ModelAndView salvar(@Valid @ModelAttribute Servico servico, Errors errors, RedirectAttributes ra) {
		
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível salvar serviço: " + errors.getFieldErrors());
		} else {
			try {
				service.salvarServico(servico);
				ra.addFlashAttribute("mensagemSucesso", "Servico salvo com sucesso");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível salvar serviço: " + e.getMessage());
			}
		}
		return pesquisar(new Servico(), ra);
	}

	
	@GetMapping("/editar/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") int id) {
		Servico servico = service.buscarPorId(id);
		ModelAndView mv = new ModelAndView("cadastros/servicos-list");
		
		mv.addObject("lista", service.listarTodos());
		mv.addObject("listaCategorias", categoriaService.listarTodas());
		mv.addObject("servico", servico);

		return mv;
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") int id, RedirectAttributes ra) {
		service.excluir(id);
		ra.addFlashAttribute("mensagemSucesso", "Serviço removido com sucesso");
		return "redirect:/servicos/";		
	
	}

}
