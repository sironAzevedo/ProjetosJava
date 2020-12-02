package br.com.fiap.service;

import org.springframework.data.domain.Page;

import br.com.fiap.model.dto.StudentDTO;

public interface IStudentService {

	void save(StudentDTO dto);

	void update(String code, StudentDTO dto);

	void delete(String code);

	Page<StudentDTO> findAll(String page, String size);

	StudentDTO findByCode(String code);

}
