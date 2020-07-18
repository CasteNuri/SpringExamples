package es.eoi.springboot.tallerMecanico.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MecanicoDto {

	private String dni;
	private String nombre;
	private String ciudad;
	private Double salario;
	private AparcamientoDto aparcamiento;
	private List<ReparacionDto3> reparaciones;
}
