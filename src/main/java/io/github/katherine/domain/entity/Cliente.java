package io.github.katherine.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cliente") //Não precisa colocar se o nome da classe eh igual no BD tambem
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id") //Não é obrigatório se o nome for igual na tabela do BD
	private Integer id;
	
	@Column(name = "nome", length = 100)
	private String nome;

	@OneToMany (mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;

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

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | " +
				"Nome: " + nome;
	}
	
	

}
