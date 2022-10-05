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
			clientes.salvar(new Cliente("Sebastian"));
			clientes.salvar(new Cliente("Francisco"));
			
			List<Cliente> todosClientes = clientes.listarTodos();
			todosClientes.forEach(System.out::println);
		};
	}

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
