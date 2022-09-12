package br.edu.ifpb.dac.guilherme.projetojpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Carro implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	private Integer placa;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPlaca() {
		return placa;
	}
	public void setPlaca(Integer placa) {
		this.placa = placa;
	}
	
	@Override
	public String toString() {
		return "Carro[Id = " + id + " Placa =" + placa + "]";
	}

}
