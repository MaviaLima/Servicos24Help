package br.com.help.servicos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Categoria {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Length(min=1, max=60, 
	      message= "Informar a categoria (até {max} caracteres)")
	private String nome;

	public Categoria() {
		super();
	}

	public Categoria(Integer id,
			@Length(min = 1, max = 60, message = "Informar a categoria (até {max} caracteres)") String nome ) {
		
		super();
		this.id = id;
		this.nome = nome;
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
