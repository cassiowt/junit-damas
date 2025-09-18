package com.example;

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
