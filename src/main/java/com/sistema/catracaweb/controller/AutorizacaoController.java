package com.sistema.catracaweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.catracaweb.model.Autorizacao;
import com.sistema.catracaweb.service.AutorizacaoService;

@Controller
@RequestMapping("autorizacao")
public class AutorizacaoController {

	@Autowired
	private AutorizacaoService service;
	
	@GetMapping({"", "/"})
	public String raiz(Autorizacao autorizacao) {
		return "nutricionista/autorizar-aluno";
	}
	
	@PostMapping("/salvar")
	public String salvar(Autorizacao autorizacao, RedirectAttributes attr) {
		service.salvar(autorizacao);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");		
		return "redirect:/autorizacao";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getAutorizacoes(HttpServletRequest request){
		return ResponseEntity.ok(service.buscarAutorizacoes(request));
	}
	
	@GetMapping("/home/datatables/server")
	public ResponseEntity<?> historicoAutorizacoesHome(HttpServletRequest request) {
		
		return ResponseEntity.ok(service.buscarAutorizacoesHome(request));
		
	}
		
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		return "redirect:/autorizacao";
	}
	
}
