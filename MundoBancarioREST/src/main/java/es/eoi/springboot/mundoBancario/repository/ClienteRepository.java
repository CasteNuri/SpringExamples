package es.eoi.springboot.mundoBancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.eoi.springboot.mundoBancario.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

	@Query("SELECT c FROM Cliente c WHERE c.dni LIKE %:elDni%")
	Cliente findByDniContaining(@Param("elDni") String dni);

}
