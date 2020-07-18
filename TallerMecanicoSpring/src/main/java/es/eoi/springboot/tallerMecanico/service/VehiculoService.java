package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import es.eoi.springboot.tallerMecanico.entity.Vehiculo;

public interface VehiculoService {

	public List<Vehiculo> findAllVehiculos();

	public Vehiculo createVehiculo(Vehiculo vehiculo);

	public Vehiculo findVehiculoByMatricula(String matricula);
	
	public void deleteByMatricula(String matricula);
	
	public Vehiculo updateVehiculo(String matricula, String newMatricula);
	
}
