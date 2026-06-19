# ArtGallery

Sistema de gerenciamento de obras de arte digitais, desenvolvido como Trabalho Final
da disciplina Técnicas de Programação I — UFC, 2026/1.

## Descrição

O ArtGallery é uma plataforma de curadoria e exposição de obras de arte digitais. Permite o
cadastro de diferentes tipos de obras, avaliações de usuários, organização de exposições
temáticas e consultas diversas, com uma interface gráfica em Java Swing.

## Funcionalidades

- Cadastro de obras (Pintura Digital, Modelagem 3D, Arte Generativa)
- Ativação e desativação de obras
- Avaliação de obras por usuários (nota de 0 a 10)
- Criação de exposições e associação de obras
- Consulta das obras mais bem avaliadas (ranking)
- Busca de obras por autor
- Persistência automática em arquivo (serialização Java)

## Conceitos de Orientação a Objetos aplicados

- **Herança**: `PinturaDigital`, `Modelagem3D` e `ArteGenerativa` herdam de `Obra`
- **Classe abstrata**: `Obra` define o contrato comum, incluindo o método abstrato `exibirDetalhes()`
- **Polimorfismo e late binding**: `Exposicao` e o repositório armazenam `Obra`, mas cada
  subtipo executa sua própria versão de `exibirDetalhes()` em tempo de execução
- **Interfaces**: `IRepositorioObra` e `IArtGallery` desacoplam contrato de implementação
- **Encapsulamento**: todos os atributos são privados, com acesso via getters
- **Exceções customizadas**: `NotaInvalidaException`, `ObraJaCadastradaException`,
  `ObraNaoEncontradaException`
- **Coleções Java**: uso de `Vector` para armazenar obras, avaliações e exposições

## Estrutura de pacotes
artgallery/
├── exception/   → exceções customizadas
├── model/       → Obra, PinturaDigital, Modelagem3D, ArteGenerativa, Avaliacao, Exposicao
├── repository/  → IRepositorioObra e implementação com persistência
├── service/     → IArtGallery e implementação com as regras de negócio
└── view/        → interface gráfica em Java Swing

## Persistência

O sistema salva automaticamente o estado das obras (`obras.dat`) e das exposições
(`exposicoes.dat`) usando serialização Java nativa (`ObjectOutputStream`/`ObjectInputStream`).
Os dados são recarregados automaticamente ao iniciar o programa.

## Como executar
1. Importe o projeto no Eclipse (ou outra IDE Java)
2. Execute a classe `artgallery.view.Main`
3. A janela principal será aberta com acesso a todas as funcionalidades
