% SISTEMA DE LOGIN SEGURO
% NOME DO(A) ALUNO(A)
% 2026

# 1 INTRODUÇÃO

Este documento apresenta a estrutura de um sistema de login seguro desenvolvido individualmente como base reutilizável para um futuro Projeto de Formação e Conclusão. A aplicação separa a lógica de autenticação da apresentação e permite a adaptação do tema visual sem reescrever as regras principais.

# 2 OBJETIVOS

O objetivo geral é implementar cadastro, autenticação, encerramento de sessão e autorização por perfis. Os objetivos específicos são validar dados de entrada, armazenar senhas protegidas, persistir usuários e sessões no MongoDB Atlas e organizar o código em módulos simples.

# 3 TECNOLOGIAS E ESTRUTURA

Foram utilizados Java 17, Spring Boot, Spring MVC, Spring Security, Spring Data MongoDB, Spring Session MongoDB e Thymeleaf. O pacote `configuracao` concentra a segurança e a criação do usuário inicial. O pacote `usuario` concentra o documento MongoDB, o repositório, o formulário e o serviço. O pacote `controle` concentra as páginas web.

Os templates ficam em `src/main/resources/templates`, enquanto o CSS fica em `static/css`. Os fragmentos de cabeçalho, navegação e rodapé evitam repetição e facilitam a troca futura do tema.

# 4 SEGURANÇA

O Spring Security controla a autenticação pelo formulário `/login`. A senha recebida nunca é salva diretamente: o serviço aplica `BCryptPasswordEncoder` antes de persistir o usuário. O sistema também valida nome, email e tamanho mínimo da senha.

Existem três perfis: `ADMIN`, `PROFESSOR` e `ALUNO`. A lista de usuários exige `ADMIN`; a área de ensino exige `ADMIN` ou `PROFESSOR`; as áreas comuns exigem autenticação. O logout invalida a sessão e apaga o cookie de sessão. A proteção contra fixação de sessão troca o identificador após o login.

# 5 MONGODB ATLAS

A conexão é definida pela variável `MONGODB_URI`, descrita em `.env.example`. O banco padrão é `sistema_login`. A entidade `usuario` usa a coleção `usuarios`, com índice único para email. O Spring Session usa a coleção `sessoes`, permitindo que os dados da sessão sejam armazenados no MongoDB.

A string de conexão real não deve ser versionada. No Atlas, deve ser criado um usuário de banco com a menor permissão necessária e o acesso de rede deve ser limitado aos endereços utilizados. Em produção, a aplicação deve usar HTTPS e `COOKIE_SECURE=true`.

# 6 DECISÕES DE DESIGN

A interface usa páginas pequenas, fragmentos Thymeleaf e um único arquivo de estilo. A combinação de fundo claro, verde e amarelo busca uma leitura simples e permite mudar cores, tipografia e espaçamentos sem alterar os controladores. O conteúdo é responsivo para telas menores.

A escolha por classes e métodos com nomes minúsculos segue a orientação da atividade. Em uma equipe profissional, recomenda-se a convenção Java de nomes de classes iniciados em maiúscula, mas a implementação atual prioriza a padronização solicitada.

# 7 EXECUÇÃO E EVOLUÇÃO

Para executar, configure `MONGODB_URI` e `MONGODB_DATABASE`, instale Java 17 e Maven e rode `mvn spring-boot:run`. O README contém o passo a passo completo.

O projeto pode receber novos temas criando outros arquivos de template ou substituindo o CSS. Também pode receber novos módulos de domínio sem alterar `seguranca`, desde que os novos módulos definam suas próprias regras de autorização.

# REFERÊNCIAS

SPRING. Spring Security Reference. Disponível em: https://docs.spring.io/spring-security/reference/. Acesso em: 29 set. 2026.

MONGODB. MongoDB Atlas Documentation. Disponível em: https://www.mongodb.com/docs/atlas/. Acesso em: 29 set. 2026.

SPRING. Spring Session Reference. Disponível em: https://docs.spring.io/spring-session/reference/. Acesso em: 29 set. 2026.
