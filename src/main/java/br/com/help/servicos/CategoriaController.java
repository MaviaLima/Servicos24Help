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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping("/")
	public ModelAndView listarCategorias() {
		ModelAndView mv = new ModelAndView("categoria/categorias-list");
		mv.addObject("lista", categoriaService.listarTodas());
		return mv;
	}
	

	@GetMapping("/nova")
	public String exibirForm(@ModelAttribute Categoria categoria) {
		return "cadastros/categorias-list";
	}

		
	@PostMapping("salvar")
	public String salvar(@Valid @ModelAttribute Categoria categoria, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível salvar a categoria: " + errors.getFieldErrors());
		} else {
			try {
				categoriaService.salvar(categoria);
				ra.addFlashAttribute("mensagemSucesso", "Categoria salva com sucesso [" + categoria.getNome() + "]");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível salvar categoria: " + e.getMessage());
			}
		}
		return "redirect:/categorias/";
	}
	
	@GetMapping("listaServicos")
	public ModelAndView pesquisar(Servico servico, Categoria categoria, RedirectAttributes ra) {
		
		ModelAndView mv = new ModelAndView("cadastros/servicos-list");
		if (servico == null || servico.getDescricao() == null) {
			mv.addObject("listaTodos", servicoService.listarTodos());	
//		} else {
//			mv.addObject("lista", categoria.getServicos());
		}
		 
	    //setando mensagens de erro no template
        mv.addObject("mensagemErro", ra.getFlashAttributes().get("mensagemErro"));
        mv.addObject("mensagemSucesso", ra.getFlashAttributes().get("mensagemSucesso"));

		return mv;
	}

	
	
	@GetMapping("/editar/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") int id) {
		Categoria c = categoriaService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("categoria/categoria-form");
		mv.addObject("categoria", c);
		return mv;
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") int id, RedirectAttributes ra) {
		Categoria categoriaRemovida = categoriaService.excluir(id);
		ra.addFlashAttribute("mensagemSucesso", "Categoria removida com sucesso [" + categoriaRemovida.getNome() + "]");
		return "redirect:/categorias/";
	}

}
