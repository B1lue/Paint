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

        this.cor = getColorFromTokenizer(quebrador);

        this.texto = quebrador.nextToken();

        if (quebrador.hasMoreTokens()) {
            this.fontName = quebrador.nextToken();
        } else {
            this.fontName = "Arial"; // Valor padrão
        }

        if (quebrador.hasMoreTokens()) {
            this.fontSize = Integer.parseInt(quebrador.nextToken());
        } else {
            this.fontSize = 12; // Valor padrão
        }

        if (quebrador.hasMoreTokens()) {
            this.fontStyle = Integer.parseInt(quebrador.nextToken());
        } else {
            this.fontStyle = Font.PLAIN; // Valor padrão
        }

        this.ponto = new Ponto(x, y, this.cor);
    }

    private Color getColorFromTokenizer(StringTokenizer quebrador) {
        int r = Integer.parseInt(quebrador.nextToken());
        int g = Integer.parseInt(quebrador.nextToken());
        int b = Integer.parseInt(quebrador.nextToken());
        return new Color(r, g, b);
    }

    @Override
    public void torneSeVisivel(Graphics g) {
        if (texto != null) {
            g.setColor(this.cor);
            g.setFont(new Font(this.fontName, this.fontStyle, this.fontSize));
            String[] linhas = this.texto.split("\n");
            for (int i = 0; i < linhas.length; i++) {
                g.drawString(linhas[i], this.ponto.getX(), this.ponto.getY() + i * g.getFontMetrics().getHeight());
            }
        }
    }

    public String toString() {
        return "t:" +
                this.ponto.getX() +
                ":" +
                this.ponto.getY() +
                ":" +
                getColorString(this.cor) +
                ":" +
                this.texto +
                ":" +
                this.fontName +
                ":" +
                this.fontSize +
                ":" +
                this.fontStyle;
    }

    private String getColorString(Color color) {
        return color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
    }
}