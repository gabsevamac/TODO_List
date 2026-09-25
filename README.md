# TODO List - Java Project

Este é um projeto de **TODO List (Lista de Tarefas)** desenvolvido em Java. Ele serve como uma excelente base de estudos para fixação de conceitos de programação orientada a objetos (POO), estruturação de código, manipulações de datas, enums e coleções. Além disso, a arquitetura do projeto foi pensada para servir como material de treinamento e prática para a escrita de **testes automatizados**.

## 🎯 Objetivo

O principal objetivo deste projeto não é ser uma aplicação robusta para produção, mas sim um ambiente controlado e didático focado em:
- **Reforço de Conceitos:** Uso de exceções customizadas ou nativas, encapsulamento, separação de responsabilidades e manipulação de fluxos em linha de comando (CLI).
- **Treinamento de Testes:** A lógica de negócios está separada da interface de usuário (`Main.java` vs `Program.java` e modelo `Task.java`), facilitando a criação de testes unitários utilizando bibliotecas como JUnit e Mockito.

## 🚀 Como Rodar o Projeto

### Pré-requisitos
- **Java JDK 17+** instalado na sua máquina.
- **IDE Java** de sua preferência (IntelliJ IDEA, Eclipse, VS Code) recomendada.

### Passos para Execução
1. Clone ou baixe este repositório para o seu ambiente local.
2. Navegue até a pasta raiz do projeto.
3. Se estiver usando uma IDE:
   - Importe o projeto como um projeto Java.
   - Localize a classe `Main.java` dentro do pacote `com.xoris`.
   - Clique com o botão direito e selecione **Run 'Main.main()'**.
4. Se estiver usando linha de comando (Terminal/CMD):
   ```bash
   # Navegue até a pasta de código fonte
   cd src/main/java
   
   # Compile os arquivos
   javac com/xoris/*.java com/xoris/enums/*.java com/xoris/models/*.java
   
   # Execute o programa
   java com.xoris.Main
   ```

## 🛠 Boas Práticas Adotadas

Durante o desenvolvimento deste projeto, procurou-se seguir boas práticas de engenharia de software:

- **Tratamento de Exceções Resiliente:** Implementação de loops seguros e tratamento com `try-catch` para captura de erros de entrada de usuário (`NumberFormatException`, `DateTimeParseException`, etc.), evitando que o programa encerre de forma inesperada por erros de digitação.
- **Isolamento de Funções:** Criação de métodos auxiliares para rotinas de leitura de inputs (ex: `readInt()`, `readDate()`), mantendo o código conciso e evitando repetição (DRY - Don't Repeat Yourself).
- **Uso de Enums:** Categorias (`TaskCategory`) e Prioridades (`Priority`) são tipadas usando *Enums*, garantindo a integridade dos dados e prevenindo erros de tipagem (Type Safety).
- **Switch Expressions:** Utilização de blocos `switch` modernos do Java para retornos diretos e expressivos.
- **Separação de Lógica de Negócios (SoC):** O modelo `Task` e a classe gerenciadora `Program` mantêm suas responsabilidades restritas ao estado e validações, deixando a `Main` apenas com o papel de interagir com o usuário via console.

## 🧪 Como praticar testes com este projeto?

Para quem deseja treinar a criação de testes e se aprofundar, sugere-se a seguinte trilha:
1. Adicione a dependência do **JUnit 5** no seu projeto (via Maven/Gradle).
2. Crie testes unitários para a classe `Program`, validando se a criação de tarefas respeita os parâmetros passados.
3. Crie testes para os métodos de filtro (`getTasksByCategory`, `getTasksByPriority`, etc.), validando desde listas vazias até múltiplos resultados esperados.
4. Teste casos de erro, forçando os cenários descritos nas validações da lógica para garantir que a exceção correta é lançada (usando `assertThrows`).

Sinta-se à vontade para clonar, modificar, aprimorar a estrutura e adicionar novas funcionalidades enquanto pratica!
