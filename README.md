# Sanitizer Bean - Framework
> Este repositório contém a implementação de um framework de validação (pattern validation, regex validation, pattern validation, etc.) extensível a aplicações que não demandam alta complexibilidade.

## 💻 Pré-requisitos
Para executar este projeto, certifique-se de ter as seguintes ferramentas configuradas no seu ambiente:
- **Java 21** ou superior;
- **Apache Maven** para gerenciamento de dependências e build do projeto;
- Editor ou IDE de sua escolha (IntelliJ IDEA, Eclipse, VS Code, etc.);
- Um projeto necessário de implementação;

## 🚀 Sobre o projeto
Todas as anotations do projeto contam com JavaDoc, explicando a entrada de seus valores e saída da validação esperada. As annotations do projeto, são:
```java
@DateRangeValidation
@MatchValidation
@LogicExpressionValidation
@RegexValidation
@SizingValidation
@RemoveCharsValidation
@DateFormatValidation
@CollectionValidation
@BlankValidation
```


### ✨ Funcionalidades
1- É gerado uma classe que possuí atributos, esses atributos possuem valores, e com as annotations do Sanitizer esses valores são verificados e retornados por 2 vias:
- Relatório PDF do conteúdo de variáveis com erro;
- Throwable direto que uma validação incorreta é capturada;
O estouro do erro com base em PDF ou Throwable, é com base na seguinte configuração:
```yaml
  exception.validation.type=Report
```
2- Cada uma dessas excecoes capturadas, gera um log, flexibilizado também via variável de ambiente, dos seguintes valores:
```yaml
  exception.regex.field=The field does not match the specified pattern.
  exception.sizing.field=The field size is outside the allowed limits.
  exception.blank.field=This field cannot be blank or null.
  exception.match.field=The field cannot contain the specified value.
  exception.dateFormat.field=The date format does not match the expected pattern.
  exception.logic.field=The logical expression was not satisfied.
  exception.collection.field=The collection is empty or invalid.
  exception.dateRange.field=The date is outside the allowed range.
  exception.sanitize.field=Invalid character(s) found in the field value.
```
Dentre as validações existentes, estão:
- Validação de caracteres em branco;
- Validacao de regex (aceitando o regex na entrada, ou, usando um default);
- Remoção de caracteres indesejados;
- Range de data com base em 2 períodos;
- Pattern da data atual;
- Match de 2 campos;
- Expressão lógica com base no valor;
- Tamanho de campo;
- Coleções e listas vazias;
  
### 🛠️ Tecnologias Utilizadas
- **Java**: Linguagem de programação principal do projeto;
- **Apache Maven**: Ferramenta de build e gerenciamento de dependências;
- **Spring Boot**: Framework java;

## 🛠️ Configuração e Execução

1. Clone este repositório:
```bash
   git clone https://github.com/enzokaua/auth-server
```
2. Importe ele em seu projeto atual;
3. Gere a validação da classe que deseja;
```java
  SanitizerFramework.classValidate(myClass);
```
4. Verifique a saída com base na propriedade setada;

