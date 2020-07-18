package es.eoi.springboot.mundoBancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.springboot.mundoBancario.entity.Banco;
import es.eoi.springboot.mundoBancario.repository.BancoRepository;

@Service
public class BancoServiceImpl implements BancoService{

	@Autowired
	private BancoRepository repository;
	
	@Override
	public void createBanco(Banco banco) {
		repository.save(banco);
	}

	@Override
	public Optional<Banco> readById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Banco> readAllBancos() {
		return repository.findAll();
	}

	@Override
	public void updateBanco(Banco banco) {
		repository.save(banco);
	}

	@Override
	public void deleteBanco(Long id) {
		repository.deleteById(id);
	}

}
