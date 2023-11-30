package Figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Elipse extends Figura {
    protected Ponto centro;
    protected Color corPreenchimento;
    protected int raio1, raio2;

    public Elipse(int x, int y, int r1, int r2, Color cor,Color corPreenchimento) {
        super(cor);
        this.corPreenchimento = corPreenchimento;
        this.centro = new Ponto(x, y);
        this.raio1 = r1;
        this.raio2 = r2;
    }

    public Elipse(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int[] valores = new int[4];
        int i = 0;

        while (quebrador.hasMoreTokens() && i < valores.length) {
            valores[i] = Integer.parseInt(quebrador.nextToken());
            i++;
        }

        Color cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto(valores[0], valores[1], cor);
        this.raio1 = valores[2];
        this.raio2 = valores[3];
        this.cor = cor;
    }

    public Ponto getCentro() {
        return this.centro;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.corPreenchimento);
        g.fillOval(this.centro.getX() - raio1, this.centro.getY() - raio2, 2 * raio1, 2 * raio2);
        g.setColor(this.cor);
        g.drawOval(this.centro.getX() - raio1, this.centro.getY() - raio2, 2 * raio1, 2 * raio2);

    }

    public String toString() {
        return "e:" +
                this.centro.getX() +
                ":" +
                this.centro.getY() +
                ":" +
                this.raio1 +
                ":" +
                this.raio2 +
                ":" +
                this.cor.getRed() +
                ":" +
                this.cor.getGreen() +
                ":" +
                this.cor.getBlue() +
                ":";
    }
}