package com.example;

public class Jogo {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();

        // Coloca peças brancas
        tabuleiro.colocarPeca(5, 0, new Peca(Peca.Cor.BRANCA));
        tabuleiro.colocarPeca(5, 2, new Peca(Peca.Cor.BRANCA));
        tabuleiro.colocarPeca(5, 4, new Peca(Peca.Cor.BRANCA));

        // Coloca peças pretas
        tabuleiro.colocarPeca(2, 1, new Peca(Peca.Cor.PRETA));
        tabuleiro.colocarPeca(2, 3, new Peca(Peca.Cor.PRETA));
        tabuleiro.colocarPeca(2, 5, new Peca(Peca.Cor.PRETA));

        System.out.println("Tabuleiro inicial:");
        System.out.println(tabuleiro.toStringTabuleiro());

        // Movimento simples: branca avança
        tabuleiro.moverPeca(5, 0, 4, 1);
        System.out.println("Após mover branca (5,0) para (4,1):");
        System.out.println(tabuleiro.toStringTabuleiro());

        // Movimento simples: preta avança
        tabuleiro.moverPeca(2, 1, 3, 0);
        System.out.println("Após mover preta (2,1) para (3,0):");
        System.out.println(tabuleiro.toStringTabuleiro());

    // Adiciona peça preta na posição intermediária para permitir captura
    tabuleiro.colocarPeca(3, 2, new Peca(Peca.Cor.PRETA));

    // Movimento de captura: branca captura preta
    tabuleiro.moverPeca(4, 1, 2, 3);
    System.out.println("Após branca capturar preta (4,1) para (2,3):");
    System.out.println(tabuleiro.toStringTabuleiro());
    }
}
