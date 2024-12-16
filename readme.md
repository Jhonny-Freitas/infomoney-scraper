
# 📊 InfoMoney Scraper - Desafio Técnico

## 📌 Sobre o Desafio

Este projeto é a solução para o desafio técnico proposto no processo seletivo da Knewin. O objetivo principal foi criar um **scraper em Java** que coleta notícias da seção **Mercados** do site [InfoMoney](https://www.infomoney.com.br/mercados/), atendendo aos seguintes requisitos:

- Extrair notícias das **3 primeiras páginas** da seção **Mercados**, carregadas ao clicar em "Carregar Mais".
- Obter as seguintes informações de cada notícia:
    - **URL**
    - **Título**
    - **Subtítulo**
    - **Autor**
    - **Data de publicação** (formato: `dia/mês/ano hora:minuto`)
    - **Conteúdo**, limpo de tags HTML e sem quebras de linha.
- Fazer **o mínimo de requisições possível** ao site.
- Utilizar a biblioteca **Jsoup** para o parsing de HTML.

🎉 Além disso, o projeto conta com uma funcionalidade adicional: a opção de coletar **todas as notícias disponíveis** na categoria **Mercados**, indo além das 3 páginas solicitadas!

---

## 🛠️ Como Rodar o Projeto

### 1️⃣ Pré-requisitos

Certifique-se de ter configurado na sua máquina:

- **Java 11+**
- **Maven**

### 2️⃣ Clonar o repositório

```bash
git clone git@github.com:Jhonny-Freitas/infomoney-scraper.git
cd infomoney-scraper
```

### 3️⃣ Instalar dependências

Instale as dependências com o Maven:

```bash
mvn install
```

### 4️⃣ Executar o scraper

Inicie o scraper com o comando:

```bash
mvn exec:java
```

O programa realizará o scraping das notícias e exibirá os resultados diretamente no console.

---

## 🗂️ Estrutura do Projeto

### **Principais Classes**

#### 1. `InfoMoneyScraper`
A classe principal que orquestra todo o processo de scraping, incluindo a extração das notícias e a interação com a API do InfoMoney.

#### 2. `News`
Modelo que representa uma notícia com suas propriedades (URL, título, subtítulo, autor, data de publicação e conteúdo). Inclui métodos para formatar a saída.

#### 3. `ScrapingService`
Realiza as requisições HTTP e utiliza o **Jsoup** para parsear as páginas HTML.

#### 4. `NewsFetcher`
Responsável por coletar notícias da página principal e carregar notícias adicionais ao interagir com a API do InfoMoney (aqui você pode configurar até quantas notícias pesquisar).

#### 5. `NewsParser`
Parseia os elementos HTML para extrair subtítulo, autor, data e conteúdo das notícias.

#### 6. `NewsFormatter`
Limpa o conteúdo das notícias, removendo tags HTML e quebras de linha.

#### 7. `NewsPrinter`
Exibe as notícias no console, formatadas de forma legível.

---

## 🚀 Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Maven**: Gerenciador de dependências.
- **Jsoup**: Biblioteca para scraping e parsing de HTML.
- **Ferramentas de desenvolvedor do navegador**: Para analisar o comportamento da página e mapear as requisições necessárias.

---

## 🌟 Destaques do Projeto

- Atende a todos os requisitos do desafio.
- Adiciona uma funcionalidade extra: **raspagem de todas as notícias disponíveis** na categoria **Mercados**.
- Faz o mínimo de requisições possíveis, otimizando o processo.
- Código modular e organizado, com foco em reusabilidade e legibilidade.

---

## 🙏 Agradecimento

Gostaria de agradecer pela oportunidade de participar deste processo seletivo. Este desafio foi uma excelente oportunidade para aplicar meus conhecimentos em **Java** e explorar novas abordagens para **scraping de dados** e **tratamento de informações**. Estou animado com o aprendizado que tive e espero que o projeto atenda às expectativas.

💙 Muito obrigado pela atenção! 