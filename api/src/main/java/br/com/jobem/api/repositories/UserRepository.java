package br.com.jobem.api.repositories;

import br.com.jobem.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

        boolean existsByEmail(String email);

        boolean existsByEmailAndPassword(String email, String password);

        User findFirstByEmailAndPassword(String email, String password);
}
