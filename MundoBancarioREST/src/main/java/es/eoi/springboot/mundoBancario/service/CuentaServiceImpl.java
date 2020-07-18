package es.eoi.springboot.mundoBancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.springboot.mundoBancario.entity.Cuenta;
import es.eoi.springboot.mundoBancario.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService{

	@Autowired
	private CuentaRepository repository;
	@Override
	public void createCuenta(Cuenta cuenta) {
		repository.save(cuenta);
	}

	@Override
	public Optional<Cuenta> readById(Long id) {
		return repository.findById(id);
	}	
	@Override
	public List<Cuenta> readAllCuentas() {
		return repository.findAll();
	}

	@Override
	public void updateCuenta(Cuenta cuenta) {
		repository.save(cuenta);
	}

	@Override
	public void deleteCuenta(Long id) {
		repository.deleteById(id);
	}
}
