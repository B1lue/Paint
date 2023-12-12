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

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());
        this.largura = Integer.parseInt(quebrador.nextToken());
        this.altura = Integer.parseInt(quebrador.nextToken());

        this.cor = getColorFromTokenizer(quebrador);
        this.corDePreenchimento = getColorFromTokenizer(quebrador);
        this.p1 = new Ponto(x, y, this.cor);
    }

    private Color getColorFromTokenizer(StringTokenizer quebrador) {
        int r = Integer.parseInt(quebrador.nextToken());
        int g = Integer.parseInt(quebrador.nextToken());
        int b = Integer.parseInt(quebrador.nextToken());
        return new Color(r, g, b);
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
                getColorString(this.cor) +
                ":" +
                getColorString(this.corDePreenchimento);
    }

    private String getColorString(Color color) {
        return color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
    }
}