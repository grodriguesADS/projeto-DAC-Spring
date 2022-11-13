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

import br.edu.ifpb.dac.guilherme.projetojpa.business.service.ConverterService;
import br.edu.ifpb.dac.guilherme.projetojpa.business.service.OwnerService;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto.OwnerDTO;


@RestController
@RequestMapping("/api/owner")
public class OwnerController {
	
	@Autowired
	private ConverterService converterService;
	@Autowired
	private OwnerService ownerService;
	
	
	@PostMapping
	public ResponseEntity save(@RequestBody OwnerDTO dto) {
		try {
			Owner entity = converterService.dTOToOwner(dto);
			ownerService.save(entity);
			dto = converterService.ownerToDTO(entity);
			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody OwnerDTO dto) {
		try {
			dto.setId(id);
			Owner entity = converterService.dTOToOwner(dto);
			ownerService.update(entity);
			dto = converterService.ownerToDTO(entity);

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			ownerService.delete(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity find(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "licencePlate", required = false) String name ,
			@RequestParam(value = "licencePlate", required = false) String email) {

		try {
			Owner filter = new Owner();
			filter.setId(id);
			filter.setName(name);
			filter.setEmail(email);

			List<Owner> entities = ownerService.find(filter);
			List<OwnerDTO> dtos = converterService.listOwnerToDTO(entities);
			return ResponseEntity.ok(dtos);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
