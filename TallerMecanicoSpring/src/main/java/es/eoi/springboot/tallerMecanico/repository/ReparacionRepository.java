package es.eoi.springboot.tallerMecanico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.springboot.tallerMecanico.entity.Reparacion;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Long>{

	@Query("SELECT r FROM Reparacion r ORDER BY r.precioReparacion DESC")
	public List<Reparacion> getReparacionListByPrecioDesc();
}
