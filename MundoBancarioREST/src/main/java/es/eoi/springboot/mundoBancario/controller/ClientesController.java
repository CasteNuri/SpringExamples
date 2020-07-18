package es.eoi.springboot.mundoBancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.springboot.mundoBancario.entity.Cliente;
import es.eoi.springboot.mundoBancario.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClientesController {

	@Autowired
	private ClienteService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> readClienteById(@PathVariable String id) {
		Cliente myCliente = service.findByDni(id);
		return new ResponseEntity<Cliente>(myCliente, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> readAll() {
		List<Cliente> clientes = service.readAllClientes();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
		service.createCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSurname(@RequestBody Cliente cliente, @PathVariable String dni) {
		Cliente currentCliente = service.findByDni(dni);
		if (currentCliente == null || dni == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cliente);
		}

		currentCliente.setDireccion(cliente.getDireccion());
		currentCliente.setNombre(cliente.getNombre());
		currentCliente.setDni(cliente.getDni());

		service.updateCliente(currentCliente);
		return ResponseEntity.ok(currentCliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable String dni) {
		service.deleteCliente(dni);
		return null;
	}
}
