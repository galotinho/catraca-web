package com.sistema.catracaweb.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.sistema.catracaweb.model.Refeicao;
import com.sistema.catracaweb.service.RefeicaoService;

@Controller
@RequestMapping("refeicao")
public class RefeicaoController {
	
	@Autowired
	private RefeicaoService service;

	@GetMapping("/refeicao")
	public String cadastrarRefeicao(ModelMap model) {
		
		model.addAttribute("refeicao", new Refeicao());
		return "nutricionista/refeicao";
	}
	
	@GetMapping("/data/disponivel")
	public ResponseEntity<?> getAlimentos() {
		
		return ResponseEntity.ok(service.buscarAlimentos());
	}
	
	@GetMapping("/data/disponivel/{data}")
	public ResponseEntity<?> getAlimentosDisponibilizados(@PathVariable("data") String data) {
		
		return ResponseEntity.ok(service.buscarAlimentosDisponibilizados(LocalDate.parse(data)));
	}
	
			
	@PostMapping("/salvar")
	public String salvarRefeicao(Refeicao refeicao, RedirectAttributes attr) {
		
		refeicao.setAlimentos(converter(refeicao.getAlimentosLista()));
		
		LocalDate hoje = LocalDate.now();
		
		if(service.dataJaCadastrada(refeicao.getData())) {
			attr.addFlashAttribute("falha", "Data já disponibilizada! Para incluir novos alimentos, edite-a!");	
		}else {
			if(hoje.isBefore(refeicao.getData()) || 
					hoje.isEqual(refeicao.getData())) {
				service.salvar(refeicao);
				attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
			}else {
				attr.addFlashAttribute("falha", "Data deverá ser posterior ou igual a "
						+ "data de hoje!");			
			}	
		}			
		
		attr.addFlashAttribute("refeicao", refeicao);
		
		return "redirect:/refeicao/refeicao";
	}	
	
	@PostMapping("/editar")
	public String editarRefeicao(Refeicao refeicao, RedirectAttributes attr) {
		
		LocalDate hoje = LocalDate.now();
		
		if(service.dataJaCadastrada(refeicao.getData(), refeicao.getId())) {
			attr.addFlashAttribute("falha", "Data já cadastrada! Para incluir novos alimentos, edite-a!");	
		}else {		
			if(hoje.isBefore(refeicao.getData()) || 
					hoje.isEqual(refeicao.getData())) {
				
				service.editarAlimentoDisponivel(refeicao,
						converter(refeicao.getAlimentosLista()));
				attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
			}else {
				attr.addFlashAttribute("falha", "Data deverá ser posterior ou igual a "
						+ "data de hoje!");			
			}
		}
		
		attr.addFlashAttribute("refeicao", refeicao);
		
		return "redirect:/refeicao/refeicao";
	}
	
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> historicoRefeicoes(HttpServletRequest request) {
		
		return ResponseEntity.ok(service.buscarRefeicoes(request));
		
	}
	
	@GetMapping("/home/datatables/server")
	public ResponseEntity<?> historicoRefeicoesHome(HttpServletRequest request) {
		
		return ResponseEntity.ok(service.buscarRefeicoesHome(request));
		
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluirRefeicao(@PathVariable("id") Long id, RedirectAttributes attr) {
		
		Refeicao refeicao = service.buscarRefeicaoPorId(id).get();		
		LocalDate hoje = LocalDate.now();
		
		if(hoje.isBefore(refeicao.getData()) || 
				hoje.isEqual(refeicao.getData())) {
			
			service.removerRefeicaoDisponivel(id);
			attr.addFlashAttribute("sucesso", "Refeição excluída com sucesso.");
		}else {
			attr.addFlashAttribute("falha", "Refeições de datas anteriores a data de hoje não"
					+ " poderão ser excluídos! ");			
		}
		
		attr.addFlashAttribute("refeicao", refeicao);
				
		return "redirect:/refeicao/refeicao";
	}
	
	@GetMapping("/editar/{id}") 
	public String preEditarRefeicao(@PathVariable("id") Long id, ModelMap model) {
		
		Refeicao refeicao = service.buscarRefeicaoPorId(id).get();
		
		model.addAttribute("refeicao", refeicao);
		return "nutricionista/refeicao";
	}	
	
	private List<Alimento> converter(String[] alimentos){
		
		List<Alimento> lista = new ArrayList<Alimento>();
		
		for(int i=0; i<alimentos.length; i++) {
			Alimento al = service.buscarAlimento(Long.parseLong(alimentos[i]));
			lista.add(al);
		}
		
		return lista;
	}
	
}
