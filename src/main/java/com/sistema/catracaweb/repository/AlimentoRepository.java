package com.sistema.catracaweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.catracaweb.model.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento, Long>{

	@Query("select a from Alimento a where a.nome like :nome%")
	Page<Alimento> findAllByNome(String nome, Pageable pageable);
}
