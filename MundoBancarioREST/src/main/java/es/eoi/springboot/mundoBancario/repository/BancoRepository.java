package es.eoi.springboot.mundoBancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.springboot.mundoBancario.entity.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long>{

}
