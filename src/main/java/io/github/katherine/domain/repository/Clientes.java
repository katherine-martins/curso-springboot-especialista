package io.github.katherine.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.katherine.domain.entity.Cliente;

@Repository
public class Clientes {
	//private static String INSERT = "insert into cliente (nome) values (?)";
	private static String SELECT_ALL = "select * from cliente";
	private static String UPDATE = "update cliente set nome = ? where id = ?";
	private static String DELETE = "delete from cliente where id = ?";
	private static String SELECT_NOME = "select * from cliente where nome like '%?%'";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManager entityManager;	
	
	//CRUD
	@Transactional
	public Cliente salvar(Cliente cliente) {
		entityManager.persist(cliente);		
		return cliente;
	}
	
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		entityManager.merge(cliente);
		return cliente;
	}
	
	@Transactional
	public void deletar(Cliente cliente) {
		if(!entityManager.contains(cliente)){
			cliente = entityManager.merge(cliente);
		}
		entityManager.remove(cliente);
	}
	
	@Transactional
	public void deletar(Integer id) {
		Cliente cliente = entityManager.find(Cliente.class, id);
		deletar(cliente);
	}
	

	@Transactional(readOnly = true)
	public List<Cliente> buscarNome(String nome) {
		String jpql = "select c from Cliente c where c.nome like :nome ";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome +"%");
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> listarTodos(){
		return entityManager.createQuery("from CLIENTE", Cliente.class).getResultList();
	}
}
