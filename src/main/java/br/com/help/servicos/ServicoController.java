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
//	@Autowired
//	private CategoriaService categoriaService;
			
	@GetMapping("/list")
	public ModelAndView pesquisar(Servico servico, RedirectAttributes ra) {
			ModelAndView mv = new ModelAndView("cadastros/servico-list");
		
		mv.addObject("lista", service.listarTodos());
		mv.addObject("listaCategorias", ECategoria.values());
		mv.addObject("servico", servico);
//		mv.addObject("listaServicos",service.buscarPorCategoria());
		 //setando mensagens de erro no template
	    mv.addObject("mensagemErro", ra.getFlashAttributes().get("mensagemErro"));
	    mv.addObject("mensagemSucesso", ra.getFlashAttributes().get("mensagemSucesso"));
	    return mv;
		
	}

	@GetMapping("/novo")
	public String exibirForm(@ModelAttribute Servico servico) {
		return "cadastros/servico-form";
	}

	@RequestMapping(value="saveList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView salvarPesquisarServico(@Valid @ModelAttribute Servico servico, @RequestParam(value="action", 
		required=false) String action, Errors errors, RedirectAttributes ra) {
		
		if (action != null && action.equals("salvar")) {
			return salvar(servico, errors, ra);
		} else {
			return pesquisar(servico, ra);
		}
	}

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
		Servico s = service.buscarPorId(id);
		ModelAndView mv = new ModelAndView("cadastros/servicos-list");
		mv.addObject("servico", s);
		return mv;
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") int id, RedirectAttributes ra) {
		service.excluir(id);
		ra.addFlashAttribute("mensagemSucesso", "Serviço removido com sucesso");
		return "redirect:/servicos/list";		
	
	}

}
