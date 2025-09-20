package com.example;

/**
 * Representa um tabuleiro de damas 8x8.
 * 
 * Esta classe gerencia o estado do tabuleiro de jogo, incluindo o posicionamento
 * das peças, movimentação e captura de peças adversárias. O tabuleiro segue as
 * regras tradicionais do jogo de damas.
 * 
 * O tabuleiro é representado por uma matriz 8x8 onde cada posição pode conter
 * uma peça ou estar vazia (null).
 * 
 * @author Sistema de Damas
 * @version 1.0
 */
public class Tabuleiro {
    private Peca[][] casas = new Peca[8][8];
    private java.util.List<Peca> capturadasBrancas = new java.util.ArrayList<>();
    private java.util.List<Peca> capturadasPretas = new java.util.ArrayList<>();

    public Peca getCasa(int linha, int coluna) {
        return casas[linha][coluna];
    }

    public void colocarPeca(int linha, int coluna, Peca peca) {
        casas[linha][coluna] = peca;
    }

    /**
     * Move uma peça no tabuleiro de damas de uma posição de origem para uma posição de destino.
     * 
     * O método executa as seguintes etapas:
     * 1. Verifica se existe uma peça na posição de origem
     * 2. Valida o tipo de movimento:
     *    - Movimento simples (diagonal de 1 casa): move a peça se o destino estiver livre
     *    - Movimento de captura (diagonal de 2 casas): captura a peça adversária no meio do caminho
     * 3. Executa o movimento válido, atualizando as posições no tabuleiro
     * 4. Remove a peça capturada (se aplicável)
     * 5. Verifica se a peça deve ser promovida ao atingir a extremidade oposta do tabuleiro:
     *    - Peças brancas são promovidas ao chegar na linha 0
     *    - Peças pretas são promovidas ao chegar na linha 7
     * 
     * @param origemLinha linha da posição de origem (0-7)
     * @param origemColuna coluna da posição de origem (0-7)
     * @param destinoLinha linha da posição de destino (0-7)
     * @param destinoColuna coluna da posição de destino (0-7)
     * @throws IllegalArgumentException se não houver peça na origem, se o destino estiver ocupado,
     *                                  se não for possível capturar uma peça ou se o movimento for inválido
     */
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
                // Adiciona peça capturada à lista
                if (capturada.getCor() == Peca.Cor.BRANCA) {
                    capturadasBrancas.add(capturada);
                } else {
                    capturadasPretas.add(capturada);
                }
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
    /**
     * Retorna uma representação gráfica do tabuleiro de damas em texto.
     * Peças brancas: B, peças pretas: P, casas claras vazias: ., casas escuras vazias: #
     */
    public String toStringTabuleiro() {
        StringBuilder sb = new StringBuilder();
        
        // Cabeçalho com números das colunas
        sb.append("  ");
        for (int col = 0; col < 8; col++) {
            sb.append("\u001B[34m").append(col).append("\u001B[0m ");
        }
        sb.append("\n");

        for (int linha = 0; linha < 8; linha++) {
            // Número da linha no início
            sb.append("\u001B[34m").append(linha).append("\u001B[0m ");

            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = casas[linha][coluna];
                if (peca == null) {
                    // Casa escura quando linha + coluna é ímpar
                    if ((linha + coluna) % 2 == 1) {
                        sb.append(".");
                    } else {
                        sb.append("_");
                    }
                } else if (peca.getCor() == Peca.Cor.BRANCA) {
                    sb.append("b");
                } else {
                    sb.append("\u001B[31mp\u001B[0m"); // p em vermelho
                }
                sb.append(" ");
            }
            // Exibe peças capturadas ao lado do tabuleiro
            if (linha == 1) {
                sb.append("  Capturadas brancas: ");
                for (Peca p : capturadasBrancas) sb.append("b ");
            }
            if (linha == 2) {
                sb.append("  Capturadas pretas: ");
                for (Peca p : capturadasPretas) sb.append("\u001B[31mp\u001B[0m ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
