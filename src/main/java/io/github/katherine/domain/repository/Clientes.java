package io.github.katherine.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.katherine.domain.entity.Cliente;

@Repository
public class Clientes {
	private static String INSERT = "insert into cliente (nome) values (?)";
	private static String SELECT_ALL = "select * from cliente";
	private static String UPDATE = "update cliente set nome = ? where id = ?";
	private static String DELETE = "delete from cliente where id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//CRUD
	public Cliente salvar(Cliente cliente) {
		jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});		
		return cliente;
	}
	
	public Cliente atualizar(Cliente cliente) {
		jdbcTemplate.update(UPDATE, new Object[] {
				cliente.getNome(), cliente.getId()});
		return cliente;
	}
	
	public void deletar(Cliente cliente) {
		deletar(cliente.getId());
	}
	
	public void deletar(Integer id) {
		jdbcTemplate.update(DELETE, new Object[]{id});
	}
	
	public List<Cliente> listarTodos(){
		return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
				return new Cliente(resultSet.getString("nome"));
			}
			
		});
	}
}