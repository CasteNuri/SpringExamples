package es.eoi.springboot.tallerMecanico.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReparacionDto3 {

	private Double precioReparacion;
	private Date fechaReparacion;
	private VehiculoDto2 vehiculo;
}
