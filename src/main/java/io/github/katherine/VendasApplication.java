package io.github.katherine;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.katherine.domain.entity.Pedido;
import io.github.katherine.domain.repository.Pedidos;
import io.github.katherine.domain.entity.Cliente;
import io.github.katherine.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired Clientes clientes,
			@Autowired Pedidos pedidos
	){
		return args -> {
			System.out.println("Criando Clientes");
			Cliente cliente = new Cliente("Luffy");
			clientes.save(cliente);
			Cliente cliente1 =new Cliente("Zoro");
			clientes.save(cliente1);
			Cliente cliente2 =new Cliente("Nami");
			clientes.save(cliente2);
			Cliente cliente3 =new Cliente("Usopp");
			clientes.save(cliente3);

			//Criando Pedido
			Pedido p = new Pedido();
			p.setCliente(cliente);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));

			pedidos.save(p);

			//Listando pedidos
			pedidos.findByCliente(cliente).forEach(System.out::println);

		};
	}

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
