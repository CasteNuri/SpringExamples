package es.eoi.springboot.mundoBancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.springboot.mundoBancario.entity.Banco;

public interface BancoService {

public void createBanco(Banco banco);
	
	public Optional<Banco> readById(Long id);
	
	public List<Banco> readAllBancos();
	
	public void updateBanco(Banco banco);
	
	public void deleteBanco(Long id);
}
