package com.example;

public class Jogo {
    static Tabuleiro tabuleiro = new Tabuleiro();

    public static void main(String[] args) {

        iniciar();
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

       // Movimento simples: branca avança
        tabuleiro.moverPeca(5, 2, 4, 3);
        System.out.println("Após mover branca (5,2) para (4,3):");
        System.out.println(tabuleiro.toStringTabuleiro());

        // Movimento de captura: preta captura branca
        tabuleiro.moverPeca(3, 0, 5, 2);
        System.out.println("Após preta capturar branca (3,0) para (5,2):");
        System.out.println(tabuleiro.toStringTabuleiro());
    }

    public static void iniciar() {
        // Coloca peças pretas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) { // apenas casas escuras
                    tabuleiro.colocarPeca(i, j, new Peca(Peca.Cor.PRETA));
                }
            }
        }
        // Coloca peças brancas
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    tabuleiro.colocarPeca(i, j, new Peca(Peca.Cor.BRANCA));
                }
            }
        }
    }
}
