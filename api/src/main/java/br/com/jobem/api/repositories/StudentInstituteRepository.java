package br.com.jobem.api.repositories;

import br.com.jobem.api.models.CourseInstitute;
import br.com.jobem.api.models.StudentInstitute;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentInstituteRepository extends JpaRepository<StudentInstitute, Integer> {
     Optional<StudentInstitute> findByCpf(String cpf);
     boolean existsByCpf(String cpf);
     int countByCourseInstitute_InstituteId(int id);

     @Query("SELECT ci FROM course_institute ci " +
             "WHERE ci.name = ?1 AND ci.institute.name = ?2")
     Optional<CourseInstitute> getStudentInstitute(String courseName, String instituteName);
}
