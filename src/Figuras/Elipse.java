package Figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Elipse extends Figura {
    protected Ponto centro;
    protected int raio1, raio2;
    protected Color corDePreenchimento;

    public Elipse(int x, int y, int r1, int r2, Color cor, Color corDePreenchimento) {
        super(cor);
        this.corDePreenchimento = corDePreenchimento;
        this.centro = new Ponto(x, y, cor);
        this.raio1 = r1;
        this.raio2 = r2;
    }

    public Elipse(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());
        this.raio1 = Integer.parseInt(quebrador.nextToken());
        this.raio2 = Integer.parseInt(quebrador.nextToken());

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
                getColorString(this.cor) +
                ":" +
                getColorString(this.corDePreenchimento);
    }

    private String getColorString(Color color) {
        return color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
    }
}