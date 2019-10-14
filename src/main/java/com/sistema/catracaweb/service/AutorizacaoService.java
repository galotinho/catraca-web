package com.sistema.catracaweb.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.sistema.catracaweb.model.Autorizacao;
import com.sistema.catracaweb.repository.AlunoRepository;
import com.sistema.catracaweb.repository.AutorizacaoRepository;
import com.sistema.catracaweb.repository.RefeicaoRepository;

@Service
public class AutorizacaoService {
	
	@Autowired
	private AutorizacaoRepository repository;
	
	@Autowired
	private RefeicaoRepository refeicaoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private Datatables dataTables;
	
	@Transactional(readOnly=false)
	public void salvar(Autorizacao autorizacao) {
		Autorizacao aut = new Autorizacao();
		
		aut.setRefeicao(refeicaoRepository.findByRefeicaoData(autorizacao.getRefeicao().getData()));
		
		String curso = autorizacao.getCurso();
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		for(int i=0; i < autorizacao.getTurmasLista().length;i++) {
			alunos.addAll(alunoRepository.findAllByCursoTurma(Long.parseLong(curso), Long.parseLong(autorizacao.getTurmasLista()[i])));
		}
		
		aut.setAlunos(alunos);
		
		repository.save(aut);		
	}

	@Transactional(readOnly=false)
	public void remover(Long id) {
		repository.deleteById(id);		
	}
	
	@Transactional(readOnly=true)
	public Map<String,Object> buscarAutorizacoes(HttpServletRequest http) {
		
		dataTables.setRequest(http);
		dataTables.setColunas(DatatablesColunas.AUTORIZACAO);
		
		Page<?> page;
		
		LocalDate hoje = LocalDate.now();
		
		page = repository.findAllHojeEmDiante(hoje, dataTables.getPageable());
		
		
		return dataTables.getResponse(page);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> buscarAutorizacoesHome(HttpServletRequest request) {
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.AUTORIZACAO);
		LocalDate hoje = LocalDate.now();
		// Refeicoes a partir de hoje
		Page<?> page = repository.findAllHojeEmDiante(hoje, dataTables.getPageable());
		
		
		return dataTables.getResponse(page);
	}
}
