package br.com.sistemalogin.usuario;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface usuariorepositorio extends MongoRepository<usuario, String> {
    Optional<usuario> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}
