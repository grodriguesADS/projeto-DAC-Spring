package br.edu.ifpb.dac.guilherme.projetojpa.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.dac.guilherme.projetojpa.entity.Carro;

@Repository
public interface CarroDAO extends PagingAndSortingRepository<Carro, Integer>  {

}
