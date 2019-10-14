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

import com.sistema.catracaweb.model.Alimento;
import com.sistema.catracaweb.service.AlimentoService;

@Controller
@RequestMapping("alimentos")
public class AlimentoController {
	
	@Autowired
	private AlimentoService service;
	
	@GetMapping({"","/"})
	public String abrir(Alimento alimento) {
		return "nutricionista/alimento";
	}
	
	@PostMapping("/salvar")
	public String salvar(Alimento alimento, RedirectAttributes attr) {
		service.salvar(alimento);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");		
		return "redirect:/alimentos";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getAlimentos(HttpServletRequest request){
		return ResponseEntity.ok(service.buscarAlimentos(request));
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("alimento", service.buscarPorId(id));
		return "nutricionista/alimento";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		return "redirect:/alimentos";
	}

}
