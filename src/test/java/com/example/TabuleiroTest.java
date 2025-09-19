/**
 * Classe de teste para a classe Tabuleiro.
 * Contém testes unitários para verificar a funcionalidade e comportamento do tabuleiro do jogo,
 * incluindo posicionamento de peças, validação de movimento e gerenciamento do estado do jogo.
 * 
 * Esta suíte de testes utiliza o framework JUnit para garantir que a classe Tabuleiro
 * opere corretamente de acordo com as regras e especificações do jogo.
 * 
 * @author Testes gerados para a classe Tabuleiro
 * @version 1.0
 * @since 1.0
 */
package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TabuleiroTest {

    /**
     * Testa se uma peça válida pode ser movida corretamente no tabuleiro.
     * 
     * Verifica se:
     * - A peça é removida da posição original (5,1)
     * - A peça é colocada na nova posição (4,2)
     * - A referência da peça permanece a mesma após o movimento
     */
    @Test
    void deveMoverPecaValida() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca p = new Peca(Peca.Cor.BRANCA);
        tabuleiro.colocarPeca(5, 1, p);
        tabuleiro.moverPeca(5, 1, 4, 2);
        assertNull(tabuleiro.getCasa(5, 1));
        assertEquals(p, tabuleiro.getCasa(4, 2));
    }

    /**
     * Verifica que não é permitido mover uma peça para uma casa que já está ocupada.
     * 
     * Este teste coloca duas peças no tabuleiro (uma branca e uma preta) em posições
     * diferentes e tenta mover a peça branca para a posição ocupada pela peça preta.
     * Deve lançar uma IllegalArgumentException com a mensagem "Destino ocupado".
     * 
     * @throws IllegalArgumentException quando tentativa de mover peça para casa ocupada
     */
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

    /**
     * Testa se uma peça pode capturar uma peça adversária durante um movimento.
     * 
     * Este teste verifica que quando uma peça branca se move da posição (5,1) para (3,3),
     * ela captura com sucesso uma peça preta localizada em (4,2) em seu caminho. Após o
     * movimento, tanto a posição original quanto a posição da peça capturada devem estar
     * vazias, enquanto o destino deve conter a peça que fez a captura.
     * 
     * Comportamento esperado:
     * - Posição original (5,1) fica vazia
     * - Posição da peça capturada (4,2) fica vazia
     * - Posição de destino (3,3) contém a peça branca que fez a captura
     */
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

    /**
     * Testa se uma peça branca é promovida para dama quando alcança a última linha do tabuleiro.
     * Verifica se ao mover uma peça branca da posição (1,2) para a posição (0,3), 
     * que representa a linha final para peças brancas, a peça é automaticamente 
     * promovida para dama através do método isDama().
     */
    @Test
    void devePromoverPecaParaDama() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca branca = new Peca(Peca.Cor.BRANCA);
        tabuleiro.colocarPeca(1, 2, branca);
        tabuleiro.moverPeca(1, 2, 0, 3);
        assertTrue(branca.isDama());
    }

    /**
     * Testa se o tabuleiro rejeita corretamente um movimento inválido.
     * 
     * Este teste verifica se uma exceção IllegalArgumentException é lançada
     * quando se tenta realizar um movimento que não é permitido pelas regras
     * do jogo de damas. O teste coloca uma peça branca na posição (5,1) e
     * tenta movê-la para a posição (2,5), que representa um movimento inválido.
     * 
     * @throws IllegalArgumentException quando o movimento não é permitido
     */
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
