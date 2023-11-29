package Figuras;

import java.awt.*;
import java.util.StringTokenizer;
public class Linha extends Figura {
    protected Ponto p1, p2;

    public Linha(int x1, int y1, int x2, int y2, Color cor) {
        super(cor);

        this.p1 = new Ponto(x1, y1, cor);
        this.p2 = new Ponto(x2, y2, cor);
    }

    public Linha(String s) {
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

        this.p1 = new Ponto(valores[0], valores[1], cor);
        this.p2 = new Ponto(valores[2], valores[3], cor);
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "l:" +
                this.p1.getX() +
                ":" +
                this.p1.getY() +
                ":" +
                this.p2.getX() +
                ":" +
                this.p2.getY() +
                ":" +
                this.cor.getRed() +
                ":" +
                this.cor.getGreen() +
                ":" +
                this.cor.getBlue();
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawLine(this.p1.getX(), this.p1.getY(),   // ponto inicial
                this.p2.getX(), this.p2.getY());  // ponto final
    }

}