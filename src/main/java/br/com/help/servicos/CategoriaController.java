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
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
		
	@GetMapping("/")
	public ModelAndView pesquisar(Categoria categoria, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("cadastros/categorias-list");
		if (categoria == null || categoria.getId() == null) {
			mv.addObject("lista", categoriaService.listarTodas());
		}else {
			mv.addObject("listaPorNome", categoriaService.buscarPorNome(categoria.getNome()));
		}
		mv.addObject("categoria", categoria);
	 //setando mensagens de erro no template
	    mv.addObject("mensagemErro", ra.getFlashAttributes().get("mensagemErro"));
	    mv.addObject("mensagemSucesso", ra.getFlashAttributes().get("mensagemSucesso"));
	    return mv;
		
	}


	@GetMapping("/nova")
	public String exibirForm(@ModelAttribute Categoria categoria) {
		return "redirect:/categorias/";
	}

	@RequestMapping(value="/saveList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView salvarPesquisarCategoria(@Valid @ModelAttribute Categoria categoria, @RequestParam(value="action", 
		required=false) String action, Errors errors, RedirectAttributes ra) {
		
		if (action != null && action.equals("salvar")) {
			return salvar(categoria, errors, ra);
		} else {
			return pesquisar(categoria, ra);
		}
	}

		
	@PostMapping("salvar")
	public ModelAndView salvar(@Valid @ModelAttribute Categoria categoria, Errors errors, RedirectAttributes ra) {
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
		//return "redirect:/categorias/";
		return pesquisar(new Categoria(), ra);
	}
		
	@GetMapping("/editar/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") int id) {
		Categoria c = categoriaService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("cadastros/categorias-list");
		mv.addObject("lista", categoriaService.listarTodas());
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
