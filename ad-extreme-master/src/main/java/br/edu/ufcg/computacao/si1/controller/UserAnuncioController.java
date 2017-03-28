package br.edu.ufcg.computacao.si1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

@Controller
public class UserAnuncioController {

    @Autowired
    private AnuncioServiceImpl anuncioService;
    
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.GET)
    public ModelAndView getPageCadastrarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();

        model.addObject("tipos", anuncioForm.getTipos());
        model.setViewName("user/cadastrar_anuncio");

        return model;
    }

    @RequestMapping(value = "/user/listar/anuncios", method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();

        model.addObject("anuncios", anuncioService.getAnuncioRepository().findAll());

        model.setViewName("user/listar_anuncios");

        return model;
    }

    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastrarAnuncio(anuncioForm);
        }

        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());
        
        Usuario usuarioLogged = usuarioService.getByEmail(SecurityContextHolder
        		.getContext().getAuthentication().getName()).get();
        anuncio.setDono(usuarioLogged.getEmail());

        anuncioService.create(anuncio);

        
        usuarioLogged.addAnuncio(anuncio.get_id().toString());
        usuarioService.update(usuarioLogged);

        attributes.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
        return new ModelAndView("redirect:/user/cadastrar/anuncio");
    }
    
    @RequestMapping(value = "/user/pesquisar/anuncios", method = RequestMethod.GET)
    public ModelAndView getPagePesquisarAnuncio(){
        ModelAndView model = new ModelAndView();

        model.setViewName("user/pesquisar_anuncio");

        return model;
    }

	@RequestMapping(value ="/user/anuncio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Anuncio> getAnuncio(@PathVariable Long id){
		Anuncio anuncio = anuncioService.getById(id).get();
		return new ResponseEntity<>(anuncio, HttpStatus.OK);
	}
}
