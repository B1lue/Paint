package Figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Retangulo extends Figura {
    protected Ponto p1;
    protected int largura;
    protected int altura;
    protected Color corDePreenchimento;

    public Retangulo(int x, int y, int largura, int altura, Color cor, Color corDePreenchimento) {
        super(cor);
        this.corDePreenchimento = corDePreenchimento;
        this.p1 = new Ponto(x, y, cor);
        this.largura = largura;
        this.altura = altura;
    }

    public Retangulo(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();


        this.cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B
    }

    public Ponto getP1() {
        return this.p1;
    }

    @Override
    public void torneSeVisivel(Graphics g) {
        g.setColor(this.corDePreenchimento);
        g.fillRect(this.p1.getX(), this.p1.getY(), this.largura, this.altura);
        g.setColor(this.cor);
        g.drawRect(this.p1.getX(), this.p1.getY(), this.largura, this.altura);
    }

    public String toString() {
        return "r:" +
                this.p1.getX() +
                ":" +
                this.p1.getY() +
                ":" +
                this.largura +
                ":" +
                this.altura +
                ":" +
                this.cor.getRed() +
                ":" +
                this.cor.getGreen() +
                ":" +
                this.cor.getBlue();
    }
}