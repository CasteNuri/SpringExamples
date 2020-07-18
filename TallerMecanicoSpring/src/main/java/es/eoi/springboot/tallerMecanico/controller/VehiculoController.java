package es.eoi.springboot.tallerMecanico.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.springboot.tallerMecanico.dto.VehiculoDto;
import es.eoi.springboot.tallerMecanico.entity.Vehiculo;
import es.eoi.springboot.tallerMecanico.service.VehiculoService;

@RestController
@RequestMapping(value="/vehiculos")
public class VehiculoController {
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> vehiculoList() {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		List<VehiculoDto> vehiculoList = vehiculoService.findAllVehiculos().stream()
				.map(p -> modelMapper.map(p, VehiculoDto.class)).collect(Collectors.toList());
		response.put("Vehiculos", vehiculoList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createVehiculo(@RequestBody VehiculoDto vehiculo) {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		Vehiculo newVehiculo = null;

		try {
			newVehiculo = vehiculoService.createVehiculo(modelMapper.map(vehiculo, Vehiculo.class));
		} catch (DataAccessException e) {
			response.put("message", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("newVehiculo", newVehiculo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String,Object>> getVehiculoById(@PathVariable(value="id") String matricula) {
		Map<String,Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		VehiculoDto vehiculoDto = null;
		Vehiculo vehiculo = null;
		
		try {
			vehiculo = vehiculoService.findVehiculoByMatricula(matricula);
		} catch (Exception e) {
			response.put("mensaje", "El vehiculo con matricula ".concat(matricula.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		vehiculoDto = modelMapper.map(vehiculo, VehiculoDto.class);
		response.put("myVehiculo", vehiculoDto);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String,Object>> updateVehiculo(@PathVariable(value="id") String matricula, @RequestParam("matricula") String newMatricula) {
		Map<String,Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		
		Vehiculo vehiculoUpdated = vehiculoService.updateVehiculo(matricula, newMatricula);
		
		response.put("vehiculoUpdated", modelMapper.map(vehiculoUpdated, VehiculoDto.class));
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Object>> deleteVehiculo(@PathVariable(value="id") String matricula) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			vehiculoService.deleteByMatricula(matricula);
		} catch (IllegalArgumentException e) {
			response.put("mensaje", "El Vehiculo con matricula ".concat(matricula.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("vehiculo eliminado", matricula);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NO_CONTENT);
	}

}
