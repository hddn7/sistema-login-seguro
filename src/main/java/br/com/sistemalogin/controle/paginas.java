package br.com.sistemalogin.controle;

import br.com.sistemalogin.usuario.usuarioform;
import br.com.sistemalogin.usuario.usuarioservico;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class paginas {
    private final usuarioservico servico;

    public paginas(usuarioservico servico) { this.servico = servico; }

    @GetMapping("/")
    public String inicio() { return "inicio"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/cadastro")
    public String cadastro(Model modelo) {
        modelo.addAttribute("usuarioform", new usuarioform());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@Valid @ModelAttribute("usuarioform") usuarioform formulario,
                            BindingResult resultado, Model modelo) {
        if (resultado.hasErrors()) return "cadastro";
        try {
            servico.cadastrar(formulario);
            return "redirect:/login?cadastrado=true";
        } catch (IllegalArgumentException erro) {
            modelo.addAttribute("erroCadastro", erro.getMessage());
            return "cadastro";
        }
    }

    @GetMapping("/painel")
    public String painel() { return "painel"; }

    @GetMapping("/usuarios")
    public String usuarios(Model modelo) {
        modelo.addAttribute("usuarios", servico.listar());
        return "usuarios";
    }

    @PostMapping("/usuarios/{id}/excluir")
    public String excluir(@PathVariable String id) {
        servico.excluir(id);
        return "redirect:/usuarios";
    }
}
