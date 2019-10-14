package com.sistema.catracaweb.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.catracaweb.datables.Datatables;
import com.sistema.catracaweb.datables.DatatablesColunas;
import com.sistema.catracaweb.model.Alimento;
import com.sistema.catracaweb.repository.AlimentoRepository;

@Service
public class AlimentoService {

	@Autowired
	private AlimentoRepository repository;

	@Autowired
	private Datatables dataTables;
	
	@Transactional(readOnly=false)
	public void salvar(Alimento alimento) {
		repository.save(alimento);		
	}

	@Transactional(readOnly=true)
	public Map<String,Object> buscarAlimentos(HttpServletRequest http) {
		
		dataTables.setRequest(http);
		dataTables.setColunas(DatatablesColunas.ALIMENTOS);
		
		Page<?> page;
		
		if(dataTables.getSearch().isEmpty()) {
			page = repository.findAll(dataTables.getPageable());
		}else {
			page = repository.findAllByNome(dataTables.getSearch(), dataTables.getPageable());
		}
		
		return dataTables.getResponse(page);
	}

	@Transactional(readOnly=true)
	public Alimento buscarPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly=false)
	public void remover(Long id) {
		repository.deleteById(id);		
	}
}
