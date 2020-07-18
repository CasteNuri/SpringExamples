package es.eoi.springboot.tallerMecanico.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoDto {

	private String matricula;
	private String marca;
	private String modelo;
	private List<ReparacionDto2> reparaciones;
}
