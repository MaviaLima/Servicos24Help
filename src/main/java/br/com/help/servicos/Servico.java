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
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank @Length(min=10, max=200, 
			message= "Informar descricao do serviço (até {max} caracteres)")
	private String descricao;

	@NotBlank
//	@ManyToOne
//	@JoinColumn(name="servico_id")
	@Enumerated(EnumType.STRING)
	private ECategoria categoria;


	public Servico() {
		super();
	}



	public Servico(Integer id,
			@NotBlank @Length(min = 10, max = 200, message = "Informar descricao do serviço (até {max} caracteres)") String descricao,
			@NotBlank ECategoria categoria) {
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


	public ECategoria getCategoria() {
		return categoria;
	}


	public void setCategoria(ECategoria categoria) {
		this.categoria = categoria;
	}


}
