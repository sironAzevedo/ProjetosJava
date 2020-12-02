package br.com.fiap.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.dto.StudentDTO;
import br.com.fiap.service.IStudentService;

@RestController
@RequestMapping(value = "/api/v1/student")
public class EstudanteController {
	
	@Autowired
	private IStudentService service;
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public void registerStudent(@Valid @RequestBody StudentDTO dto) {
		service.save(dto);
	}
	
	@PutMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public void updateStudent(
			@RequestParam(value = "code") String code, 
			@Valid @RequestBody StudentDTO dto) {
		service.update(code, dto);
	}
	
	@DeleteMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteStudent(@RequestParam(value = "code") String code) {
		service.delete(code);
	}
	
	@GetMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Page<StudentDTO> getAllStudent(
			@RequestParam(value = "page") String page,
			@RequestParam(value = "size") String size) {
		return service.findAll(page, size);
	}
	
	@ResponseBody
	@GetMapping(value = "/detail")
	@ResponseStatus(value = HttpStatus.OK)
	public StudentDTO getStudent(@RequestParam(value = "code") String code) {
		return service.findByCode(code);
	}
}
