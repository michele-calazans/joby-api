package br.com.jobem.api.repositories;

import br.com.jobem.api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByStudentInstituteId(int id);

    boolean existsByInformationUserId(int id);

    Student findByInformationUserId(int id);
}
