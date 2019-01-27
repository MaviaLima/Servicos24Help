package br.com.help.servicos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Categoria {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank @Length(min=10, max=60, 
	      message= "Informar a categoria (até {max} caracteres)")
	private String nome;
//	@ManyToOne
	private List<Servico> servicos;
	
	public Categoria() {
		super();
	}

	public Categoria(Integer id,
			@NotBlank @Length(min = 10, max = 60, message = "Informar a categoria (até {max} caracteres)") String nome, 
					List<Servico> servicos) {
		super();
		this.id = id;
		this.nome = nome;
		this.servicos = servicos;
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

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	
//	public List<Servico> getServicos() {
//		return servicos;
//	}
//
//	public void setServicos(List<Servico> servicos) {
//		this.servicos = servicos;
//	}
	
	
}
