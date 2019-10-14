package com.sistema.catracaweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.catracaweb.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	@Query("select a from Aluno a where a.nome like :nome%")
	Page<Aluno> findAllByNome(String nome, Pageable pageable);

}
