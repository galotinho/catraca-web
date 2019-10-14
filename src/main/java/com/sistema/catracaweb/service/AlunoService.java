package com.sistema.catracaweb.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.catracaweb.datables.Datatables;
import com.sistema.catracaweb.datables.DatatablesColunas;
import com.sistema.catracaweb.model.Aluno;
import com.sistema.catracaweb.model.Curso;
import com.sistema.catracaweb.model.Turma;
import com.sistema.catracaweb.repository.AlunoRepository;
import com.sistema.catracaweb.repository.CursoRepository;
import com.sistema.catracaweb.repository.TurmaRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private Datatables dataTables;
	
	@Transactional(readOnly=false)
	public void salvar(Aluno aluno) {
		repository.save(aluno);		
	}

	@Transactional(readOnly=false)
	public void remover(Long id) {
		repository.deleteById(id);		
	}
	
	@Transactional(readOnly=true)
	public Aluno buscarPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly=true)
	public Map<String,Object> buscarAlunos(HttpServletRequest http) {
		
		dataTables.setRequest(http);
		dataTables.setColunas(DatatablesColunas.ALUNOS);
		
		Page<?> page;
		
		if(dataTables.getSearch().isEmpty()) {
			page = repository.findAll(dataTables.getPageable());
		}else {
			page = repository.findAllByNome(dataTables.getSearch(), dataTables.getPageable());
		}
		
		return dataTables.getResponse(page);
	}
	
	@Transactional(readOnly = true)
	public List<Curso> buscarCursos() {
		return cursoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Turma> buscarTurmas() {
		return turmaRepository.findAll();
	}

}
