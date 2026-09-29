package br.com.sistemalogin.configuracao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class segurancateste {
    @Test
    void deve_criar_hash_bcrypt_diferente_da_senha() {
        BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();
        String senha = "senha123";
        String hash = codificador.encode(senha);

        assertThat(hash).startsWith("$2");
        assertThat(hash).isNotEqualTo(senha);
        assertThat(codificador.matches(senha, hash)).isTrue();
    }
}
