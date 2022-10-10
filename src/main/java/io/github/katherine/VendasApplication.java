package io.github.katherine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.katherine.domain.entity.Cliente;
import io.github.katherine.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			clientes.salvar(new Cliente("Luffy"));
			clientes.salvar(new Cliente("Zoro"));
			clientes.salvar(new Cliente("Nami"));
			clientes.salvar(new Cliente("Usopp"));
			
			//Listando todos os clientes
			List<Cliente> todosClientes = clientes.listarTodos();
			System.out.println("Clientes cadastrados:");
			todosClientes.forEach(System.out::println);
			
			//Atualizando nome dos clientes
			System.out.println("Atualizando clientes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + "atualizado");
				clientes.atualizar(c);
			});
			
			//Listando clientes atualizados
			todosClientes = clientes.listarTodos();
			todosClientes.forEach(System.out::println);
			
			//Fazendo busca por Nome do Cliente. OBS: Não existe mais esse método
			System.out.println("Buscando clientes");
			clientes.buscarNome("Lu").forEach(System.out::println);
			
			//Não tá dando para deletar
			System.out.println("deletando clientes");
			clientes.listarTodos().forEach(c -> {
				clientes.deletar(c);
				});
	
			
			//Verificar se tem clientes
			todosClientes = clientes.listarTodos();
			if(todosClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado!");
			}else {
				todosClientes.forEach(System.out::println);
			}
			
			};
	}

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
