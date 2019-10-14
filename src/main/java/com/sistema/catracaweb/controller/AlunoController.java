package com.sistema.catracaweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.catracaweb.model.Aluno;
import com.sistema.catracaweb.service.AlunoService;

@Controller
@RequestMapping("aluno")
public class AlunoController {

	@Autowired
	private AlunoService service;
	
	@GetMapping({"","/"})
	public String raiz(ModelMap model) {
		
		model.addAttribute("aluno", new Aluno());
		return "administrador/aluno";
	}
	
	@PostMapping("/salvar")
	public String salvar(Aluno aluno, RedirectAttributes attr) {
		service.salvar(aluno);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");		
		return "redirect:/aluno";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getAlunos(HttpServletRequest request){
		return ResponseEntity.ok(service.buscarAlunos(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("aluno", service.buscarPorId(id));
		return "administrador/aluno";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		return "redirect:/aluno";
	}
	
	@GetMapping("/curso/listar")
	public ResponseEntity<?> getCursos() {
		
		return ResponseEntity.ok(service.buscarCursos());
	}
	
	@GetMapping("/turma/listar")
	public ResponseEntity<?> getTurmas() {
		
		return ResponseEntity.ok(service.buscarTurmas());
	}
}
