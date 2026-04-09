# SISTEMA DE ACHADOS E PERDIDOS IFPB MONTEIRO

##  Solução Web para Gerenciamento de Achados e Perdidos (IFPB Campus Monteiro)

Este projeto é um Sistema Web desenvolvido como Trabalho de Conclusão de Curso (TCC) para informatizar e otimizar o processo de gestão de itens perdidos e achados no Instituto Federal da Paraíba (IFPB), Campus Monteiro.

O objetivo principal é facilitar a comunicação e o registro dos itens, permitindo que a comunidade acadêmica e a administração possam encontrar ou devolver objetos de forma eficiente e transparente.

###  Repositório

* **Link:** https://github.com/cmigueldev/Achados-e-perdidos-do-IFPB-MONTEIRO.git

---

##  Tecnologias Utilizadas

A aplicação foi desenvolvida com foco em uma arquitetura robusta e moderna, utilizando as seguintes tecnologias:

| Categoria | Tecnologia | Versão Principal | Descrição |
| :---: | :---: | :---: | :--- |
| **Back-end** | **Java** | 17+ | Linguagem principal para o desenvolvimento da lógica de negócio. |
| | **Spring Boot** | 3.2.x | Framework para a criação da API REST e controle de dependências. |
| | **Spring Data JPA** | | Facilita a persistência de dados no banco de dados. |
| **Front-end** | **Thymeleaf** | | Motor de templates para renderização dinâmica das páginas HTML. |
| | **Bootstrap** | 5.x | Framework CSS para o design responsivo da interface. |
| **Banco de Dados** | **PostgreSQL** | (Recomendado) | Sistema de gerenciamento de banco de dados relacional. |
| **Build Tool** | **Maven** | 3.x | Gerenciador de dependências e automação de compilação. |

---

##  Configuração e Execução

Para executar o projeto localmente, siga os passos abaixo:

### Pré-requisitos

Certifique-se de ter instalado em sua máquina:

* **Java Development Kit (JDK) 17 ou superior.**
* **Apache Maven 3.6 ou superior.**
* **Um servidor PostgreSQL** ou altere as configurações do `application.properties` para usar outro banco (como H2, se preferir um banco em memória para testes rápidos).

### Passos para Rodar

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/cmigueldev/Achados-e-perdidos-do-IFPB-MONTEIRO.git](https://github.com/cmigueldev/Achados-e-perdidos-do-IFPB-MONTEIRO.git)
    cd Achados-e-perdidos-do-IFPB-MONTEIRO
    ```

2.  **Configurar o Banco de Dados:**
    * Crie um banco de dados PostgreSQL (ex: `achados_perdidos`).
    * Edite o arquivo `src/main/resources/application.properties` com as credenciais do seu banco de dados:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/achados_perdidos
        spring.datasource.username=seu_usuario
        spring.datasource.password=sua_senha
        spring.jpa.hibernate.ddl-auto=update # Ou 'create' na primeira execução
        ```

3.  **Compilar e Executar:**
    * Utilize o Maven para empacotar e rodar a aplicação:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4.  **Acessar a Aplicação:**
    A aplicação estará disponível em seu navegador no endereço:
    ```
    http://localhost:8080
    ```

---

##  Status e Contribuições

Este projeto está concluído em sua versão TCC (MVP – Produto Mínimo Viável).

* **Autores:** Carlos Miguel Barbosa de Araújo e Hendrick Diego Oliveira Pimentel
* **Orientador:** Prof. Dr. Paulo Roberto Pereira da Silva
* **Instituição:** IFPB – Campus Monteiro

### Trabalhos Futuros (Roadmap)
Conforme detalhado na conclusão do TCC, as próximas etapas incluem:
* Integração de segurança com **Spring Security**.
* Desenvolvimento de módulo de notificações por e-mail.
* Integração de login com o sistema acadêmico **SUAP**.
