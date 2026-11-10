# Sistema Hospitalar — Sprint 2

Projeto da disciplina de Programação Modular. A Sprint 2 acrescenta ao sistema da Sprint 1 uma API REST feita com Java e Spring Boot, com persistência em MySQL e regras básicas de negócio.

## Tecnologias
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- MySQL
- Maven

## Como executar

1. Instale o JDK 17 (ou superior compatível) e o Maven.
2. Tenha o MySQL instalado ou use o Docker Compose que está nesta pasta.
3. Para iniciar o banco com Docker, execute dentro da pasta `backend`:

   ```bash
   docker compose up -d
   ```

4. Se estiver usando um MySQL local, crie o banco `sistema_hospitalar` e confira usuário e senha no arquivo `src/main/resources/application.properties`.
5. Ainda dentro de `backend`, inicie a API:

   ```bash
   mvn spring-boot:run
   ```

A API ficará disponível em `http://localhost:8080`.

> O projeto usa `root` como usuário e senha padrão no exemplo do Docker. Se seu MySQL tiver outra senha, altere `DB_PASSWORD` ou o arquivo `application.properties`.

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| GET, POST | `/pacientes` | Listar e cadastrar pacientes |
| GET, PUT, DELETE | `/pacientes/{id}` | Consultar, atualizar e excluir paciente |
| GET, POST | `/profissionais` | Listar e cadastrar profissionais |
| GET | `/profissionais/especialidade/{especialidade}` | Buscar médicos por especialidade |
| GET, POST | `/consultas` | Listar e cadastrar consultas |
| PUT | `/consultas/{id}/situacao` | Alterar a situação da consulta |
| GET, POST | `/internacoes` | Listar e cadastrar internações |
| POST | `/internacoes/{id}/alta` | Registrar alta |
| GET, POST | `/quartos` | Listar e cadastrar quartos |
| GET, PUT, DELETE | `/quartos/{id}` | Consultar, atualizar e excluir quarto |
| GET, POST | `/procedimentos-cirurgicos` | Listar e cadastrar cirurgias |
| PUT | `/procedimentos-cirurgicos/{id}/situacao` | Alterar a situação da cirurgia |

## Exemplos de requisição

### Cadastrar paciente — POST `/pacientes`

```json
{
  "nome": "Mariana Souza",
  "cpf": "12345678900",
  "dataNascimento": "1998-05-10",
  "telefone": "31999990000",
  "endereco": "Rua Central, 100",
  "email": "mariana@email.com"
}
```

### Cadastrar profissional — POST `/profissionais`

O campo `categoria` pode ser `CLINICO_GERAL`, `ESPECIALISTA` ou `CIRURGIAO`.

```json
{
  "categoria": "ESPECIALISTA",
  "nome": "Ana Costa",
  "registroProfissional": "CRM12345",
  "telefone": "31988887777",
  "email": "ana@email.com",
  "especialidade": "Cardiologia"
}
```

### Cadastrar consulta — POST `/consultas`

O campo `modalidade` pode ser `ROTINA`, `ESPECIALIZADA` ou `EMERGENCIA`. Use os IDs de paciente e profissional que já foram cadastrados.

```json
{
  "modalidade": "ROTINA",
  "pacienteId": 1,
  "profissionalId": 1,
  "data": "2026-12-01",
  "horario": "09:00:00",
  "motivo": "Consulta de rotina",
  "observacoesMedicas": ""
}
```

Para alterar uma situação, envie um texto JSON como `"CONFIRMADA"` para `/consultas/{id}/situacao`.

## Regras de negócio implementadas
- Cada categoria de profissional valida os atendimentos que pode realizar.
- Consulta especializada exige um médico especialista com a mesma especialidade solicitada.
- Atendimento de emergência exige prioridade: `BAIXA`, `MODERADA`, `ALTA` ou `CRITICA`.
- Um profissional não pode ter dois atendimentos ativos no mesmo dia e horário.
- Consulta concluída, cancelada ou com não comparecimento não pode voltar para uma situação anterior.
- Procedimento cirúrgico só pode ser concluído depois de iniciado.
- Internação exige quarto com vaga e não pode ultrapassar a capacidade máxima.
- Ao registrar alta, a ocupação e a situação do quarto são atualizadas.
- Os tipos de quarto são `ENFERMARIA`, `APARTAMENTO` e `UTI`.

## Organização
- `src/main/java`: classes de domínio, controllers, services, repositories e DTOs.
- `src/main/resources`: configurações da aplicação.
- `src/test/java`: testes simples das mudanças de situação.

O front-end estático da Sprint 1 foi mantido na pasta principal do projeto. Nesta Sprint, a API funciona separadamente e pode ser testada pelo Postman, Insomnia ou navegador.
