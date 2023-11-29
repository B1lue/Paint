package Figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Quadrado extends Figura {
    protected Ponto p1;
    protected int lado;
    protected int direcao;
    protected Color corDePreenchimento;
    public Quadrado(int x, int y, int lado, int direcao, Color cor) {
        super(cor);
        this.corDePreenchimento = corDePreenchimento;
        this.p1 = new Ponto(x, y, cor);
        this.lado = lado;
        this.direcao = direcao;
    }

    public Quadrado(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        //int x1 = Integer.parseInt(quebrador.nextToken());
        //int y1 = Integer.parseInt(quebrador.nextToken());

        // int lado = Integer.parseInt(quebrador.nextToken());

        this.cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken()));

    }

    public Ponto getP1() {
        return this.p1;
    }

    @Override
    public void torneSeVisivel(Graphics g) {
        g.setColor(this.corDePreenchimento);
        g.fillRect(this.p1.getX(), this.p1.getY(), this.lado, this.lado);
        g.setColor(this.cor);
        g.drawRect(this.p1.getX(), this.p1.getY(), this.lado, this.lado);
    }

    public String toString() {
        return "q:" +
                this.p1.getX() +
                ":" +
                this.p1.getY() +
                ":" +
                this.lado +
                ":" +
                this.cor.getRed() +
                ":" +
                this.cor.getGreen() +
                ":" +
                this.cor.getBlue();
    }
}