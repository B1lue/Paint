package Figuras;

import java.awt.*;
import java.util.StringTokenizer;


public class Circulo extends Figura
{
    protected Ponto centro;
    protected int raio;

    public Circulo(int x, int y, int r, Color cor) {
        super(cor);

        this.centro = new Ponto(x, y);
        this.raio = r;

        System.out.println(this.centro.getX() + this.centro.getY() + this.raio);
    }

    public Circulo(String s) {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int x = 0;
        int y = 0;
        int raio = 0;

        if (quebrador.hasMoreTokens()) {
            x = Integer.parseInt(quebrador.nextToken());
        }

        if (quebrador.hasMoreTokens()) {
            y = Integer.parseInt(quebrador.nextToken());
        }

        if (quebrador.hasMoreTokens()) {
            raio = Integer.parseInt(quebrador.nextToken());
        }

        this.centro = new Ponto(x, y, Color.BLACK);
        this.raio = raio;
    }

    public Ponto getCentro() {
        return this.centro;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);

    }

    public String toString() {
        return "c:" +
                this.centro.getX() +
                ":" +
                this.centro.getY() +
                ":" +
                this.raio +
                ":" +
                this.cor.getRed() +
                ":" +
                this.cor.getGreen() +
                ":" +
                this.cor.getBlue();
    }

}