package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**Classe que controla as paginas web da aplicacao
 * 
 * @author Hector
 *
 */

@Controller
public class WebPagesController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getPageIndex() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getPageLogin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		return model;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView getPageIndexUser() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user/index");

		return model;
	}

	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public ModelAndView getPageIndexCompany() {
		ModelAndView model = new ModelAndView();
		model.setViewName("company/index");

		return model;
	}

	@RequestMapping(value = "company/anunciantes", method = RequestMethod.GET)
	public ModelAndView getPageAnunciantesCompany() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/company/anunciantes");

		return model;
	}

	@RequestMapping(value = "user/anunciantes", method = RequestMethod.GET)
	public ModelAndView getPageAnunciantesUser() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user/anunciantes");

		return model;
	}

	@RequestMapping(value = "user/perfil", method = RequestMethod.GET)
	public ModelAndView getPageAnuncianteUser() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user/perfil");

		return model;
	}

	@RequestMapping(value = "company/perfil", method = RequestMethod.GET)
	public ModelAndView getPageAnuncianteCompany() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/company/perfil");

		return model;
	}
	
	@RequestMapping(value = "company/anuncio", method = RequestMethod.GET)
	public ModelAndView getPageAnuncioCompany() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/company/anuncio");

		return model;
	}

	@RequestMapping(value = "user/anuncio", method = RequestMethod.GET)
	public ModelAndView getPageAnuncioUser() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user/anuncio");

		return model;
	}
}
