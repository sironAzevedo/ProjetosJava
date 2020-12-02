package br.com.fiap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.fiap.model.Student;
import br.com.fiap.model.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	@Mappings({
	      @Mapping(target="code", source="dto.code"),
	      @Mapping(target="name", source="dto.name"),
	      @Mapping(target="email", source="dto.email")
	    })
	Student toStudent(StudentDTO dto);
	
	@Mappings({
	      @Mapping(target="code", source="dto.code", defaultExpression = "java(java.util.UUID.randomUUID().toString().replaceAll(\"-\", \"\").substring(1, 16))"),
	      @Mapping(target="name", source="dto.name"),
	      @Mapping(target="email", source="dto.email")
	    })
	Student toStudentSave(StudentDTO dto);
	
	
	@Mappings({
	      @Mapping(target="code", source="entity.code"),
	      @Mapping(target="name", source="entity.name"),
	      @Mapping(target="email", source="entity.email")
	    })
	StudentDTO toStudentDTO(Student entity);

}
