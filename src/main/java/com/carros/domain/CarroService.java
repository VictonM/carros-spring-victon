package com.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {
	@Autowired
	private CarroRepository rep;
	
	public Iterable<Carro> getCarros(){
		return rep.findAll();
	}
	
	public List<Carro> getCarrosFake() {
		List<Carro> carros = new ArrayList<>();
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasilia"));
		carros.add(new Carro(3L, "Chevete"));
		return carros;
	}

	public Optional<Carro> getCarroById(Long id) {
		return rep.findById(id);
	}

	public List<Carro> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}

	public Carro salvar(Carro carro) {
		return rep.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id,"Não foi possível inserir o registro.");
		Optional<Carro> optional = getCarroById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id "+db.getId());
			
			rep.save(db);
			return db;
		}else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}

	public void delete(Long id) {
		rep.deleteById(id);
	}
}
