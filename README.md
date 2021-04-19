# poll-api
API para gerenciar pautas a serem votadas


## Instruções para build
Para rodar o projeto você precisa seguir alguns passos:

### Via Docker
Na raiz do projeto:
- mvn clean install
- docker-compose up -d --build

Após os comandos acima a api poderá ser acessada pela url localhost:8080

### IDE
Caso deseje rodar a aplicação na IDE, sem utilizar o docker, basta criar um run configuration do tipo Java Application, apontando para o projeto.
Será necessário uma instância do postgress rodando e as configurações para acesso ao banco deve ser realizado no arquivo application.yml.