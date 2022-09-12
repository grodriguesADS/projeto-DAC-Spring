package br.edu.ifpb.dac.guilherme.projetojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.dac.guilherme.projetojpa.control.CarroController;
import br.edu.ifpb.dac.guilherme.projetojpa.entity.Carro;

@SpringBootApplication
public class ProjetoJpaApplication implements CommandLineRunner {
	
	@Autowired
	private CarroController carroController;
	

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		carroController.create();
		
		List<Carro> carros = carroController.list();
		
		for (Carro carro : carros) {
			System.out.println(carro);
		}
		
		
		
	}

}
