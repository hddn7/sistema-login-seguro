$paginas = @(
    @('SISTEMA DE LOGIN SEGURO', 'DOCUMENTACAO BASICA', '', 'NOME DO(A) ALUNO(A)', '2026', '', '1 INTRODUCAO', 'Sistema modular de cadastro, autenticacao e autorizacao.', 'A apresentacao fica separada da logica para facilitar novos temas.'),
    @('2 TECNOLOGIAS E ESTRUTURA', 'Java 17, Spring Boot, Spring Security, Thymeleaf,', 'Spring Data MongoDB e Spring Session MongoDB.', '', 'Pacotes principais:', 'configuracao: seguranca e dados iniciais.', 'controle: rotas web.', 'usuario: documento, repositorio e servico.', '', 'Templates ficam em templates e o tema em static/css.'),
    @('3 SEGURANCA', 'Senhas sao protegidas com BCryptPasswordEncoder.', 'O login e o logout sao controlados pelo Spring Security.', 'Perfis: ADMIN, PROFESSOR e ALUNO.', 'A lista de usuarios exige ADMIN.', 'A area de ensino exige ADMIN ou PROFESSOR.', 'A sessao usa fixacao protegida e e salva no MongoDB.'),
    @('4 MONGODB ATLAS E EXECUCAO', 'A conexao usa MONGODB_URI e MONGODB_DATABASE.', 'A colecao usuarios guarda os cadastros.', 'A colecao sessoes guarda as sessoes HTTP.', 'Nunca publique a URI real ou senhas no GitHub.', '', 'Execute com: mvn spring-boot:run', 'Acesse: http://localhost:8080', '', '5 EVOLUCAO', 'O CSS e os fragmentos podem ser trocados sem mudar', 'a autenticacao. Consulte o README para o Gitflow e o Atlas.')
)

$objetos = @()
$objetos += '1 0 obj`n<< /Type /Catalog /Pages 2 0 R >>`nendobj`n'
$objetos += '2 0 obj`n<< /Type /Pages /Kids [5 0 R 6 0 R 7 0 R 8 0 R] /Count 4 >>`nendobj`n'
$objetos += '3 0 obj`n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>`nendobj`n'
$objetos += '4 0 obj`n<< /Producer (sistema-login) >>`nendobj`n'
for ($pagina = 0; $pagina -lt 4; $pagina++) {
    $linhas = @('BT', '/F1 16 Tf', '72 750 Td')
    foreach ($linha in $paginas[$pagina]) {
        $limpa = $linha.Replace('\', '\\').Replace('(', '\(').Replace(')', '\)')
        $linhas += "($limpa) Tj"
        $linhas += '0 -24 Td'
    }
    $stream = ($linhas -join "`n") + "`nET"
    $objetos += "$(5 + $pagina) 0 obj`n<< /Length $($stream.Length) >>`nstream`n$stream`nendstream`nendobj`n"
}
$saida = "%PDF-1.4`n"
$offsetos = @(0)
foreach ($objeto in $objetos) {
    $offsetos += [System.Text.Encoding]::ASCII.GetByteCount($saida)
    $saida += $objeto
}
$xref = [System.Text.Encoding]::ASCII.GetByteCount($saida)
$saida += "xref`n0 $($objetos.Count + 1)`n0000000000 65535 f `n"
for ($i = 1; $i -lt $offsetos.Count; $i++) { $saida += ('{0:0000000000} 00000 n ' -f $offsetos[$i]) + "`n" }
$saida += "trailer`n<< /Size $($objetos.Count + 1) /Root 1 0 R >>`nstartxref`n$xref`n%%EOF`n"
[System.IO.File]::WriteAllText((Join-Path $PSScriptRoot 'documentacao.pdf'), $saida, [System.Text.Encoding]::ASCII)
