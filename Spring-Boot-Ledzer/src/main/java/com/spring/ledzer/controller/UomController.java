package com.spring.ledzer.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Uom;
import com.spring.ledzer.model.dto.UomDTO;
import com.spring.ledzer.repository.UomRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/uom")
public class UomController {

	@Autowired
	UomRepository repository;

	@GetMapping("/uom_list")
	//@PreAuthorize("hasRole('ADMIN')")
	public List<UomDTO> getAllUoms() {
		System.out.println("Get all Uoms...");
		List<Uom> uoms = repository.findAll();
        return uoms.stream().map(uom -> convertToDto(uom)).collect(Collectors.toList());
	}

	@PostMapping(value = "/create")
	public Uom postUom(@RequestBody Uom _uom) {

		Uom uom = repository.save(new Uom(_uom.getName()));
		return uom;
	}
	
	/**
	 * This method returns the items in store by ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/{id}")
	public ResponseEntity<Uom> getUom(@PathVariable("id") Long id) throws ResourceNotFoundException {
			Uom uom = repository.findById(id)
			  .orElseThrow(() -> new ResourceNotFoundException("Uom not found for this id :: " + id));
			return ResponseEntity.ok().body(uom);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Uom> updateUom(@PathVariable("id") long id, @RequestBody Uom uom) {
		System.out.println("Update Uom with ID = " + id + "...");

		Optional<Uom> uomData = repository.findById(id);

		if (uomData.isPresent()) {
			Uom _uom = uomData.get();
			_uom.setName(uom.getName());
			return new ResponseEntity<>(repository.save(_uom), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Uom> deleteUom(@PathVariable("id") Long id) throws ResourceNotFoundException {
		
		Uom uom = repository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Uom not found for this id :: " + id));
        repository.delete(uom);
        //return new ResponseEntity<>("Uom has been deleted!", HttpStatus.OK);
		return ResponseEntity.ok().body(uom);
		
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAllUoms() {
		System.out.println("Delete All uomDTOs...");

		repository.deleteAll();

		return new ResponseEntity<>("All uomDTOs have been deleted!", HttpStatus.OK);
	}
	
	@SuppressWarnings("unused")
	private UomDTO convertToDto(Uom uom) {
		ModelMapper modelMapper = new ModelMapper();
	    UomDTO uomDTO = modelMapper.map(uom, UomDTO.class);
	    return uomDTO;
	}
	
}
