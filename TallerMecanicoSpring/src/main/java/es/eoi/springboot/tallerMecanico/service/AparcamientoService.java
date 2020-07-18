package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import es.eoi.springboot.tallerMecanico.entity.Aparcamiento;

public interface AparcamientoService {

	public List<Aparcamiento> findAllParkings();
	
	public Aparcamiento createParking(Aparcamiento parking);
	
	public Aparcamiento findParkingById(Long id);
}
