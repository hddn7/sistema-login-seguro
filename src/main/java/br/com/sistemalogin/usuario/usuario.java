package br.com.sistemalogin.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class usuario {
    @Id
    private String id;

    @NotBlank(message = "informe seu nome")
    @Size(min = 3, max = 80, message = "o nome deve ter entre 3 e 80 caracteres")
    private String nome;

    @NotBlank(message = "informe seu email")
    @Email(message = "informe um email valido")
    @Indexed(unique = true)
    private String email;

    private String senha;
    private String papel;
    private boolean ativo = true;

    public usuario() { }

    public usuario(String nome, String email, String senha, String papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getPapel() { return papel; }
    public void setPapel(String papel) { this.papel = papel; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
