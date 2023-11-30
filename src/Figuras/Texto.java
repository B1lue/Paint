package Figuras;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

public class Texto extends Figura {
    private final String texto;
    private final Ponto ponto;
    private final String fontName;
    private final int fontSize;
    private final int fontStyle;

    public Texto(int x, int y, String texto, Color cor, String fontName, int fontSize, int fontStyle) {
        super(cor);
        this.texto = texto;
        this.ponto = new Ponto(x, y, cor);
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
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
        this.fontName = quebrador.nextToken();
        this.fontSize = Integer.parseInt(quebrador.nextToken());
        this.fontStyle = Integer.parseInt(quebrador.nextToken());
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        g.setFont(new Font(this.fontName, this.fontStyle, this.fontSize));
        g.drawString(this.texto, this.ponto.getX(), this.ponto.getY());
    }

    public String toString() {
        return "t:" +
                this.ponto.getX() +
                ":" +
                this.ponto.getY() +
                ":" +
                this.cor.getRed() +
                ":" +
                this.cor.getGreen() +
                ":" +
                this.cor.getBlue() +
                ":" +
                this.texto +
                ":" +
                this.fontName +
                ":" +
                this.fontSize +
                ":" +
                this.fontStyle;
    }
}