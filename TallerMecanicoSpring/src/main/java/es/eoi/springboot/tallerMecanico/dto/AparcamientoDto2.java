package es.eoi.springboot.tallerMecanico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AparcamientoDto2 {
	
	private Long id;
	private int fila;
	private String columna;
	private MecanicoDto2 mecanico;
}
