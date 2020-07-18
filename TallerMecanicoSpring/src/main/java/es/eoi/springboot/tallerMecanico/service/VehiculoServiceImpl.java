package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.springboot.tallerMecanico.entity.Vehiculo;
import es.eoi.springboot.tallerMecanico.repository.VehiculoRepository;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepo;

	@Override
	public List<Vehiculo> findAllVehiculos() {
		return vehiculoRepo.findAll();
	}

	@Override
	@Transactional
	public Vehiculo createVehiculo(Vehiculo vehiculo) {
		return vehiculoRepo.save(vehiculo);
	}

	@Override
	public Vehiculo findVehiculoByMatricula(String matricula) {
		return vehiculoRepo.findById(matricula).get();
	}

	@Override
	@Transactional
	public void deleteByMatricula(String matricula) {
		vehiculoRepo.deleteById(matricula);

	}

	@Override
	@Transactional
	public Vehiculo updateVehiculo(String matricula, String newMatricula) {
		Vehiculo vehiculo = vehiculoRepo.findById(matricula).get();
		vehiculo.setMatricula(newMatricula);
		return vehiculoRepo.save(vehiculo);
	}
}
