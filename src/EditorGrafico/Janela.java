package EditorGrafico;


import Figuras.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Objects;
import java.util.Vector;

public class Janela extends JFrame implements MouseListener, MouseMotionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    protected JButton btnPonto = new JButton("Ponto"),
            btnLinha = new JButton("Linha"),
            btnCirculo = new JButton("Circulo"),
            btnElipse = new JButton("Elipse"),
            btnCores = new JButton("Cores"),
            btnAbrir = new JButton("Abrir"),
            btnSalvar = new JButton("Salvar"),
            btnApagar = new JButton("Apagar"),
            btnSair = new JButton("Sair");

    protected MeuJPanel pnlDesenho = new MeuJPanel();

    protected JLabel statusBar1 = new JLabel("Mensagem:"),
            statusBar2 = new JLabel("Coordenada:");

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaCirculo, esperaInicioCirculo, esperaFimCirculo, esperarFimElipse, esperarInicioElipse;

    protected Color corAtual = Color.BLACK;
    protected Ponto p1;
    protected Circulo circulo1;

    protected Elipse elipse1;

    protected static Vector<Figura> figuras = new Vector<Figura>();

    protected JButton btnTexto = new JButton("Texto");
    protected boolean esperaTexto;
    protected String textoDigitado;

    public Janela() {
        super("Editor Gr�fico");


        try {
            Image btnPontoImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/ponto.jpg")));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo ponto.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnLinhaImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/linha.jpg")));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo linha.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCirculoImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/circulo.jpg")));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo circulo.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnElipseImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/elipse.jpg")));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo elipse.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCoresImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/cores.jpg")));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo cores.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnAbrirImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/abrir.jpg")));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo abrir.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSalvarImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/salvar.jpg")));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo salvar.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnApagarImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/apagar.jpg")));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo apagar.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSairImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/sair.jpg")));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo sair.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener(new DesenhoDePonto());
        btnLinha.addActionListener(new DesenhoDeReta());
        btnCirculo.addActionListener(new DesenhoCirculo());
        btnTexto.addActionListener(new DesenhoDeTexto());
        btnElipse.addActionListener(new DesenhoDeElipse());

        JPanel pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout();
        pnlBotoes.setLayout(flwBotoes);

        pnlBotoes.add(btnTexto);
        pnlBotoes.add(btnAbrir);
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnPonto);
        pnlBotoes.add(btnLinha);
        pnlBotoes.add(btnCirculo);
        pnlBotoes.add(btnElipse);
        pnlBotoes.add(btnCores);
        pnlBotoes.add(btnApagar);
        pnlBotoes.add(btnSair);

        JPanel pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1, 2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes, BorderLayout.NORTH);
        cntForm.add(pnlDesenho, BorderLayout.CENTER);
        cntForm.add(pnlStatus, BorderLayout.SOUTH);

        this.addWindowListener(new FechamentoDeJanela());

        this.setSize(700, 500);
        this.setVisible(true);
    }

    protected class DesenhoDeTexto implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaTexto = true;
            statusBar1.setText("Mensagem: clique o local para o texto");
            textoDigitado = JOptionPane.showInputDialog("Digite o texto:");
        }
    }

    protected class MeuJPanel extends JPanel
            implements MouseListener,
            MouseMotionListener {
        public void mouseReleased(MouseEvent e) {
            // Este método é chamado quando um botão do mouse é liberado
            if (esperarFimElipse) {
                figuras.add(new Elipse(elipse1.getCentro().getX(), elipse1.getCentro().getY(),
                        Math.abs(e.getX() - elipse1.getCentro().getX()),
                        Math.abs(e.getY() - elipse1.getCentro().getY()), corAtual));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperarFimElipse = false;
                statusBar1.setText("Mensagem: caralho so pika");
            } else if (esperaFimReta) {
                figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperaFimReta = false;
                statusBar1.setText("Mensagem: se fode ai");
            } else if (esperaFimCirculo) {
                figuras.add(new Circulo(circulo1.getCentro().getX(), circulo1.getCentro().getY(),
                        (int) Math.sqrt(Math.pow(e.getX() - circulo1.getCentro().getX(), 2) + Math.pow(e.getY() - circulo1.getCentro().getY(), 2)), corAtual));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperaFimCirculo = false;
                statusBar1.setText("Mensagem: cara como eu consegui isso ?");
            }
        }

        public MeuJPanel() {
            super();

            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void paint(Graphics g) {
            for (Figura figura : figuras) figura.torneSeVisivel(g);
        }

        public void mousePressed(MouseEvent e) {
            if (esperaPonto) {
                figuras.add(new Ponto(e.getX(), e.getY(), corAtual));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = false;
            } else if (esperaInicioReta) {
                p1 = new Ponto(e.getX(), e.getY(), corAtual);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final da reta");
            } else if (esperaInicioCirculo) {
                circulo1 = new Circulo(e.getX(), e.getY(), 0, corAtual);
                esperaInicioCirculo = false;
                esperaFimCirculo = true;
                statusBar1.setText("Mensagem: clique o ponto final do círculo");
            } else if (esperaTexto) {
                figuras.add(new Texto(e.getX(), e.getY(), textoDigitado, corAtual));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaTexto = false;
                statusBar1.setText("Mensagem:");
            }
            if (esperarInicioElipse) {
                elipse1 = new Elipse(e.getX(), e.getY(), 0, 0, corAtual);
                esperarInicioElipse = false;
                esperarFimElipse = true;
            }
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // Este método torna visivel a figura sendo desenhada
            if (esperaFimReta) {
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual).torneSeVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            }else if(esperaFimCirculo){
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                int raio = (int) Math.sqrt(Math.pow(e.getX() - circulo1.getCentro().getX(), 2) + Math.pow(e.getY() - circulo1.getCentro().getY(), 2));
                new Circulo(circulo1.getCentro().getX(), circulo1.getCentro().getY(), raio, corAtual).torneSeVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            }else if(esperarFimElipse){
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                int semiEixoA = Math.abs(e.getX() - elipse1.getCentro().getX());
                int semiEixoB = Math.abs(e.getY() - elipse1.getCentro().getY());
                new Elipse(elipse1.getCentro().getX(), elipse1.getCentro().getY(), semiEixoA, semiEixoB, corAtual).torneSeVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            }
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordenada: " + e.getX() + "," + e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = true;
            esperaInicioReta = false;
            esperaFimReta = false;

            statusBar1.setText("Mensagem: clique o local do ponto desejado");
        }
    }

    protected class DesenhoDeReta implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoCirculo implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Quando o botão de círculo é clicado, definimos as variáveis de estado apropriadas
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false; // Não é necessário, mas é bom para a clareza
            esperaInicioCirculo = true;

            statusBar1.setText("Mensagem: clique o centro do círculo");
        }
    }

    protected class DesenhoDeElipse implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperarInicioElipse = true;

            statusBar1.setText("Mensagem: clique o centro da elipse");
        }
    }

    protected static class FechamentoDeJanela extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    protected static class SalvarArquivo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int res = fc.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File arquivo = fc.getSelectedFile();
                try {
                    ObjectOutputStream gravador = new ObjectOutputStream(new FileOutputStream(arquivo));
                    gravador.writeObject(figuras);
                    gravador.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao salvar arquivo: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Este método é chamado quando um botão do mouse é liberado
        if (esperaFimReta) {
            figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
            esperaFimReta = false;
            statusBar1.setText("Mensagem: aaaaaaaaaaaaa");
        } else if (esperaFimCirculo) {
            figuras.add(new Circulo(circulo1.getCentro().getX(), circulo1.getCentro().getY(),
                    (int) Math.sqrt(Math.pow(e.getX() - circulo1.getCentro().getX(), 2) + Math.pow(e.getY() - circulo1.getCentro().getY(), 2)), corAtual));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
            esperaFimCirculo = false;
            statusBar1.setText("Mensagem: aaaaaaaa");
        } else if (esperarFimElipse) {
            figuras.add(new Elipse(elipse1.getCentro().getX(), elipse1.getCentro().getY(),
                    Math.abs(e.getX() - elipse1.getCentro().getX()),
                    Math.abs(e.getY() - elipse1.getCentro().getY()), corAtual));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
            esperarFimElipse = false;
            statusBar1.setText("Mensagem: a a a a");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Este método é chamado quando um clique simples ocorre
        if (esperaPonto) {
            figuras.add(new Ponto(e.getX(), e.getY(), corAtual));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
            esperaPonto = false;
        } else if (esperaTexto) {
            textoDigitado = JOptionPane.showInputDialog("Digite o texto:");
            figuras.add(new Texto(e.getX(), e.getY(), textoDigitado, corAtual));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
            esperaTexto = false;
            statusBar1.setText("Mensagem: texto adicionado");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Este método é chamado quando um botão do mouse é pressionado
        if (esperaPonto) {
            figuras.add(new Ponto(e.getX(), e.getY(), corAtual));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
            esperaPonto = false;
        } else if (esperaInicioReta) {
            p1 = new Ponto(e.getX(), e.getY(), corAtual);
            esperaInicioReta = false;
            esperaFimReta = true;
            statusBar1.setText("Mensagem: clique o ponto final da reta");
        } else if (esperaInicioCirculo) {
            circulo1 = new Circulo(e.getX(), e.getY(), 0, corAtual);
            esperaInicioCirculo = false;
            esperaFimCirculo = true;
            statusBar1.setText("Mensagem: arraste para definir o raio do círculo");
        } else if (esperarInicioElipse) {
            elipse1 = new Elipse(e.getX(), e.getY(), 0, 0, corAtual);
            esperarInicioElipse = false;
            esperarFimElipse = true;
            statusBar1.setText("Mensagem: arraste para definir os eixos da elipse");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Este método é chamado quando o mouse entra na área do componente
        if (esperaFimReta) {
            statusBar1.setText("Mensagem: clique o ponto final da reta");
        } else if (esperaFimCirculo) {
            statusBar1.setText("Mensagem: clique o ponto final do circulo");
        } else if (esperarFimElipse) {
            statusBar1.setText("Mensagem: clique o ponto final da elipse");

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Este método é chamado quando o mouse sai da área do componente
        if (esperaFimReta) {
            statusBar1.setText("Mensagem: crloooooooooooooooooo");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Este método é chamado quando o mouse é arrastado enquanto um botão do mouse está pressionado
        if (esperaFimReta) {
            figuras.add(new Ponto(e.getX(), e.getY(), corAtual));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
        } else if (esperaFimCirculo) {
            pnlDesenho.paint(pnlDesenho.getGraphics());
            int raio = (int) Math.sqrt(Math.pow(e.getX() - circulo1.getCentro().getX(), 2) + Math.pow(e.getY() - circulo1.getCentro().getY(), 2));
            new Circulo(circulo1.getCentro().getX(), circulo1.getCentro().getY(), raio, corAtual).torneSeVisivel(pnlDesenho.getGraphics());
        } else if (esperarFimElipse) {
            pnlDesenho.paint(pnlDesenho.getGraphics());
            int semiEixoA = Math.abs(e.getX() - elipse1.getCentro().getX());
            int semiEixoB = Math.abs(e.getY() - elipse1.getCentro().getY());
            new Elipse(elipse1.getCentro().getX(), elipse1.getCentro().getY(), semiEixoA, semiEixoB, corAtual).torneSeVisivel(pnlDesenho.getGraphics());
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        statusBar2.setText("Coordenada: " + e.getX() + ", " + e.getY());
    }
}

