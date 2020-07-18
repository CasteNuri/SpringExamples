package es.eoi.springboot.mundoBancario.service;

import java.util.List;

import es.eoi.springboot.mundoBancario.entity.Cliente;

public interface ClienteService {

	public Cliente createCliente(Cliente cliente);
	
	public List<Cliente> readAllClientes();
	
	public void deleteCliente(String dni);

	public void updateCliente(Cliente cliente);
	
	public Cliente findByDni(String dni);
	
}
