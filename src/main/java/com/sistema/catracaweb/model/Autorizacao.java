package com.sistema.catracaweb.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity	
@Table(name = "AUTORIZACAO")
public class Autorizacao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "refeicao_id")
	private Refeicao refeicao;
	
	@ManyToMany
	@JoinTable(
		name = "AUTORIZACAO_TEM_ALUNOS", 
        joinColumns = { @JoinColumn(name = "autorizacao_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "aluno_id", referencedColumnName = "id") }
	)
	private List<Aluno> alunos;
	
	@Transient
	private String[] turmasLista;
	
	@Transient
	private String curso;

}
