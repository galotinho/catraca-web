package com.sistema.catracaweb.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity	
@Table
public class Refeicao {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data;
	
	@ManyToMany
	@JoinTable(
		name = "refeicao_tem_alimentos", 
        joinColumns = { @JoinColumn(name = "refeicao_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "alimento_id", referencedColumnName = "id") }
	)
	private List<Alimento> alimentos;
	
	@Transient
	private String[] alimentosLista;

}
