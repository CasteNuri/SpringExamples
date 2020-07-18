package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import es.eoi.springboot.tallerMecanico.entity.Reparacion;

public interface ReparacionService {

	public List<Reparacion> findAllReparaciones();

	public Reparacion createReparacion(Reparacion reparacion);

	public Reparacion findReparacionById(Long id);
	
	public void deleteReparacionById(Long id);
	
	public Reparacion updateReparacion(Long id, Double precio);
	
	public List<Reparacion> getReparacionListByPrecioDesc();
}
