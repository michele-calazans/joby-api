package br.com.jobem.api.repositories;

import br.com.jobem.api.models.Experience;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    Optional<List<Experience>> findExperienceByStudentId(int id);

    boolean existsExperienceByStudentInformationUserId(int id);

    Optional<List<Experience>> findExperienceByStudentInformationUserId(int id);


    boolean existsExperienceByStudentId(int id);

}
