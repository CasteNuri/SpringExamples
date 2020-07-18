package es.eoi.springboot.mundoBancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.springboot.mundoBancario.entity.Cuenta;

public interface CuentaService {

public void createCuenta(Cuenta cuenta);
	
	public Optional<Cuenta> readById(Long id);
	
	public List<Cuenta> readAllCuentas();
	
	public void updateCuenta(Cuenta cuenta);
	
	public void deleteCuenta(Long id);
}
