package br.com.sistemalogin.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class usuarioform {
    @NotBlank(message = "informe seu nome")
    @Size(min = 3, max = 80, message = "o nome deve ter entre 3 e 80 caracteres")
    private String nome;

    @NotBlank(message = "informe seu email")
    @Email(message = "informe um email valido")
    private String email;

    @NotBlank(message = "informe uma senha")
    @Size(min = 6, message = "a senha deve ter pelo menos 6 caracteres")
    private String senha;

    private String papel = "ALUNO";

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getPapel() { return papel; }
    public void setPapel(String papel) { this.papel = papel; }
}
