package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import es.eoi.springboot.tallerMecanico.entity.Aparcamiento;
import es.eoi.springboot.tallerMecanico.entity.Mecanico;

public interface MecanicoService {

	public List<Mecanico> findAllMecanicos();

	public Mecanico createMecanico(Mecanico mecanico);

	public Mecanico findMecanicoByDni(String dni);
	
	public void deleteByDni(String dni);
	
	public Mecanico assignParking(String dni, Aparcamiento parking);
}
