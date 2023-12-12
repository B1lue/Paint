package Figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Circulo extends Figura {
    protected Ponto centro;
    protected int raio;
    protected Color corDePreenchimento;

    public Circulo(int x, int y, int raio, Color cor, Color corDePreenchimento) {
        super(cor);
        this.corDePreenchimento = corDePreenchimento;
        this.centro = new Ponto(x, y, cor);
        this.raio = raio;
    }

    public Circulo(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());
        this.raio = Integer.parseInt(quebrador.nextToken());

        this.cor = getColorFromTokenizer(quebrador);
        this.corDePreenchimento = getColorFromTokenizer(quebrador);
        this.centro = new Ponto(x, y, this.cor);
    }

    private Color getColorFromTokenizer(StringTokenizer quebrador) {
        int r = Integer.parseInt(quebrador.nextToken());
        int g = Integer.parseInt(quebrador.nextToken());
        int b = Integer.parseInt(quebrador.nextToken());
        return new Color(r, g, b);
    }

    public Ponto getCentro() {
        return this.centro;
    }

    @Override
    public void torneSeVisivel(Graphics g) {
        g.setColor(this.corDePreenchimento);
        g.fillOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);
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
                getColorString(this.cor) +
                ":" +
                getColorString(this.corDePreenchimento);
    }

    private String getColorString(Color color) {
        return color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
    }
}