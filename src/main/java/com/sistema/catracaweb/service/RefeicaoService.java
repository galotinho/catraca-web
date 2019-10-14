package com.sistema.catracaweb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.catracaweb.datables.Datatables;
import com.sistema.catracaweb.datables.DatatablesColunas;
import com.sistema.catracaweb.model.Alimento;
import com.sistema.catracaweb.model.Refeicao;
import com.sistema.catracaweb.repository.RefeicaoRepository;


@Service
public class RefeicaoService {

	@Autowired
	private RefeicaoRepository repository;
	
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = true)
	public boolean dataJaCadastrada(LocalDate data) {
		
		List<LocalDate> datas = repository.findByData(data);

		if(datas.isEmpty()) {
			return false;
		}
		return true;
	}

	@Transactional(readOnly = false)
	public void salvar(Refeicao refeicao) {
		repository.save(refeicao);
		
	}

	@Transactional(readOnly = true)
	public Alimento buscarAlimento(Long id) {
		// TODO Auto-generated method stub
		return repository.findByIdAlimento(id);
	}

	@Transactional(readOnly = true)
	public List<Alimento> buscarAlimentos() {
		
		return repository.findAlimentos();
	}

	@Transactional(readOnly = true)
	public List<Alimento> buscarAlimentosDisponibilizados(LocalDate data) {

		return repository.findAlimentosDisponibilizados(data);
	}

	@Transactional(readOnly = true)
	public boolean dataJaCadastrada(LocalDate data, Long id) {
		List<LocalDate> datas = repository.findByDataAndID(data, id);

		if(datas.isEmpty()) {
			return false;
		}
		return true;
	}

	
	@Transactional(readOnly = false)
	public void editarAlimentoDisponivel(Refeicao refeicao, List<Alimento> alimentos) {
		
		Refeicao r = repository.findById(refeicao.getId()).get();
		r.setAlimentos(alimentos);
		
	}
	
	@Transactional(readOnly = true)
	public Optional<Refeicao> buscarRefeicaoPorId(Long id) {
		return repository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public void removerRefeicaoDisponivel(Long id) {
		repository.deleteById(id);
		
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> buscarRefeicoes(HttpServletRequest request) {
		
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.REFEICAO);
		
		Page<?> page = repository.findAll(datatables.getPageable());
		
		
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarRefeicoesHome(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.REFEICAO);
		LocalDate hoje = LocalDate.now();
		// Refeicoes a partir de hoje
		Page<?> page = repository.findAllHojeEmDiante(hoje, datatables.getPageable());
		
		
		return datatables.getResponse(page);
	}
	
}
