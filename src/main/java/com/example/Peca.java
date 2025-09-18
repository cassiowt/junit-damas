package com.example;

/**
 * Representa uma peça do jogo de damas.
 * 
 * Uma peça possui uma cor (branca ou preta) e pode ser promovida a dama
 * quando alcança a extremidade oposta do tabuleiro.
 */
public class Peca {
    public enum Cor { BRANCA, PRETA }
    private Cor cor;
    private boolean dama;

    public Peca(Cor cor) {
        this.cor = cor;
        this.dama = false;
    }

    public Cor getCor() { return cor; }
    public boolean isDama() { return dama; }
    public void promover() { this.dama = true; }
}
