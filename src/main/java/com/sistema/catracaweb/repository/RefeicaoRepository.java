package com.sistema.catracaweb.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.catracaweb.model.Alimento;
import com.sistema.catracaweb.model.Refeicao;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {

	@Query("select r.data "
			+ "from Refeicao r "
			+ "where r.data = :data")
	List<LocalDate> findByData(LocalDate data);
	
	@Query("select r "
			+ "from Refeicao r "
			+ "where r.data = :data")
	Refeicao findByRefeicaoData(LocalDate data);

	@Query("select a "
			+ "from Alimento a "
			+ "where a.id = :id")
	Alimento findByIdAlimento(Long id);

	@Query("select a "
			+ "from Alimento a "
			+ "order by a.nome asc")
	List<Alimento> findAlimentos();

	@Query("select r.alimentos "
			+ "from Refeicao r "
			+ "where r.data = :data")
	List<Alimento> findAlimentosDisponibilizados(LocalDate data);

	@Query("select r.data "
			+ "from Refeicao r "
			+ "where r.data = :data AND "
			+ "r.id != :id")
	List<LocalDate> findByDataAndID(LocalDate data, Long id);
	
	@Query("select r "
			+ "from Refeicao r "
			+ "where r.data >= :data "
			+ "order by r.data ASC")
	Page<?> findAllHojeEmDiante(LocalDate data, Pageable pageable);

}
