Atualização da nova versão:

-Corrigida a lógica para chegar ao resultado esperado. Adicionada expressão regular no "split" que verifica se os produtores aparecem com um produtor ou como um conjunto de produtores.
-Adicionada novas dependências para os testes de integração
-Implementado teste de integração

# teste-spring-boot
 Estudo teste de Spring Boot

Estudo desenvolvido para responder a uma proposta/teste. A API desenvolvida possibilita a leitura de um arquivo em CSV contendo uma lista de filmes, produtores e estúdios indicados a um famoso festival de cinema.

O projeto utiliza o Maven. Clone o repositório e rode mvn spring-boot:run a partir de um terminal


Após o servidor iniciado a aplicação irá partir do arquivo "service/incial.java" onde métodos para a leitura do arquivos.CSV, criação do banco de dados e preenchimento dos dados acontece. A maioria das funcionalidades giram em torno das tabelas filme, produtor e filme_produtores. A tabela filme_produtor permite que mais de um produtor possam ter dirigido um filme. Percebi que em alguns momentos os nomes dos produtores aparecem separados por ";" ou "and" no CSV, então tive um cuidado para separar e relacionar os diretores ao filme. 

A API foi implementada com base no nível 2 de maturidade de Richardson. A implementação dos verbos HTTP podem ser verificadas em "controllers/ProdutoresControllers.java".

Exemplos: 
Endpoint: localhost:8080/produtor/
Verbo: POST, adicione um JSON ao "body". Ex: 
{
	"nome" : "Steven Spielberg"
}

Verbo: GET, lista todos os produtores.
Verbo: PUT, use localhost:8080/produtor/{id}, onde ID é o identificador do registro a ser alterado. Adicione um JSON ao corpo. Ex:
{
	"nome" : "George P. Cosmatos"
}

Verbo: DELETE, use localhost:8080/produtor/{id} onde o ID é o identificador do registro a ser excluído.
Verbo: GET, use localhost:8080/produtor/{id} para buscar o registro referente ao ID informado.

O endpoint que traz o ressultado solicitado na proposta é o http://localhost:8080/.


