Funcionalidade: Criação de Produto
  Como um administrador do sistema
  Eu quero cadastrar novos produtos
  Para que eles fiquem disponíveis no cardápio

  Cenario: Criar um novo produto com sucesso
    Dado que tenho os dados de um novo produto válido
    Quando eu enviar uma requisição para criar o produto
    Então o produto deve ser criado com sucesso
    E o status da resposta deve ser 201 Created