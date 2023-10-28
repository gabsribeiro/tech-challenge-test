# Tech-Challenge

Desafio desenvolvido com Kubernets e Clean-Architecture para a fase02 do curso de Software Architecture da FIAP Pós Tech.

## Versão
2.2 [Dockerhub](https://hub.docker.com/repository/docker/pedrovcorsino/tech_challenge/tags "Go to Dockerhub")

## Índice
<a href="#tecnologias">Tecnologias</a> 

<a href="#mudanças-em-relação-à-versão-anterior">Mudanças em relação à versão anterior</a> 

<a href="#como-rodar-a-aplicação">Como rodar a aplicação</a> 

<a href="criterios-de-aceite">Criterios de aceite</a> 

<a href="#autores">Autores</a>

## Tecnologias
<div style="display: inline_block"><br>
    <img align="center" alt="java" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg">    
    <img align="center" alt="jspring" height="40" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" />  
    <img align="center" alt="postgre" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg">
    <img align="center" alt="kubernets" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kubernetes/kubernetes-plain-wordmark.svg">  
</div>

## Mudanças em relação à versão anterior

A versão da fase01 pode ser encontrada [aqui](https://github.com/PedroVCorsino/Tech-Challenge01/ "Go to fase01").

Trocamos a Arquitetura Hexagonal por Arquitetura limpa, essas mudanças podem ser melhor observadas na alteração de estrutura dos pacotes e diretórios.

![Captura de tela de 2023-09-03 21-02-07](https://github.com/PedroVCorsino/Tech-Challenge02/assets/61948860/78c6ac29-c619-4f23-8551-c2f049a79557)

Cada pacote serve para separar e agrupar as classes da aplicação de acordo com as divisão de camadas proposta na arquitetura limpa.

![Captura de tela de 2023-09-03 21-03-54](https://github.com/PedroVCorsino/Tech-Challenge02/assets/61948860/a5a03735-1300-47a4-a07c-942ee30d5480)


- Enterprise Business Rules  no pacote Domain
- Application Business Rules no pacote Application
- Interface Adapters no pacote Adapters
- Framworks & Drivers no pacote external

## Como rodar a aplicação
Clone este repositório
```bash
$ git clone https://github.com/PedroVCorsino/Tech-Challenge2.git
```
- Abra o projeto na IDE de sua preferência

- Abra o diretório: [deploy-kubernetes](https://github.com/PedroVCorsino/Tech-Challenge02/tree/main/deploy-kubernetes "Go to deploy-kubernetes")

- Utilizamos o kubernetes rodando via docker desktop

- Aplicar os seguintes manifestos:
  
```bash
$ kubectl apply -f secrets.yaml
$ kubectl apply -f postgress-deployment.yaml
$ kubectl apply -f app-deployment.yaml
```

## Crieterios de aceite

### Kubernets
- Todos os arquivos para deploy podem ser encontrados no diretorio [deploy-kubernetes](https://github.com/PedroVCorsino/Tech-Challenge02/tree/main/deploy-kubernetes "Go to deploy-kubernetes")

### Api 
 - Após subir a aplicação recomendo usar o Swagger para testar os endpoints
http://localhost:30001/swagger-ui/index.html#/

### Checkout do Pedido e alteraçoes no status
- Ao cadastrar um novo pedido a api retorna um um json com o pedido criado nele é possivel encontrar o ID do pedido para identifica-lo.
  
![Captura de tela de 2023-09-03 21-15-46](https://github.com/PedroVCorsino/Tech-Challenge02/assets/61948860/f9f7ea20-be18-4e28-850f-481fd9573b01)
- Para testar 
    - swagger-ui/index.html#/pedido-api/createPedido
    - Ou um `POST` para `/api/pedido`
```
{
  "idCliente": 1,
  "combos": [
    {
      "id": 0,
      "lanche": {
        "id": 1,
        "nome": "Hambúrguer",
        "descricao": "Delicioso hambúrguer artesanal",
        "preco": 10.99,
        "categoria": "LANCHE"
      },
      "acompanhamento": {
        "id": 1,
        "nome": "Batata Frita",
        "descricao": "Porção de batatas fritas crocantes",
        "preco": 5.99,
        "categoria": "ACOMPANHAMENTO"
      },
      "bebida": {
        "id": 1,
        "nome": "Refrigerante",
        "descricao": "Bebida gaseificada sabor cola",
        "preco": 4.99,
        "categoria": "BEBIDA"
      },
      "sobremesa": {
        "id": 5,
        "nome": "Torta de Limão",
        "descricao": "Torta doce com recheio de limão",
        "preco": 6.99,
        "categoria": "SOBREMESA"
      },
      "quantidade": 1,
      "valorUnitario": 28.96,
      "valorTotal": 28.96
    }
  ],
  "valorTotal": 28.96,
  "status": "PENDENTE",
  "pago": false,
  "dataCadastro": "",
  "dataAlteracao": ""
```
 
- se trocar `POST` por `PUT` é possivel realizar alteraçoes no pedido, incluindo o status do mesmo.
- Para testar 
    - swagger-ui/index.html#/pedido-api/updatePedido
    - Ou um `PUT` para `/api/pedido/{id}`
    - passe como parametro o iddo pedido que deseja atualizar.
    - Envie o json com as alterções no corpo.
```
{
  {
  "id": 1,
  "idCliente": 1,
  "combos": [
    {
      "id": 1,
      "lanche": {
        "id": 1,
        "nome": "Hambúrguer",
        "descricao": "Delicioso hambúrguer artesanal",
        "preco": 10.99,
        "categoria": "LANCHE"
      },
      "acompanhamento": {
        "id": 6,
        "nome": "Batata Frita",
        "descricao": "Porção de batatas fritas crocantes",
        "preco": 5.99,
        "categoria": "ACOMPANHAMENTO"
      },
      "bebida": {
        "id": 11,
        "nome": "Refrigerante",
        "descricao": "Produto gaseificada sabor cola",
        "preco": 4.99,
        "categoria": "BEBIDA"
      },
      "sobremesa": {
        "id": 16,
        "nome": "Bolo de Chocolate",
        "descricao": "Delicioso bolo de chocolate",
        "preco": 7.99,
        "categoria": "SOBREMESA"
      },
      "quantidade": 1,
      "valorUnitario": 23.98,
      "valorTotal": 23.98
    }
  ],
  "valorTotal": 23.98,
  "status": "PENDENTE",
  "pago": true,
  "dataCadastro": "2023-09-01T13:00:00.000+00:00",
  "dataAlteracao": null
}
```
### Consultar status de pagamento do pedido, e integrações com instituiçoes de pagamento.

Não conseguimos relizar a integração com mercado pago em tempo para a entrega devido a dificudades para compreender sua documentação. 
Como alternativa optamos por criar uma integração com Efí Bank porem o resultado ficou instavel e nem sempre os webhooks respondem de maneira correta.

#### Para consulta de status de pagamento 
- Use swagger-ui/index.html#/api/pagamento/verifica-pagamento/{id}
- Ou `GET` para `/api/pagamento/verifica-pagamento/`
- Envie o id do pedido que deseja consultar o status de pagamento como parâmetro.

#### Para gerar o codigo QRCode(Copie e cole)
- Use /swagger-ui/index.html#/pagamento-api/geraQrCodePedido
- Ou `GET` para `/api/pagamento/gera-qrcode{id}`
- Envie o id do pedido que deseja gerar pagamento.

#### Para listar pedidos pagos
- Use swagger-ui/index.html#/pagamento-api/getPedidosByStatusPagamento passando `true` para lista pedidos pagos e `false` para pedidos pendentes.
- Ou um `GET` para `/api/pagamento/status-pagamento?pago=false` retorna pedidos pendentes.
- Ou um `GET` para `/api/pagamento/status-pagamento?pago=true` retorna pedidos pagos.

#### Listagem de pedidos ativos
- Use /swagger-ui/index.html#/pedido-api/getPedidosAtivos
- Ou um `GET` para `/api/pedido/ativos`
- O retorno sera um json com lista de pedidos ordenados por recebimento e por status com a seguinte prioridade: Pronto > Em Preparação > Recebido
- Pedidos com status Finalizado não aparecem na lista.


## Documentação do sistema (DDD) utilizando a linguagem ubíqua.

### Domínios
- Subdomínio Principal:
    - Comida
- Subdomínio Genérico:
    - Lógica de pagamento integrada ao mercado pago.
- Subdomínio Suporte:
    - Gestão de estoque,
    - funcionários, clientes,
    - estratégias de marketing


### Contextos delimitados

- Pedido (Realização do pedido e pagamento) 
  ![image](https://github.com/PedroVCorsino/Tech-Challenge/assets/61948860/0c627219-8fb8-4bdc-b88a-3d0db6087973)

- Cozinha (Preparação e entrega do pedido)
  ![image](https://github.com/PedroVCorsino/Tech-Challenge/assets/61948860/823b0576-5524-4397-9411-6805505dfb85)

### Dicionário de linguagem ubíqua
- Identificação: Pode se identificar usando CPF, nome, e-mail ou não se identificar.
- Produto: Quaisquer itens vendidos pela lanchonete, divididos nas categorias Lanche, Acompanhamento, Bebida e Sobremesa.
- Combo: É uma oferta especial que combina um lanche, um acompanhamento, uma bebida e uma sobremesa por um preço promocional.
- Lanche: Refere-se ao item principal do cardápio, geralmente um sanduíche ou hambúrguer, ou uma opção de refeição vegana.
 - Acompanhamento: É uma opção adicional que pode ser escolhida juntamente com o lanche. Pode incluir batatas fritas, nuggets, onion rings, salada, ou outras opções de acompanhamentos.
- Bebida: São as opções líquidas disponíveis para serem consumidas junto com o lanche. Isso pode incluir refrigerantes, sucos, água, chás gelados, milkshakes, entre outros.
- Sobremesa: Refere-se a um item do cardápio que é servido após a refeição principal. Pode incluir opções como sorvetes, tortas, bolos, milkshakes especiais ou outras delícias doces.
- Categoria: Ou tipo, se refere a qual tipo de produto entre as opções lanche, acompanhamento, bebida e sobremesa.
---
  

## Autores
- [Diego Amorim](https://github.com/dieg0amorim)
- [Gabriela Ribeiro](https://github.com/gabsribeiro)
- [Luzivan Gois](https://github.com/luzivanmgois)
- [Pedro Vinicius](https://github.com/PedroVCorsino)
