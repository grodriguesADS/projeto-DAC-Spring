package br.edu.ifpb.dac.guilherme.projetojpa.presentation.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Car;
import br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto.CarDTO;
import br.edu.ifpb.dac.guilherme.projetojpa.service.CarService;
import br.edu.ifpb.dac.guilherme.projetojpa.service.ConverterService;

@RestController
@RequestMapping("/api/car")
public class CarController {

	@Autowired
	private ConverterService converterService;

	@Autowired
	private CarService carService;

	@PostMapping
	public ResponseEntity save(@RequestBody CarDTO dto) {
		try {
			Car entity = converterService.dTOToCar(dto);
			carService.save(entity);
			dto = converterService.carToDTO(entity);
			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody CarDTO dto) {
		try {
			dto.setId(id);
			Car entity = converterService.dTOToCar(dto);
			carService.update(entity);
			dto = converterService.carToDTO(entity);

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			carService.delete(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity find(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "licensePlate", required = false) String licensePlate) {

		try {
			Car filter = new Car();
			filter.setId(id);
			filter.setLicensePlate(licensePlate);

			List<Car> entities = carService.find(filter);
			List<CarDTO> dtos = converterService.listCarToDTO(entities);
			return ResponseEntity.ok(dtos);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
