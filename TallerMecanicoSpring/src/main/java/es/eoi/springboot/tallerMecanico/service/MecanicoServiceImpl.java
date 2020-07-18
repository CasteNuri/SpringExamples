package es.eoi.springboot.tallerMecanico.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.springboot.tallerMecanico.entity.Aparcamiento;
import es.eoi.springboot.tallerMecanico.entity.Mecanico;
import es.eoi.springboot.tallerMecanico.repository.MecanicoRepository;

@Service
public class MecanicoServiceImpl implements MecanicoService{

	@Autowired
	private MecanicoRepository mecanicoRepo;

	@Override
	public List<Mecanico> findAllMecanicos() {
		return mecanicoRepo.findAll();
	}

	@Override
	@Transactional
	public Mecanico createMecanico(Mecanico mecanico) {
		return mecanicoRepo.save(mecanico);
	}

	@Override
	public Mecanico findMecanicoByDni(String dni) {
		return mecanicoRepo.findMecanicoByDni(dni);
	}

	@Override
	@Transactional
	public void deleteByDni(String dni) {
		mecanicoRepo.deleteById(dni);
	}

	@Override
	@Transactional
	public Mecanico assignParking(String dni, Aparcamiento parking) {
		Mecanico myMecanico = mecanicoRepo.findMecanicoByDni(dni);
		myMecanico.setAparcamiento(parking);
		return mecanicoRepo.save(myMecanico);
	}
	
	
}
