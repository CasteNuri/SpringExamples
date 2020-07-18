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
import org.springframework.web.bind.annotation.RestController;

import es.eoi.springboot.tallerMecanico.dto.AparcamientoDto;
import es.eoi.springboot.tallerMecanico.dto.MecanicoDto;
import es.eoi.springboot.tallerMecanico.entity.Aparcamiento;
import es.eoi.springboot.tallerMecanico.entity.Mecanico;
import es.eoi.springboot.tallerMecanico.service.AparcamientoService;
import es.eoi.springboot.tallerMecanico.service.MecanicoService;

@RestController
@RequestMapping(value="/mecanicos")
public class MecanicoController {

	@Autowired
	private MecanicoService mecanicoService;

	@Autowired
	private AparcamientoService parkingService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> mecanicoList() {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		List<MecanicoDto> mecanicoList = mecanicoService.findAllMecanicos().stream()
				.map(p -> modelMapper.map(p, MecanicoDto.class)).collect(Collectors.toList());
		response.put("Mecanicos", mecanicoList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createMecanico(@RequestBody MecanicoDto mecanicoDto) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		ModelMapper modelMapper = new ModelMapper();
		Aparcamiento parking = parkingService.findParkingById(mecanicoDto.getAparcamiento().getId());
		
		Mecanico currentMecanico = modelMapper.map(mecanicoDto, Mecanico.class);
		currentMecanico.setAparcamiento(parking);
		Mecanico newMecanico = null;

		try {
			newMecanico = mecanicoService.createMecanico(currentMecanico);
		} catch (DataAccessException e) {
			response.put("message", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("newMecanico", modelMapper.map(newMecanico, MecanicoDto.class));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String,Object>> getMecanicoByDni(@PathVariable(value="id") String dni) {
		ModelMapper modelMapper = new ModelMapper();
		MecanicoDto mecanicoDto = null;
		Mecanico mecanico;
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			mecanico = mecanicoService.findMecanicoByDni(dni);
		} catch (Exception e) {
			response.put("message", "El Mecanico con Dni ".concat(dni.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		mecanicoDto = modelMapper.map(mecanico, MecanicoDto.class);
		response.put("myMecanico", mecanicoDto);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String,Object>> updateMecanico(@PathVariable(value="id") String dni, @RequestBody MecanicoDto mecanico) {
		Map<String,Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		Mecanico myMecanico = new Mecanico();
		try {
			myMecanico = mecanicoService.findMecanicoByDni(dni);
		} catch (Exception e) {
			response.put("mensaje", "El Mecanico con Dni ".concat(dni.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		myMecanico.setNombre(mecanico.getNombre());
		myMecanico.setCiudad(mecanico.getCiudad());
		myMecanico.setSalario(mecanico.getSalario());
		
		mecanicoService.createMecanico(myMecanico);
		response.put("mecanicoUpdated", modelMapper.map(myMecanico, MecanicoDto.class));
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Object>> deleteMecanico(@PathVariable(value="id") String dni) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			mecanicoService.deleteByDni(dni);
		} catch (IllegalArgumentException e) {
			response.put("mensaje", "El Mecanico con Dni ".concat(dni.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("entity deleted", dni);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/{id}/plaza")
	public ResponseEntity<Map<String,Object>> assignParking(@PathVariable(value="id") String dni, @RequestBody AparcamientoDto parking) {
		Map<String,Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		
		Aparcamiento newParking = parkingService.findParkingById(parking.getId());
		Mecanico myMecanico = mecanicoService.assignParking(dni, newParking);
		
		response.put("mecanicoUpdated", modelMapper.map(myMecanico, MecanicoDto.class));
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
}
