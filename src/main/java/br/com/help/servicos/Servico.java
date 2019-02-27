package br.com.help.servicos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Servico {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank @Length(min=1, max=100, 
			message= "Informar descricao do serviço (até {max} caracteres)")
	private String descricao;


	@ManyToOne
	@JoinColumn(name="servico_id")
	private Categoria categoria;
	
	/*
	 * @Enumerated(EnumType.STRING) private ECategoria categoria;
	 */
	
	
	public Servico() {
		super();
	}



	public Servico(Integer id,
			@NotBlank @Length(min = 2, max = 100, message = "Informar descricao do serviço (até {max} caracteres)") String descricao,
			@NotBlank Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


}
