package br.com.sistemalogin.usuario;

import java.util.List;
import java.util.Locale;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class usuarioservico implements UserDetailsService {
    private final usuariorepositorio repositorio;
    private final PasswordEncoder codificador;

    public usuarioservico(usuariorepositorio repositorio, PasswordEncoder codificador) {
        this.repositorio = repositorio;
        this.codificador = codificador;
    }

    public usuario cadastrar(usuarioform formulario) {
        String email = formulario.getEmail().trim().toLowerCase(Locale.ROOT);
        if (repositorio.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("este email ja esta cadastrado");
        }
        String papel = formulario.getPapel() == null ? "ALUNO" : formulario.getPapel().toUpperCase(Locale.ROOT);
        if (!List.of("ADMIN", "PROFESSOR", "ALUNO").contains(papel)) {
            papel = "ALUNO";
        }
        usuario novo = new usuario(formulario.getNome().trim(), email, codificador.encode(formulario.getSenha()), papel);
        return repositorio.save(novo);
    }

    public List<usuario> listar() { return repositorio.findAll(); }

    public void excluir(String id) { repositorio.deleteById(id); }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        usuario encontrado = repositorio.findByEmailIgnoreCase(email)
            .orElseThrow(() -> new UsernameNotFoundException("usuario nao encontrado"));
        return User.withUsername(encontrado.getEmail())
            .password(encontrado.getSenha())
            .roles(encontrado.getPapel())
            .disabled(!encontrado.isAtivo())
            .build();
    }
}
