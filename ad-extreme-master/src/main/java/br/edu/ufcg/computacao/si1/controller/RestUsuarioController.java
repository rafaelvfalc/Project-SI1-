package br.edu.ufcg.computacao.si1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

import br.edu.ufcg.computacao.si1.model.Usuario;

@Controller
public class RestUsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@RequestMapping(value = "/favoritos", method = RequestMethod.GET)
	public ResponseEntity<Collection<String>> meusFavoritos() {
		Usuario userLogged = getUserLogged().getBody();
		return new ResponseEntity<>(userLogged.getFavoritos(), HttpStatus.OK);

	}

	@RequestMapping(value = "/favoritos/add/{id}", method = RequestMethod.POST)
	public ResponseEntity<Collection<String>> addFavorito(@PathVariable Long id) {
		Usuario usuarioLogged = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		String emailFavoritado = (usuarioService.getById(id).get()).getEmail();
		
		usuarioLogged.addFavorito(emailFavoritado);

		if(usuarioService.update(usuarioLogged)){
			return new ResponseEntity<>(usuarioLogged.getFavoritos(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/favoritos/remove/{id}", method = RequestMethod.POST)
	public ResponseEntity<Collection<String>> removeFavorito(@PathVariable Long id) {
		Usuario usuarioLogged = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		String emailFavoritado = (usuarioService.getById(id).get()).getEmail();

		usuarioLogged.removeFavorito(emailFavoritado);

		if(usuarioService.update(usuarioLogged)){
			return new ResponseEntity<>(usuarioLogged.getFavoritos(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/logged", method = RequestMethod.GET)
	public ResponseEntity<String> getLoggedUser() {

		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getName(), HttpStatus.OK);
	}

	@RequestMapping(value = "/loggedUser", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUserLogged() {
		Usuario usuario = usuarioService
				.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

}
