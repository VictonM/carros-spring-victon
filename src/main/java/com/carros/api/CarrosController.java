package com.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	//private CarroService service = new CarroService();
	@Autowired
	private CarroService service;	
	
	@GetMapping
	public ResponseEntity<Iterable<Carro>> get() {
		return ResponseEntity.ok(service.getCarros());
		//return new ResponseEntity<>(service.getCarros(),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Carro>> get(@PathVariable("id") Long id){
		Optional<Carro> carro = service.getCarroById(id);
		if(carro.isPresent())
			return ResponseEntity.ok(carro);
		return ResponseEntity.noContent().build();
		//return new ResponseEntity<>(service.getCarroById(id),HttpStatus.OK);
	}
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Carro>> get(@PathVariable("tipo") String tipo){
		List<Carro> carros = service.getCarroByTipo(tipo);
		//return carros.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(carros);
		if(carros.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(carros);
		//return new ResponseEntity<>(service.getCarroByTipo(tipo),HttpStatus.OK);
	}
	
	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<String> post(@RequestBody Carro carro){
		Carro c = service.salvar(carro);
		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();
		
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Carro> put(@PathVariable("id") Long id ,@RequestBody Carro carro){
		Carro c = service.update(carro, id);
		return c!= null? ResponseEntity.ok(c): ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		service.delete(id);
		return ResponseEntity.ok("Carro "+id+" excluido");
	}
}
