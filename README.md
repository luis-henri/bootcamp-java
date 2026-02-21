# Bootcamp Java

Este é um projeto de **Bootcamp Java** focado em criar um **CRUD** (Create, Read, Update, Delete) e aplicar os conhecimentos adquiridos em cada aula. O objetivo é praticar conceitos de Java, Maven, Hibernate e banco de dados H2 ao longo do curso.

O projeto implementa um **Cadastro de Usuário Simples**, listagem, edição e exclusão, utilizando Hibernate e banco H2 em modo local.

---

## Requisitos para instalação

Antes de rodar o projeto, certifique-se de ter instalado na sua máquina:

| Requisito | Versão mínima |
|-----------|---------------|
| **Java (JDK)** | 21 |
| **Maven**     | 3.6+ |

### Como verificar se estão instalados

```bash
java -version
mvn -version
```

Se algum comando não for reconhecido, será necessário instalar o componente correspondente.

### Instalação do Java 21

- **Windows:** Baixe o [JDK 21](https://adoptium.net/) ou use o instalador do Oracle e configure a variável `JAVA_HOME`.
- **macOS:** `brew install openjdk@21`
- **Linux (Ubuntu/Debian):** `sudo apt install openjdk-21-jdk`

### Instalação do Maven

- **Windows:** Baixe em [maven.apache.org](https://maven.apache.org/download.cgi), extraia e adicione o `bin` ao `PATH`.
- **macOS:** `brew install maven`
- **Linux (Ubuntu/Debian):** `sudo apt install maven`

---

## Como clonar o repositório

### Bash (Linux / Git Bash no Windows)

```bash
git clone https://github.com/luis-henri/bootcamp-java.git
cd bootcamp-java
```

### Windows (PowerShell ou CMD)

```powershell
git clone https://github.com/luis-henri/bootcamp-java.git
cd bootcamp-java
```

### macOS (Terminal)

```bash
git clone https://github.com/luis-henri/bootcamp-java.git
cd bootcamp-java
```

> **Nota:** O comando `git clone` é o mesmo em todos os terminais. A diferença está apenas no interpretador de comandos (Bash, PowerShell, CMD ou Terminal do macOS).

---

## Como executar o projeto

1. Entre na pasta do projeto:

   ```bash
   cd bootcamp-java
   ```

2. Baixe as dependências e compile:

   ```bash
   mvn clean install
   ```

3. Execute a aplicação:

   ```bash
   mvn exec:java -Dexec.mainClass="Main"
   ```

   Ou, se preferir rodar pela IDE, execute a classe `Main.java`.

4. O **H2 Console** ficará disponível em: [http://localhost:8082](http://localhost:8082) para visualizar o banco de dados.

---

## Estrutura do projeto

```
bootcamp-java/
├── src/main/java/
│   ├── Main.java              # Ponto de entrada da aplicação
│   ├── model/Usuario.java     # Entidade Usuário
│   ├── repository/UsuarioRepository.java
│   └── service/UsuarioService.java
├── src/main/resources/META-INF/
│   └── persistence.xml       # Configuração JPA/Hibernate
├── pom.xml                   # Dependências Maven
└── README.md
```

---

## Funcionalidades do CRUD

- **1** – Cadastrar usuário (nome e email)
- **2** – Listar todos os usuários
- **3** – Editar usuário por ID
- **4** – Excluir usuário por ID
- **0** – Sair do sistema