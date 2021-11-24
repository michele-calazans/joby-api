package br.com.jobem.api.auth;

import br.com.jobem.api.mocks.MockProfile;
import br.com.jobem.api.models.User;
import br.com.jobem.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sessions")
public class Auth {
    MockProfile profile = new MockProfile();

    @Autowired
    private UserRepository repository;

    @CrossOrigin
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody User user) {
        if (repository.existsByEmailAndPassword(user.getEmail(), user.getPassword())) {

            return ResponseEntity.ok().body(repository.findFirstByEmailAndPassword(user.getEmail(), user.getPassword()));
        }

        return ResponseEntity.notFound().build();
    }
}
