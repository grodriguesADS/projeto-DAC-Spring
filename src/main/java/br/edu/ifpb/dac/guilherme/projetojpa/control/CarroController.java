package br.edu.ifpb.dac.guilherme.projetojpa.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.dac.guilherme.projetojpa.dao.CarroDAO;
import br.edu.ifpb.dac.guilherme.projetojpa.entity.Carro;

@Controller
public class CarroController {
	
	@Autowired
	private CarroDAO carroDAO;
	
	public void create() {
		for (int i = 1000; i < 1010; i++) {
			Carro carro = new Carro();
			carro.setPlaca(i);
			carroDAO.save(carro);
				
		}
		
	}
	
	public List<Carro> list () {
		return (List<Carro>) carroDAO.findAll();
	}

}
