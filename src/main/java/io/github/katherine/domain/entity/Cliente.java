package io.github.katherine.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente") //Não precisa colocar se o nome da classe éigual no BD também
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id") //Não é obrigatório se o nome for igual na tabela do BD
	private Integer id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	public Cliente() {
		
	} 
	
	public Cliente(String nome) {
		//this.id = id; 
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | " +
				"Nome: " + nome;
	}
	
	

}
