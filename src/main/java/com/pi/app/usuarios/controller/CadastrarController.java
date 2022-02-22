package com.pi.app.usuarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pi.app.usuarios.models.CadastrarModels;
import com.pi.app.usuarios.repository.CadastrarRepository;

@Controller
public class CadastrarController {

	@Autowired
	private CadastrarRepository cr;
	
	// GET PARA O FORM QUE CADASTRA
	@RequestMapping("/cadastrar")
	public String form() {
		return "cadastrar";
	}
	
	// POST QUE CADASTRA
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(@Valid CadastrarModels cadastro, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrar";
		}

		cr.save(cadastro);
		attributes.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso!");
		return "redirect:/usuarios-cadastrados";

	}
	
	// GET QUE LISTA EMPRESAS
	@RequestMapping("/usuarios-cadastrados")
	public ModelAndView listarUsuarios() {
		ModelAndView mv = new ModelAndView("usuarios-cadastrados");
		Iterable<CadastrarModels> usuarios = cr.findAll();
		mv.addObject("usuarios", usuarios);
		return mv;
	}

	// GET QUE DELETA EMPRESA
	@RequestMapping("/deletar-cadastro")
	public String deletarUsuario(long id) {
		CadastrarModels cadastro = cr.findById(id);
		cr.delete(cadastro);
		return "redirect:/usuarios-cadastrados";
	}

	// GET QUE CHAMA O FORM DE EDIÇÃO DA EMPRESA
	@RequestMapping("/editar-cadastro")
	public ModelAndView editarUsuario(long id) {
		CadastrarModels cadastro = cr.findById(id);
		ModelAndView mv = new ModelAndView("editar-cadastro");
		mv.addObject("usuario", cadastro);
		return mv;
	}

	// POST QUE ATUALIZA A EMPRESA
	@RequestMapping(value = "/editar-cadastro", method = RequestMethod.POST)
	public String updateUsuario(@Valid CadastrarModels cadastro, BindingResult result, RedirectAttributes attributes) {
		cr.save(cadastro);
		attributes.addFlashAttribute("success", "Cadastro alterado com sucesso!");
		return "redirect:/usuarios-cadastrados";
	}
}
