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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.springboot.tallerMecanico.dto.AparcamientoDto;
import es.eoi.springboot.tallerMecanico.dto.AparcamientoDto2;
import es.eoi.springboot.tallerMecanico.entity.Aparcamiento;
import es.eoi.springboot.tallerMecanico.service.AparcamientoService;

@RestController
@RequestMapping(value = "/parking")
public class AparcamientoController {

	@Autowired
	private AparcamientoService aparcamientoService;

//	@Autowired
//	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping
	public ResponseEntity<Map<String, Object>> parkingList() {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		List<AparcamientoDto2> parkingList = aparcamientoService.findAllParkings().stream()
				.map(p -> modelMapper.map(p, AparcamientoDto2.class)).collect(Collectors.toList());
		response.put("Aparcamientos", parkingList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createParking(@RequestBody AparcamientoDto newParking) {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		Aparcamiento newAparcamiento = null;

		try {
			newAparcamiento = aparcamientoService.createParking(modelMapper.map(newParking, Aparcamiento.class));
		} catch (DataAccessException e) {
			response.put("message", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("newParking", newAparcamiento);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String,Object>> getParkingById(@PathVariable Long id) {
		ModelMapper modelMapper = new ModelMapper();
		AparcamientoDto2 parkingDto = null;
		Aparcamiento parking = null;
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			parking = aparcamientoService.findParkingById(id);
		} catch (Exception e) {
			response.put("mensaje", "El parking con ID ".concat(id.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		parkingDto = modelMapper.map(parking, AparcamientoDto2.class);
		response.put("myParking", parkingDto);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

}
