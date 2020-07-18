package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.springboot.tallerMecanico.entity.Reparacion;
import es.eoi.springboot.tallerMecanico.repository.ReparacionRepository;

@Service
public class ReparacionServiceImpl implements ReparacionService{

	@Autowired
	private ReparacionRepository reparacionRepo;

	@Override
	public List<Reparacion> findAllReparaciones() {
		return reparacionRepo.findAll();
	}

	@Override
	@Transactional
	public Reparacion createReparacion(Reparacion reparacion) {
		return reparacionRepo.save(reparacion);
	}

	@Override
	public Reparacion findReparacionById(Long id) {
		return reparacionRepo.findById(id).get();
	}

	@Override
	@Transactional
	public void deleteReparacionById(Long id) {
		reparacionRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Reparacion updateReparacion(Long id, Double precio) {
		Reparacion reparacion = reparacionRepo.findById(id).get();
		reparacion.setPrecioReparacion(precio);
		return reparacionRepo.save(reparacion);
	}

	@Override
	public List<Reparacion> getReparacionListByPrecioDesc() {
		return reparacionRepo.getReparacionListByPrecioDesc();
	}
}
