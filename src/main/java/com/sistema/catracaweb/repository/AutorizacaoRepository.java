package com.sistema.catracaweb.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.catracaweb.model.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>{

	@Query("select a "
			+ "from Autorizacao a "
			+ "where a.refeicao.data >= :data "
			+ "order by a.refeicao.data ASC")
	Page<?> findAllHojeEmDiante(LocalDate data, Pageable pageable);
}
