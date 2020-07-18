package es.eoi.springboot.mundoBancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.springboot.mundoBancario.entity.Cliente;
import es.eoi.springboot.mundoBancario.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository repository;

	@Override
	public Cliente createCliente(Cliente cliente) {
		try {
			repository.save(cliente);
			return cliente;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Cliente> readAllClientes() {
		return repository.findAll();
	}

	@Override
	public void updateCliente(Cliente cliente) {
		Cliente cliente2 = repository.findByDniContaining(cliente.getDni());
		cliente2.setNombre(cliente.getNombre());
		cliente2.setDireccion(cliente.getDireccion());
		repository.save(cliente2);
	}

	@Override
	public void deleteCliente(String dni) {
		repository.deleteById(dni);
	}

	@Override
	public Cliente findByDni(String dni) {
		
		return repository.findByDniContaining(dni);
	}
}
