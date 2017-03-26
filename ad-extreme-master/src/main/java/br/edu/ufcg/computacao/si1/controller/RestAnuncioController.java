<<<<<<< HEAD
package br.edu.ufcg.computacao.si1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

/**
 * Created by marcus on 15/03/17.
 */

@RestController
public class RestAnuncioController {

	@Autowired
	private AnuncioServiceImpl anuncioService;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Collection<Usuario>> getUsers() {
		return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUser(@PathVariable Long id){
		return new ResponseEntity<>(usuarioService.getById(id).get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/anuncios", method = RequestMethod.GET)
	public ResponseEntity<Collection<Anuncio>> getPageListarAnuncios() {
		return new ResponseEntity<>(anuncioService.getAll(), HttpStatus.OK);
	}

=======
package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marcus on 15/03/17.
 */

@RestController
public class RestAnuncioController {

    @Autowired
    private AnuncioServiceImpl anuncioService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Collection<Usuario>> getUsers(){

    	return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/anuncios", method = RequestMethod.GET)
    public ResponseEntity<Collection<Anuncio>> getPageListarAnuncios(){

        return new ResponseEntity<>(anuncioService.getAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public ResponseEntity<String> getLoggedUser(){

        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getName(), HttpStatus.OK);
    }

>>>>>>> refs/remotes/origin/master
}