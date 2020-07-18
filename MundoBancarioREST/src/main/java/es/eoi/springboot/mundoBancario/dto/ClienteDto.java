package es.eoi.springboot.mundoBancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

	private String dni;
	
	private String nombre;
	
	private String direccion;
	
	private String direccion2;
	
	private List<BancoDto> bancos;
}
