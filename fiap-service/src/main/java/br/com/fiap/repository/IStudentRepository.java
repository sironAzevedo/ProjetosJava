package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student, String> {

}
