package EditorGrafico;


import Figuras.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Objects;
import java.util.Vector;

public class Janela extends JFrame implements MouseListener, MouseMotionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    protected JButton btnPonto = new JButton("Ponto"),
            btnTexto = new JButton("Texto"),
            btnLinha = new JButton("Linha"),
            btnCirculo = new JButton("Circulo"),
            btnElipse = new JButton("Elipse"),
            btnquadrado = new JButton("Quadrado"),
            btnRetangulo = new JButton("Retangulo"),
            btnCores = new JButton("Cores"),
            btnAbrir = new JButton("Abrir"),
            btnSalvar = new JButton("Salvar"),
            btnApagar = new JButton("Apagar"),
            btnSair = new JButton("Sair");


    protected MeuJPanel pnlDesenho = new MeuJPanel();
    protected String fontName = "Arial";
    protected int fontSize = 12;
    protected int fontStyle = Font.PLAIN;
    protected Color fontColor = Color.BLACK;
    protected JLabel statusBar1 = new JLabel("Mensagem:"), statusBar2 = new JLabel("Coordenada:");
    protected boolean esperaPonto,
            esperaInicioReta, esperaFimReta,
            esperaInicioCirculo, esperaFimCirculo,
            esperarFimElipse, esperarInicioElipse,
            esperaFimquadrado, esperarInicioquadrado,
            esperaFimRetangulo, esperarInicioRetangulo,
            esperaTexto;

    protected Color corAtual = Color.BLACK;
    protected Color corDePreenchimento = Color.WHITE;
    protected Ponto p1;
    protected Circulo circulo1;
    protected Elipse elipse1;
    protected Quadrado quadrado1;
    protected Retangulo retangulo1;
    protected static Vector<Figura> figuras = new Vector<Figura>();
    protected String textoDigitado;

    public Janela() {
        super("Editor Grafico");
        try {
            Image btnPontoImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/ponto.jpg")));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo ponto.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnLinhaImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/linha.jpg")));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo linha.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCirculoImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/circulo.jpg")));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo circulo.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnElipseImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/elipse.jpg")));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo elipse.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCoresImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/cores.jpg")));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo cores.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnAbrirImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/abrir.jpg")));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo abrir.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSalvarImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/salvar.jpg")));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo salvar.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnApagarImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/apagar.jpg")));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo apagar.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnquadradoImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/quadrado.jpg")));
            btnquadrado.setIcon(new ImageIcon(btnquadradoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo quadrado.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSairImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/sair.jpg")));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo sair.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnRetanguloImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("../imagens/retangulo.jpg")));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo retangulo.jpg não foi encontrado", "Arquivo de imagem ausente", JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener(new DesenhoDePonto());
        btnLinha.addActionListener(new DesenhoDeReta());
        btnCirculo.addActionListener(new DesenhoCirculo());
        btnTexto.addActionListener(new DesenhoDeTexto());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnApagar.addActionListener(new Apagar());
        btnquadrado.addActionListener(new DesenhoDeQuadrado());
        btnRetangulo.addActionListener(new DesenhoDeRetangulo());
        btnAbrir.addActionListener(new AbrirArquivo());
        btnSalvar.addActionListener(new SalvarArquivo());
        btnSair.addActionListener(new Sair());
        btnCores.addActionListener(new MudarCor());


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
        pnlBotoes.add(btnquadrado);
        pnlBotoes.add(btnRetangulo);
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

        this.setSize(1200, 800);
        this.setVisible(true);
    }


    protected class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener {

        public MeuJPanel() {
            super();

            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(Color.WHITE);
            for (Figura f : figuras) {
                f.torneSeVisivel(g);
            }
        }

        public void mouseReleased(MouseEvent e) {
            // Este método é chamado quando um botão do mouse é liberado
            if (esperarFimElipse) {
                figuras.add(new Elipse(elipse1.getCentro().getX(), elipse1.getCentro().getY(), Math.abs(e.getX() - elipse1.getCentro().getX()), Math.abs(e.getY() - elipse1.getCentro().getY()), corAtual, corDePreenchimento));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperarFimElipse = false;
                statusBar1.setText("Mensagem: caralho so pika");
            } else if (esperaFimReta) {
                figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperaFimReta = false;
                statusBar1.setText("Mensagem: se fode ai");
            } else if (esperaFimCirculo) {
                figuras.add(new Circulo(circulo1.getCentro().getX(), circulo1.getCentro().getY(), (int) Math.sqrt(Math.pow(e.getX() - circulo1.getCentro().getX(), 2) + Math.pow(e.getY() - circulo1.getCentro().getY(), 2)), corAtual, corDePreenchimento));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperaFimCirculo = false;
                statusBar1.setText("Mensagem: cara como eu consegui isso ?");
            } else if (esperaFimquadrado) {
                figuras.add(new Quadrado(quadrado1.getP1().getX(), quadrado1.getP1().getY(),
                        Math.abs(e.getX() - quadrado1.getP1().getX()),
                        Math.abs(e.getY() - quadrado1.getP1().getY()), corAtual, corDePreenchimento));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperaFimquadrado = false;
                statusBar1.setText("Mensagem: cara como eu consegui isso ?");
            } else if (esperaFimRetangulo) {
                figuras.add(new Retangulo(retangulo1.getP1().getX(), retangulo1.getP1().getY(), Math.abs(e.getX() - retangulo1.getP1().getX()), Math.abs(e.getY() - retangulo1.getP1().getY()), corAtual, corDePreenchimento));
                figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
                esperaFimRetangulo = false;
                statusBar1.setText("Mensagem: vou me mata");
            }

        }

        public void mousePressed(MouseEvent e) {
            // Este método é chamado quando um botão do mouse é pressionado
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
                circulo1 = new Circulo(e.getX(), e.getY(), 0, corAtual, corDePreenchimento);
                esperaInicioCirculo = false;
                esperaFimCirculo = true;
                statusBar1.setText("Mensagem: clique o ponto final do círculo");
            } else if (esperaTexto) {
                figuras.add(new Texto(e.getX(), e.getY(), textoDigitado, fontColor, fontName, fontSize, fontStyle));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaTexto = false;
                statusBar1.setText("Mensagem: texto adicionado");
            } else if (esperarInicioElipse) {
                elipse1 = new Elipse(e.getX(), e.getY(), 0, 0, corAtual, corDePreenchimento);
                esperarInicioElipse = false;
                esperarFimElipse = true;
            } else if (esperarInicioquadrado) {
                quadrado1 = new Quadrado(e.getX(), e.getY(), 0, 0, corAtual, corDePreenchimento);
                esperarInicioquadrado = false;
                esperaFimquadrado = true;
            } else if (esperarInicioRetangulo) {
                retangulo1 = new Retangulo(e.getX(), e.getY(), 0, 0, corAtual, corDePreenchimento);
                esperarInicioRetangulo = false;
                esperaFimRetangulo = true;
            }
        }


        @Override
        public void mouseDragged(MouseEvent e) {
            // Este método torna visivel a figura sendo desenhada
            Graphics g = pnlDesenho.getGraphics();
            if (esperaFimReta) {
                pnlDesenho.paint(g);
                new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual).torneSeVisivel(g);
            } else if (esperaFimCirculo) {
                pnlDesenho.paint(g);
                int raio = (int) Math.sqrt(Math.pow(e.getX() - circulo1.getCentro().getX(), 2) + Math.pow(e.getY() - circulo1.getCentro().getY(), 2));
                new Circulo(circulo1.getCentro().getX(), circulo1.getCentro().getY(), raio, corAtual, corDePreenchimento).torneSeVisivel(g);
            } else if (esperarFimElipse) {
                pnlDesenho.paint(g);
                int semiEixoA = Math.abs(e.getX() - elipse1.getCentro().getX());
                int semiEixoB = Math.abs(e.getY() - elipse1.getCentro().getY());
                new Elipse(elipse1.getCentro().getX(), elipse1.getCentro().getY(), semiEixoA, semiEixoB, corAtual, corDePreenchimento).torneSeVisivel(g);
            } else if (esperaFimquadrado) {
                pnlDesenho.paint(g);
                int semiEixoA = Math.abs(e.getX() - quadrado1.getP1().getX());
                int semiEixoB = Math.abs(e.getY() - quadrado1.getP1().getY());
                new Quadrado(quadrado1.getP1().getX(), quadrado1.getP1().getY(), semiEixoA, semiEixoB, corAtual, corDePreenchimento).torneSeVisivel(g);
            } else if (esperaFimRetangulo) {
                pnlDesenho.paint(g);
                int lado = Math.max(Math.abs(e.getX() - retangulo1.getP1().getX()), Math.abs(e.getY() - retangulo1.getP1().getY()));
                int x = Math.min(retangulo1.getP1().getX(), e.getX());
                int y = Math.min(retangulo1.getP1().getY(), e.getY());
                new Retangulo(x, y, lado, lado, corAtual, corDePreenchimento).torneSeVisivel(g);
            }
        }


        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordenada: " + e.getX() + "," + e.getY());
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // Este método é chamado quando um botão do mouse é liberado
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
            figuras.add(new Texto(e.getX(), e.getY(), textoDigitado, corAtual, fontName, fontSize, fontStyle));
            figuras.lastElement().torneSeVisivel(pnlDesenho.getGraphics());
            esperaTexto = false;
            statusBar1.setText("Mensagem: texto adicionado");
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Este método é chamado quando um botão do mouse é pressionado
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

        } else if (esperaFimquadrado) {
            statusBar1.setText("Mensagem: clique o ponto final do quadrado");

        } else if (esperaFimRetangulo) {
            statusBar1.setText("Mensagem: clique o ponto final do retangulo");

        } else if (esperaTexto) {
            statusBar1.setText("Mensagem: clique o ponto inicial do texto");

        } else if (esperaPonto) {
            statusBar1.setText("Mensagem: clique o ponto desejado");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Este método é chamado quando o mouse sai da área do componente
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Este método é chamado quando o mouse é arrastado enquanto um botão do mouse está pressionado

    }


    @Override
    public void mouseMoved(MouseEvent e) {
        statusBar2.setText("Coordenada: " + e.getX() + ", " + e.getY());
    }

    protected class AbrirArquivo implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\src\\desenho");
            fileChooser.setDialogTitle("Escolha o arquivo que deseja abrir:");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Paint file", "pnt"));

            int userSelection = fileChooser.showOpenDialog(pnlDesenho);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                try {
                    BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
                    String linha;
                    figuras.clear();
                    while ((linha = leitor.readLine()) != null) {
                        char tipo = linha.charAt(0);
                        switch (tipo) {
                            case 'p':
                                figuras.add(new Ponto(linha));
                                break;
                            case 'l':
                                figuras.add(new Linha(linha));
                                break;
                            case 'c':
                                figuras.add(new Circulo(linha));
                                break;
                            case 'e':
                                figuras.add(new Elipse(linha));
                                break;
                            case 'q':
                                figuras.add(new Quadrado(linha));
                                break;
                            case 't':
                                figuras.add(new Texto(linha));
                                break;
                            case 'r':
                                figuras.add(new Retangulo(linha));
                                break;
                        }
                    }
                    leitor.close();
                    pnlDesenho.repaint();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    protected static class SalvarArquivo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File directory = new File(System.getProperty("user.dir") + "\\src\\desenho");
            if (!directory.exists()) {
                directory.mkdir();
            }
            String filename = JOptionPane.showInputDialog("Digite o nome do arquivo:");
            if (filename != null) {
                if (!filename.endsWith(".pnt")) filename += ".pnt";
                File arq = new File(directory, filename);

                try {
                    FileWriter mudarArq = new FileWriter(arq);
                    for (Figura figura : figuras)
                        mudarArq.write(figura + "\n");
                    mudarArq.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
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

    protected class DesenhoDeQuadrado implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperarInicioquadrado = true;

            statusBar1.setText("Mensagem: clique o centro do Quadrado");
        }
    }

    protected class DesenhoDeRetangulo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperarInicioRetangulo = true;

            statusBar1.setText("Mensagem: clique o centro do Retangulo");
        }
    }

    protected static class FechamentoDeJanela extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    protected class Apagar implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            figuras.clear();
            pnlDesenho.repaint();

        }

    }

    protected class MudarCor implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final JDialog dialog = new JDialog();
            dialog.setTitle("Escolha as cores");
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

            final JColorChooser colorChooserLinha = new JColorChooser(corAtual);
            final JColorChooser colorChooserPreenchimento = new JColorChooser(corAtual);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(e1 -> {
                corAtual = colorChooserLinha.getColor();
                corDePreenchimento = colorChooserPreenchimento.getColor();
                dialog.dispose();
            });

            JLabel labelLinha = new JLabel("Cor da Linha:");
            labelLinha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            dialog.add(labelLinha);
            dialog.add(colorChooserLinha);

            JLabel labelPreenchimento = new JLabel("Cor de Preenchimento:");
            labelPreenchimento.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            dialog.add(labelPreenchimento);
            dialog.add(colorChooserPreenchimento);

            okButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            dialog.add(okButton);

            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            dialog.pack();
            dialog.setVisible(true);
        }


    }

    protected class DesenhoDeTexto implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaTexto = true;
            statusBar1.setText("Mensagem: clique o local para o texto");

            final JDialog dialog = new JDialog();
            dialog.setTitle("Digite o texto");
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

            final JTextArea textAreaTexto = new JTextArea(8, 20);
            textAreaTexto.setLineWrap(true);
            textAreaTexto.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textAreaTexto);
            scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, textAreaTexto.getPreferredSize().height));

            //vetor de fonte
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] fontNames = ge.getAvailableFontFamilyNames();
            final JComboBox<String> comboBoxFontes = new JComboBox<>(fontNames);
            comboBoxFontes.setSelectedItem(fontName);

            JButton btnCustomizar = new JButton("Customizar");
            btnCustomizar.addActionListener(e12 -> {
                final JDialog dialogCustomizar = new JDialog();
                dialogCustomizar.setTitle("Customizar Texto");
                dialogCustomizar.setLayout(new BoxLayout(dialogCustomizar.getContentPane(), BoxLayout.Y_AXIS));

                final JTextField textFieldFontSize = new JTextField(String.valueOf(fontSize), 20);
                textFieldFontSize.setMaximumSize(textFieldFontSize.getPreferredSize());

                final JCheckBox checkBoxBold = new JCheckBox("Negrito", (fontStyle & Font.BOLD) != 0);
                final JCheckBox checkBoxItalic = new JCheckBox("Itálico", (fontStyle & Font.ITALIC) != 0);
                final JColorChooser colorChooser = new JColorChooser(fontColor);

                JButton okButton = new JButton("OK");
                okButton.addActionListener(e1 -> {
                    fontName = (String) comboBoxFontes.getSelectedItem();
                    fontSize = Integer.parseInt(textFieldFontSize.getText());
                    fontStyle = (checkBoxBold.isSelected() ? Font.BOLD : 0) | (checkBoxItalic.isSelected() ? Font.ITALIC : 0);
                    fontColor = colorChooser.getColor();
                    dialogCustomizar.dispose();
                });

                dialogCustomizar.add(new JLabel("Nome da Fonte:"));
                dialogCustomizar.add(comboBoxFontes);
                dialogCustomizar.add(new JLabel("Tamanho da Fonte:"));
                dialogCustomizar.add(textFieldFontSize);
                dialogCustomizar.add(checkBoxBold);
                dialogCustomizar.add(checkBoxItalic);
                dialogCustomizar.add(new JLabel("Cor da Fonte:"));
                dialogCustomizar.add(colorChooser);
                dialogCustomizar.add(okButton);
                dialogCustomizar.pack();
                dialogCustomizar.setVisible(true);
            });

            JButton okButton = new JButton("OK");
            okButton.addActionListener(e13 -> {
                textoDigitado = textAreaTexto.getText();
                dialog.dispose();
            });

            dialog.add(new JLabel("Texto:"));
            dialog.add(scrollPane);
            dialog.add(btnCustomizar);
            dialog.add(okButton);
            dialog.pack();
            dialog.setVisible(true);
        }

    }

    protected static class Sair implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


}
