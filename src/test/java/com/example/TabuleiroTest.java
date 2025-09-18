package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TabuleiroTest {

    @Test
    void deveMoverPecaValida() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca p = new Peca(Peca.Cor.BRANCA);
        tabuleiro.colocarPeca(5, 1, p);
        tabuleiro.moverPeca(5, 1, 4, 2);
        assertNull(tabuleiro.getCasa(5, 1));
        assertEquals(p, tabuleiro.getCasa(4, 2));
    }

    @Test
    void naoDevePermitirMoverParaCasaOcupada() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca p1 = new Peca(Peca.Cor.BRANCA);
        Peca p2 = new Peca(Peca.Cor.PRETA);
        tabuleiro.colocarPeca(5, 1, p1);
        tabuleiro.colocarPeca(4, 2, p2);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            tabuleiro.moverPeca(5, 1, 4, 2);
        });
        assertTrue(ex.getMessage().contains("Destino ocupado"));
    }

    @Test
    void deveCapturarPecaAdversaria() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca branca = new Peca(Peca.Cor.BRANCA);
        Peca preta = new Peca(Peca.Cor.PRETA);
        tabuleiro.colocarPeca(5, 1, branca);
        tabuleiro.colocarPeca(4, 2, preta);
        tabuleiro.moverPeca(5, 1, 3, 3);
        assertNull(tabuleiro.getCasa(5, 1));
        assertNull(tabuleiro.getCasa(4, 2));
        assertEquals(branca, tabuleiro.getCasa(3, 3));
    }

    @Test
    void devePromoverPecaParaDama() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca branca = new Peca(Peca.Cor.BRANCA);
        tabuleiro.colocarPeca(1, 2, branca);
        tabuleiro.moverPeca(1, 2, 0, 3);
        assertTrue(branca.isDama());
    }

    @Test
    void deveRejeitarMovimentoInvalido() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca branca = new Peca(Peca.Cor.BRANCA);
        tabuleiro.colocarPeca(5, 1, branca);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            tabuleiro.moverPeca(5, 1, 2, 5);
        });
        assertTrue(ex.getMessage().contains("Movimento inválido"));
    }
}
