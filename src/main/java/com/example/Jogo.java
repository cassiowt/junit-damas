package com.example;

public class Jogo {
    static Peca.Cor vez = Peca.Cor.BRANCA;
    static Tabuleiro tabuleiro = new Tabuleiro();

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        iniciar();
        System.out.println("Tabuleiro inicial:");
        System.out.println(tabuleiro.toStringTabuleiro());

        while (true) {
            System.out.println("Vez das peças " + (vez == Peca.Cor.BRANCA ? "BRANCAS" : "PRETAS"));
            System.out.println("Digite as coordenadas da peça que deseja mover (linha coluna):");
            int origemLinha = scanner.nextInt();
            int origemColuna = scanner.nextInt();

            Peca pecaSelecionada = tabuleiro.getCasa(origemLinha, origemColuna);
            if (pecaSelecionada == null || pecaSelecionada.getCor() != vez) {
                System.out.println("Selecione uma peça da cor correta!");
                continue;
            }

            System.out.println("Digite as coordenadas de destino (linha coluna):");
            int destinoLinha = scanner.nextInt();
            int destinoColuna = scanner.nextInt();

            try {
                tabuleiro.moverPeca(origemLinha, origemColuna, destinoLinha, destinoColuna);
                System.out.println("Movimento realizado!");
                vez = (vez == Peca.Cor.BRANCA) ? Peca.Cor.PRETA : Peca.Cor.BRANCA;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.");
            }
            System.out.println(tabuleiro.toStringTabuleiro());

            System.out.println("Deseja continuar jogando? (s/n)");
            String continuar = scanner.next();
            if (!continuar.equalsIgnoreCase("s")) {
                break;
            }
        }
        scanner.close();
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
