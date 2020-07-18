package es.eoi.springboot.tallerMecanico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.springboot.tallerMecanico.entity.Mecanico;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, String>{

	@Query("SELECT m FROM Mecanico m WHERE m.dni = ?1")
	public Mecanico findMecanicoByDni(String dni);
}
