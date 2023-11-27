package Figuras;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

public class Texto extends Figura {
    private String texto;
    private Ponto ponto;

    public Texto(int x, int y, String texto, Color cor) {
        super(cor);
        this.texto = texto;
        this.ponto = new Ponto(x, y, cor);
    }

    public Texto(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");
        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());
        Color cor = new Color(Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()));

        this.texto = quebrador.nextToken();
        this.ponto = new Ponto(x, y, cor);
        this.cor = cor;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setPonto(int x, int y) {
        this.ponto = new Ponto(x, y, this.getCor());
    }

    public String getTexto() {
        return this.texto;
    }

    public Ponto getPonto() {
        return this.ponto;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(this.texto, this.ponto.getX(), this.ponto.getY());
    }

    public String toString() {
        return "t:" +
                this.ponto.getX() +
                ":" +
                this.ponto.getY() +
                ":" +
                this.texto +
                ":" +
                this.getCor().getRed() +
                ":" +
                this.getCor().getGreen() +
                ":" +
                this.getCor().getBlue();
    }
}
