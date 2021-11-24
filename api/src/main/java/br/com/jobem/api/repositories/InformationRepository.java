package br.com.jobem.api.repositories;


import br.com.jobem.api.models.Information;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Integer> {
    Optional<Information> findById(int id);
}
