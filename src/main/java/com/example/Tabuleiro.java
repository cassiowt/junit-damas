package com.example;

public class Tabuleiro {
    private Peca[][] casas = new Peca[8][8];

    public Peca getCasa(int linha, int coluna) {
        return casas[linha][coluna];
    }

    public void colocarPeca(int linha, int coluna, Peca peca) {
        casas[linha][coluna] = peca;
    }

    public void moverPeca(int origemLinha, int origemColuna, int destinoLinha, int destinoColuna) {
        Peca peca = casas[origemLinha][origemColuna];
        if (peca == null) throw new IllegalArgumentException("Não há peça na posição de origem!");

        if (Math.abs(destinoLinha - origemLinha) == 1 && Math.abs(destinoColuna - origemColuna) == 1) {
            if (casas[destinoLinha][destinoColuna] == null) {
                casas[destinoLinha][destinoColuna] = peca;
                casas[origemLinha][origemColuna] = null;
            } else {
                throw new IllegalArgumentException("Destino ocupado!");
            }
        } else if (Math.abs(destinoLinha - origemLinha) == 2 && Math.abs(destinoColuna - origemColuna) == 2) {
            int meioLinha = (origemLinha + destinoLinha) / 2;
            int meioColuna = (origemColuna + destinoColuna) / 2;
            Peca capturada = casas[meioLinha][meioColuna];
            if (capturada != null && capturada.getCor() != peca.getCor()) {
                casas[destinoLinha][destinoColuna] = peca;
                casas[origemLinha][origemColuna] = null;
                casas[meioLinha][meioColuna] = null;
            } else {
                throw new IllegalArgumentException("Não é possível capturar!");
            }
        } else {
            throw new IllegalArgumentException("Movimento inválido!");
        }

        if (peca.getCor() == Peca.Cor.BRANCA && destinoLinha == 0) {
            peca.promover();
        }
        if (peca.getCor() == Peca.Cor.PRETA && destinoLinha == 7) {
            peca.promover();
        }
    }
}
