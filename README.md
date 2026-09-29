# sistema de login seguro

projeto individual de um sistema de cadastro, login, logout e controle de acesso. a aplicação foi feita com java 17, spring boot, spring security, thymeleaf e mongodb atlas.

## perfis

- `admin`: acessa o painel e a lista de usuarios.
- `professor`: acessa o painel e a area de ensino.
- `aluno`: acessa somente as areas autenticadas liberadas para aluno.

novos cadastros podem escolher aluno ou professor. um usuario admin inicial e criado apenas quando ainda nao existe:

- email: `admin@exemplo.com`
- senha: `admin123`

troque essa senha em um ambiente real.

## requisitos

- java 17 ou superior
- maven 3.9 ou superior
- uma conta no mongodb atlas

## mongodb atlas

1. crie um cluster gratuito no mongodb atlas.
2. crie um usuario de banco com permissao de leitura e escrita.
3. em `network access`, permita o ip da sua maquina.
4. copie a connection string.
5. configure as variaveis de ambiente abaixo. nunca publique a senha real no github.

```powershell
$env:MONGODB_URI="mongodb+srv://usuario:senha@cluster.mongodb.net/?retryWrites=true&w=majority"
$env:MONGODB_DATABASE="sistema_login"
$env:COOKIE_SECURE="false"
```

as colecoes `usuarios` e `sessoes` sao criadas/usadas automaticamente. a sessao http nao fica apenas na memoria da aplicacao: o spring session salva os dados na colecao `sessoes`.

## executar localmente

```powershell
mvn spring-boot:run
```

acesse `http://localhost:8080`.

para gerar o pacote:

```powershell
mvn clean package
java -jar target/sistema-login-0.0.1-SNAPSHOT.jar
```

em producao use `COOKIE_SECURE=true` com https.

## estrutura

```text
src/main/java/br/com/sistemalogin
  configuracao/  seguranca e dados iniciais
  controle/      rotas web
  usuario/       documento, repositorio, formulario e servico
src/main/resources
  templates/     paginas thymeleaf e fragmentos reutilizaveis
  static/css/    tema visual separado da logica
```

o tema esta concentrado em `static/css/estilo.css` e os elementos repetidos ficam em `templates/fragments`. por isso a identidade visual pode ser trocada sem alterar o servico de autenticacao.

## gitflow e github

use uma branch `develop` para desenvolvimento e branches curtas como `feature/login-seguro` para cada funcionalidade. depois abra um pull request para `develop`; quando a entrega estiver revisada, mescle `develop` em `main`.

```powershell
git init
git add .
git commit -m "feat: cria sistema de login seguro"
git branch -M main
git remote add origin https://github.com/seu-usuario/sistema-login.git
git push -u origin main
```

nao inclua `.env`, senhas, chaves ou a uri real do atlas.

## documentacao

a documentacao academica esta em `docs/documentacao.md` e o pdf pronto esta em `docs/documentacao.pdf`. para regenerar o arquivo sem pandoc, use o script incluido:

```powershell
powershell -ExecutionPolicy Bypass -File docs/gerarpdf.ps1
```

o markdown e a fonte principal para futuras edicoes mais completas conforme a orientacao da escola.
