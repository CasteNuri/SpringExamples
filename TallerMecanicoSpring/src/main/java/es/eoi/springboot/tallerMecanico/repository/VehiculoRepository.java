package es.eoi.springboot.tallerMecanico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.springboot.tallerMecanico.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String>{

}
