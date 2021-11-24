package br.com.jobem.api.repositories;

import br.com.jobem.api.models.CourseInstitute;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInstituteRepository extends JpaRepository<CourseInstitute, Integer> {
    List<CourseInstitute> findByInstituteId(int id);
}
