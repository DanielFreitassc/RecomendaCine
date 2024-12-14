# **RecomendaCine - Plataforma de Recomendações de Filmes**

O **RecomendaCine** é uma plataforma que sugere um filme por dia, baseado nos gostos do usuário. A ideia é facilitar a escolha de filmes, proporcionando uma experiência personalizada e envolvente.

---

## **Funcionalidades**

### **1. Cadastro e Perfil de Usuário**
- **Cadastro simples**: Permite o usuário criar um perfil, com e-mail e senha.
- **Preferências de gosto**: O usuário pode escolher seus gêneros preferidos (ação, comédia, drama, etc.), atores e diretores favoritos.
- **Avaliação de filmes**: O usuário pode avaliar filmes com "curtir" ou "não curtir", ajudando o algoritmo de recomendação a aprender suas preferências.

### **2. Recomendação Diária**
- **Algoritmo de recomendação**: O sistema utiliza as preferências do usuário, combinadas com avaliações, para sugerir um filme por dia.
- **Detalhes do filme**: A recomendação inclui informações como título, sinopse, trailer, elenco, e onde assistir (Netflix, Amazon Prime, etc.).
- **Botões de ação**: O usuário pode escolher "Salvar para depois", ou "Não gostei" para ajustar futuras recomendações.
 
### **3. Funcionalidade de Feedback**
- **Avaliação**: O usuário pode dar feedback sobre a recomendação com um sistema de "curtir" ou "não curtir".
- **Ajustes no algoritmo**: O sistema ajusta as futuras recomendações com base no feedback, para que a plataforma se torne cada vez mais personalizada.

### **4. Modo Exploração**
- **Surpresas**: Uma funcionalidade onde o sistema recomenda um filme "fora da caixa", baseado em um gênero ou tema diferente do que o usuário normalmente assiste.
- **Filmes novos ou populares**: A plataforma pode sugerir filmes populares ou recém-lançados, estimulando o usuário a explorar novas opções.

---

## **Tecnologias Utilizadas**
### **Infra**
- **Docker**: Para rodar o backend e o banco de dados.

### **Back-End**
- **Spring Boot**: Framework para construção da API.
- **Java**: Linguagem de programação
- **Banco de Dados**: PostgreSQL e H2Database.

### **Front-End**
- **Web**: ....
- **Mobile**: ....
- **Design**: ....

---

## **Fluxo de Uso**

### **1. Abertura do App**
- O usuário faz login ou cria uma conta.  
- O usuário escolhe seus gostos, gêneros e outros interesses.
- O sistema processa e configura as primeiras recomendações com base nas preferências.

### **2. Tela Principal**
- O usuário vê uma lista de sugestões de filmes com base em suas preferências.
- O filme recomendado do dia aparece em destaque, com detalhes e um botão de ação gerar uma nova recomendação.

### **3. Feedback e Ajuste**
- O usuário pode dar feedback, informando se gostou ou não da recomendação.
- O sistema ajusta suas próximas sugestões com base nesse feedback.

---

**RecomendaCine** - O seu guia pessoal para filmes incríveis, um por dia!
---
### **Modelagem do banco de dados**

![image](https://github.com/user-attachments/assets/df8ad70f-c0d6-4d3a-a871-3457380f8cb6)
