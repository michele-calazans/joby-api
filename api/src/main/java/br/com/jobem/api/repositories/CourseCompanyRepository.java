package br.com.jobem.api.repositories;

import br.com.jobem.api.models.CourseCompany;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCompanyRepository extends JpaRepository<CourseCompany, Integer> {
     CourseCompany findByName(String name);
     List<CourseCompany> findByCompanyId(int id);
}
