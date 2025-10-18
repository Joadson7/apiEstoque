🏗️ Sistema de Controle de Estoque - Obras Públicas
Sistema completo para controle de entrada e saída de materiais em obras públicas, desenvolvido em Angular com interface moderna e responsiva.

📋 Sobre o Projeto
Sistema desenvolvido para gerenciar o estoque de materiais utilizados em obras públicas, permitindo o controle preciso de entradas (compras) e saídas (uso em obras) com relatórios detalhados e filtros avançados.

✨ Funcionalidades
🏷️ Cadastro de Produtos
Registro de Produtos: Cadastro completo de novos produtos no sistema

Edição de Produtos: Atualização de informações dos produtos cadastrados

Desativação de Produtos: Controle de produtos inativos (mantém histórico)

Consulta de Produtos: Listagem completa com filtros e busca

Categorização: Organização por categorias e unidades de medida

📦 Módulo de Entradas
Registro de Entradas: Cadastro de novos materiais no estoque

Consulta de Entradas: Histórico completo de todas as entradas

Filtros Avançados: Busca por data, produto e fornecedor

Detalhes Completos: Visualização detalhada de cada entrada

🚚 Módulo de Saídas
Registro de Saídas: Controle de saída de materiais para obras

Consulta de Saídas: Histórico de todas as saídas realizadas

Validação de Estoque: Impede saídas sem estoque disponível

Controle por Localização: Saídas vinculadas a ruas específicas

📊 Funcionalidades Gerais
Dashboard: Visão geral do estoque

Relatórios: Exportação de dados

Gestão de Produtos: Cadastro e edição de produtos

Interface Responsiva: Funciona em desktop e mobile

🛠️ Tecnologias Utilizadas
Frontend: Angular 17+, TypeScript, Bootstrap 5

Backend: Spring Boot, Java

Banco de Dados: PostgreSQL

API: RESTful

Ícones: Bootstrap Icons

🏗️ Estrutura do Projeto
text:
src/
├── app/
│   ├── components/
│   │   ├── produto-cadastrar/
│   │   ├── produto-consultar/
│   │   ├── entrada-cadastrar/
│   │   ├── entrada-consultar/
│   │   ├── saida-cadastrar/
│   │   └── saida-consultar/
│   ├── services/
│   │   └── api.service.ts
│   └── models/
├── assets/
└── environments/

📦 Instalação e Configuração
Pré-requisitos
Node.js 18+

Angular CLI 17+

Java 17+

PostgreSQL

Instalação
Clone o repositório:
git clone https://github.com/Joadson7/apiEstoque.git
cd controle-estoque-obras

Instale as dependências do frontend:
npm install

Configure o ambiente:
# Copie o arquivo de ambiente
cp src/environments/environment.example.ts src/environments/environment.ts

Configure as variáveis de ambiente:
typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};

Execute a aplicação:
ng serve

A aplicação estará disponível em http://localhost:4200

🎯 Como Usar
📝 Cadastrando um Produto
Acesse "Produtos" → "Cadastrar Produto"

Preencha nome, descrição, unidade de medida

Defina categoria e informações adicionais

Clique em "Cadastrar Produto"

📦 Registrando uma Entrada
Acesse "Entradas" → "Registrar Entrada"

Selecione o produto

Informe quantidade, fornecedor e data

Clique em "Registrar Entrada"

🚚 Registrando uma Saída
Acesse "Saídas" → "Registrar Saída"

Selecione um produto com estoque disponível

Informe quantidade, destino e rua da obra

Clique em "Registrar Saída"

Consultando Relatórios
Acesse "Consultar Entradas" ou "Consultar Saídas"

Use os filtros para buscar por período

Visualize os detalhes ou exporte os dados

📊 Enums e Estruturas
Setores de Destino:
ALMOXARIFADO, ESGOTO, HIDRAULICA, POÇO_DE_VISITA, 
CAIXA_RALO, CALÇADA, MANUTENÇÃO, MEIO_FIO, DRENAGEM

Ruas em Obra:
RUA_63, RUA_64, RUA_65, RUA_66, RUA_67, RUA_68

🔧 Desenvolvimento
Estrutura de Desenvolvimento:
# Desenvolvimento com hot-reload
ng serve

# Build para produção
ng build

# Executar testes
ng test

# Lint do código
ng lint

Convenções de Código
Components: PascalCase (ex: ProdutoCadastrarComponent)

Services: PascalCase com sufixo Service (ex: ApiService)

Interfaces: PascalCase (ex: Produto)

Métodos: camelCase (ex: cadastrarProduto())

📱 Telas do Sistema
🏷️ Gestão de Produtos
✅ Cadastro de novos produtos

✅ Edição de produtos existentes

✅ Consulta com filtros

✅ Desativação (exclusão lógica)

📦 Controle de Entradas
✅ Registro de compras/entradas

✅ Histórico completo

✅ Filtros por data e produto

✅ Detalhes das entradas

🚚 Controle de Saídas
✅ Registro para obras

✅ Validação de estoque

✅ Controle por rua e setor

✅ Histórico de saídas

🤝 Contribuição
Fork o projeto

Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)

Commit suas mudanças (git commit -m 'Add some AmazingFeature')

Push para a branch (git push origin feature/AmazingFeature)

Abra um Pull Request

📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para detalhes.

👥 Autores
Joadson Torres - joadsontorres@gmail.com
Contribuidores

Comunidade Angular

Bootstrap team

⭐️ Se este projeto foi útil, deixe uma estrela no repositório!

