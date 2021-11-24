package br.com.jobem.api.repositories;

import br.com.jobem.api.models.Institute;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute, Integer> {
    Optional<Institute> findByInformation_User_Id(int id);
}
