package Figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Quadrado extends Figura {
    protected Ponto p1;
    protected int lado;
    protected Color corDePreenchimento;
    protected int direcao; //

    public Quadrado(int x, int y, int lado,int direcao, Color cor, Color corDePreenchimento) {
        super(cor);
        this.corDePreenchimento = corDePreenchimento;
        this.p1 = new Ponto(x, y, cor);
        this.lado = lado;
        this.direcao = direcao;  // nao serve pra nda(por enquanto )
    }

    public Quadrado(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());
        this.lado = Integer.parseInt(quebrador.nextToken());

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
                getColorString(this.cor) +
                ":" +
                getColorString(this.corDePreenchimento);
    }

    private String getColorString(Color color) {
        return color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
    }
}