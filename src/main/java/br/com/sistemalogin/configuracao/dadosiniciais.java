package br.com.sistemalogin.configuracao;

import br.com.sistemalogin.usuario.usuario;
import br.com.sistemalogin.usuario.usuariorepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class dadosiniciais {
    @Bean
    CommandLineRunner criaradmin(usuariorepositorio repositorio, PasswordEncoder codificador) {
        return args -> {
            if (!repositorio.existsByEmailIgnoreCase("admin@exemplo.com")) {
                repositorio.save(new usuario("Administrador", "admin@exemplo.com", codificador.encode("admin123"), "ADMIN"));
            }
        };
    }
}
