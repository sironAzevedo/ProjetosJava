package br.com.fiap.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.handler.exception.EmptyResultDataAccessException;
import br.com.fiap.mapper.StudentMapper;
import br.com.fiap.model.Student;
import br.com.fiap.model.dto.StudentDTO;
import br.com.fiap.repository.IStudentRepository;
import br.com.fiap.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private IStudentRepository repository;

	@Override
	@CacheEvict(cacheNames = StudentDTO.CACHE_NAME, allEntries = true)
	public void save(StudentDTO dto) {
		Student student = StudentMapper.INSTANCE.toStudentSave(dto);
		repository.save(student);
	}

	@Override
	@CacheEvict(cacheNames = StudentDTO.CACHE_NAME, allEntries = true)
	public void update(String code, StudentDTO dto) {
		this.findByCode(code);
		Student student = StudentMapper.INSTANCE.toStudent(dto);
		repository.save(student);
	}

	@Override
	@CacheEvict(cacheNames = StudentDTO.CACHE_NAME, allEntries = true)
	public void delete(String code) {
		StudentDTO result = this.findByCode(code);
		repository.deleteById(result.getCode());
	}

	@Override
	@Cacheable(cacheNames = StudentDTO.CACHE_NAME, key="#page + #size")
	public Page<StudentDTO> findAll(String page, String size) {
		Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size));
		Page<StudentDTO> result = repository.findAll(pageable).map(StudentMapper.INSTANCE::toStudentDTO);
		return new PageImpl<>(result.stream().collect(Collectors.toList()), pageable, result.getTotalElements());
	}

	@Override
	@Cacheable(cacheNames = StudentDTO.CACHE_NAME, key="#code")
	public StudentDTO findByCode(final String code) {
		Optional<Student> result = repository.findById(code);
		if(!result.isPresent()) {
			throw new EmptyResultDataAccessException("Nenhum dado encontrado");
		}
		
		return StudentMapper.INSTANCE.toStudentDTO(result.get());
	}
}
