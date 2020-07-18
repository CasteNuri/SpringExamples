package es.eoi.springboot.tallerMecanico.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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

import es.eoi.springboot.tallerMecanico.dto.ReparacionDto;
import es.eoi.springboot.tallerMecanico.dto.ReparacionDto2;
import es.eoi.springboot.tallerMecanico.dto.ReparacionDto3;
import es.eoi.springboot.tallerMecanico.entity.Mecanico;
import es.eoi.springboot.tallerMecanico.entity.Reparacion;
import es.eoi.springboot.tallerMecanico.entity.Vehiculo;
import es.eoi.springboot.tallerMecanico.service.MecanicoService;
import es.eoi.springboot.tallerMecanico.service.ReparacionService;
import es.eoi.springboot.tallerMecanico.service.VehiculoService;

@RestController
@RequestMapping(value = "/reparacion")
public class ReparacionController {

	@Autowired
	private ReparacionService reparacionService;
	
	@Autowired
	private MecanicoService mecanicoService;
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> reparacionesList() {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();

		List<ReparacionDto> reparacionesList = reparacionService.findAllReparaciones().stream()
				.map(p -> modelMapper.map(p, ReparacionDto.class)).collect(Collectors.toList());
		response.put("Reparaciones", reparacionesList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/vehiculo/{id}")
	public ResponseEntity<Map<String, Object>> vehicleReparationsList(@PathVariable(value="id") String matricula) {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();

		List<ReparacionDto2> reparacionesList = reparacionService.findAllReparaciones().stream()
				.filter(r -> r.getVehiculo().getMatricula().equalsIgnoreCase(matricula))
				.map(r -> modelMapper.map(r, ReparacionDto2.class)).collect(Collectors.toList());
		response.put("Reparaciones", reparacionesList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/mecanico/{id}")
	public ResponseEntity<Map<String, Object>> mecanicoReparationsList(@PathVariable(value="id") String dni) {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();

		List<ReparacionDto3> reparacionesList = reparacionService.findAllReparaciones().stream()
				.filter(r -> r.getMecanico().getDni().equalsIgnoreCase(dni))
				.map(r -> modelMapper.map(r, ReparacionDto3.class)).collect(Collectors.toList());
		response.put("Reparaciones", reparacionesList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> createReparacion(@RequestBody ReparacionDto rDto){
		Map<String, Object> response = new HashMap<String, Object>();
        ModelMapper modelMapper = new ModelMapper();
        
        Mecanico mecanico= mecanicoService.findMecanicoByDni(rDto.getMecanico().getDni());
        Vehiculo vehiculo= vehiculoService.findVehiculoByMatricula(rDto.getVehiculo().getMatricula());
        
        Reparacion currentReparacion = modelMapper.map(rDto, Reparacion.class);
		currentReparacion.setMecanico(mecanico);
		currentReparacion.setVehiculo(vehiculo);
		Reparacion newReparacion = null;

		try {
			newReparacion = reparacionService.createReparacion(currentReparacion);
		} catch (DataAccessException e) {
			response.put("message", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("newReparacion", modelMapper.map(newReparacion, ReparacionDto.class));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getReparacionById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();

		ReparacionDto reparacionDto = null;
		Reparacion reparacion = null;

		try {
			reparacion = reparacionService.findReparacionById(id);
		} catch (NoSuchElementException e) {
			response.put("mensaje", "La reparacion con id ".concat(id.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		reparacionDto = modelMapper.map(reparacion, ReparacionDto.class);
		response.put("myReparacion", reparacionDto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateReparacion(@PathVariable Long id, @RequestParam("precioReparacion") Double precio) {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();
		Reparacion myReparacion = new Reparacion();

		myReparacion = reparacionService.updateReparacion(id, precio);

		response.put("reparacionUpdated", modelMapper.map(myReparacion, ReparacionDto.class));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Object>> deleteReparacion(@PathVariable Long id) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			reparacionService.deleteReparacionById(id);
		} catch (IllegalArgumentException e) {
			response.put("mensaje", "La Reparacion con id ".concat(id.toString().concat(" no existe.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("reparacion eliminada", id);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/informes/ventasOrderByPriceDesc")
	public ResponseEntity<Map<String, Object>> getReparacionesByPriceDesc() {
		Map<String, Object> response = new HashMap<String, Object>();
		ModelMapper modelMapper = new ModelMapper();

		List<ReparacionDto> reparacionesList = reparacionService.getReparacionListByPrecioDesc().stream()
				.map(p -> modelMapper.map(p, ReparacionDto.class)).collect(Collectors.toList());
		response.put("Reparaciones", reparacionesList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
