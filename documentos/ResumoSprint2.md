# Sprint 2 — Sistema Hospitalar

## Objetivo
Nesta etapa, o sistema da Sprint 1 recebeu uma API REST para cadastrar e consultar os dados principais do hospital e aplicar algumas regras de negócio.

## Organização do código
O back-end foi separado em camadas:
- **Model:** representa as classes do sistema, como Paciente, ProfissionalSaude, Consulta, Internacao e Quarto.
- **Repository:** faz o acesso aos dados usando Spring Data JPA.
- **Service:** concentra as regras de negócio.
- **Controller:** recebe as requisições HTTP e retorna as respostas da API.

## Novas classes e regras
- ProfissionalSaude é uma classe abstrata. As categorias Médico Clínico Geral, Médico Especialista e Cirurgião herdam dela.
- Atendimento é uma classe abstrata com dados comuns. Consulta de Rotina, Consulta Especializada, Atendimento de Emergência e Procedimento Cirúrgico representam modalidades diferentes.
- As consultas usam o enum SituacaoConsulta, com as situações Agendada, Confirmada, Em atendimento, Concluída, Cancelada e Não compareceu.
- O procedimento cirúrgico possui situação própria e só pode ser concluído depois de iniciado.
- O quarto possui tipo (Enfermaria, Apartamento ou UTI), capacidade e situação. Nesta versão simples, Apartamento e UTI possuem capacidade para um paciente; a Enfermaria pode ter capacidade maior.
- O sistema verifica se o profissional já possui outro atendimento no mesmo dia e horário.
- Uma internação só é cadastrada quando há vaga no quarto. Ao registrar a alta, a ocupação do quarto é atualizada.

## API
Foram implementados os endpoints principais `/pacientes`, `/profissionais`, `/consultas`, `/internacoes` e `/quartos`. Também foi criado `/procedimentos-cirurgicos` para registrar as cirurgias.

## Banco de dados
Foi escolhido o MySQL por ser um banco relacional adequado para os dados do sistema, que possuem relações entre pacientes, profissionais, consultas, internações e quartos. O uso de chaves e relacionamentos ajuda a manter os dados organizados e consistentes.

## Como testar
As instruções para iniciar a aplicação estão no arquivo `backend/README.md`. Alguns exemplos de requisições também estão em `backend/testes-api.http`.
