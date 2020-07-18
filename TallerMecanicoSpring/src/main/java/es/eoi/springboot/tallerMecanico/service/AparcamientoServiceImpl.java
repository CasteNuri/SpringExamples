package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.springboot.tallerMecanico.entity.Aparcamiento;
import es.eoi.springboot.tallerMecanico.repository.AparcamientoRepository;

@Service
public class AparcamientoServiceImpl implements AparcamientoService{

	@Autowired
	private AparcamientoRepository aparcamientoRepo;

	@Override
	public List<Aparcamiento> findAllParkings() {
		return aparcamientoRepo.findAll();
	}

	@Override
	@Transactional
	public Aparcamiento createParking(Aparcamiento parking) {
		return aparcamientoRepo.save(parking);
	}

	@Override
	public Aparcamiento findParkingById(Long id) {
		return aparcamientoRepo.findById(id).get();
	}
}
