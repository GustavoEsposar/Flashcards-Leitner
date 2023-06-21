package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.ControllerDificuldade;
import Controller.ControllerPontuacao;
import Controller.ControllerSessao;

public class JanelaSessao extends JFrame {
    // Atributos
    private JPanel painelTexto;
    private JLabel textArea;
    private JLabel quantidadeLabel;

    // Construtor
    public JanelaSessao() {
        this.painelTexto = new JPanel();
        this.textArea = new JLabel();
        this.quantidadeLabel = new JLabel();
        configurarJanela(ControllerSessao.getJanelaPai(), ControllerDificuldade.getNivel());
    }

    //------------------------------------------------------------------------
    // <<Implementação>>
    private void configurarJanela(JanelaPrincipal pai, int nivel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 750); // Aumenta o tamanho da janela
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Praticar!");
        setLayout(new GridBagLayout());

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (ControllerSessao.getTodosSorteados() == false) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Deseja encerrar a sessão?", "Confirmação",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) {
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    } else {
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        ControllerPontuacao.iniciarJanela();
                    }
                } else {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    ControllerPontuacao.iniciarJanela();
                }
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 1;
        constraints.weighty = 1;

        add(criaPainelTopo(pai), constraints);

        constraints.gridy = 1;
        JPanel painelIntermediario = new JPanel(new GridBagLayout()); // use GridBagLayout here too
        painelIntermediario.add(criaPainelTexto(), constraints);
        add(painelIntermediario, constraints);

        constraints.gridy = 2;
        add(criaPainelInferior(), constraints);
    }

    private JPanel criaPainelTopo(JanelaPrincipal pai) {
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Aumenta o espaçamento horizontal e vertical

        JButton acerteiButton = new JButton("Acertei");
        acerteiButton.setFont(acerteiButton.getFont().deriveFont(Font.BOLD, 20)); // Aumenta o tamanho da fonte do botão
        acerteiButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ControllerSessao.getTodosSorteados() == false) {
                    if (ControllerSessao.getClicou() == false) {
                        ControllerSessao.incrementarAcertos();
                        ControllerSessao.avancarFlashcard();
                        ControllerSessao.setClicou(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Resposta já indicada!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos os flashcards já foram acessados.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton erreiButton = new JButton("Errei");
        erreiButton.setFont(erreiButton.getFont().deriveFont(Font.BOLD, 20)); // Aumenta o tamanho da fonte do botão
        erreiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ControllerSessao.getTodosSorteados() == false) {
                    if (ControllerSessao.getClicou() == false) {
                        ControllerSessao.incrementarErros();
                        ControllerSessao.retrocederFlashcard();
                        ControllerSessao.setClicou(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Resposta já indicada!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos os flashcards já foram acessados.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        quantidadeLabel.setFont(quantidadeLabel.getFont().deriveFont(Font.BOLD, 20));
        this.quantidadeLabel.setText("Flashcards:  " + ControllerSessao.getContador() + "/"
                + Integer.toString(ControllerSessao.getSistema().getBaralho().getSomaQtd(ControllerSessao.getQtdCaixas())));

        painelTopo.add(acerteiButton);
        painelTopo.add(erreiButton);
        painelTopo.add(quantidadeLabel);

        return painelTopo;
    }

    private JPanel criaPainelTexto() {

        this.painelTexto.setLayout(new GridBagLayout());

        this.textArea.setBorder(new EmptyBorder(40, 15, 40, 40)); // Aumenta a margem interna

        this.textArea.setHorizontalAlignment(SwingConstants.CENTER); // Centralizar o texto horizontalmente
        
        Font fonteTexto = textArea.getFont();
        textArea.setFont(fonteTexto.deriveFont(fonteTexto.getSize() * 2)); // Aumenta o tamanho da fonte interna

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        atualizarFlashcard();
        this.painelTexto.add(this.textArea, constraints);
        this.painelTexto.setBorder(new LineBorder(Color.BLACK, 2, true)); // Aumenta a espessura da borda
        this.painelTexto.setPreferredSize(new Dimension(800, 400)); // Aumenta o tamanho do painel

        return this.painelTexto;
    }

    private JPanel criaPainelInferior() {
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Aumenta o espaçamento horizontal e vertical

        JButton virarButton = new JButton("Virar");
        virarButton.setFont(quantidadeLabel.getFont().deriveFont(Font.BOLD, 20));
        virarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarFlashcard();
            }
        });

        JButton passarButton = new JButton("Passar");
        passarButton.setFont(quantidadeLabel.getFont().deriveFont(Font.BOLD, 20));
        passarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ControllerSessao.getClicou() == false) {
                    atualizarContagem();
                    ControllerSessao.setClicou(false);
                    try {
                        ControllerSessao.passarFlashcard();
                        atualizarContagem();
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    atualizarFlashcard();
                }else {
                    JOptionPane.showMessageDialog(null, "Não é possível passar quando\n a resposta foi indicada!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton sortearButton = new JButton("Sortear");
        sortearButton.setFont(quantidadeLabel.getFont().deriveFont(Font.BOLD, 20));
        sortearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ControllerSessao.getTodosSorteados() == false) {
                    if (ControllerSessao.getClicou() == true) {
                        atualizarContagem();
                        ControllerSessao.setClicou(false);
                        try {
                            ControllerSessao.sortearFlashcard();
                            atualizarContagem();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        atualizarFlashcard();
                    } else {
                        JOptionPane.showMessageDialog(null, "Indique a resposta para sortear!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos os flashcards já foram acessados.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        painelInferior.add(virarButton);
        painelInferior.add(passarButton);
        painelInferior.add(sortearButton);

        return painelInferior;
    }

    public void atualizarFlashcard() {
        if (ControllerSessao.getVirou() == true) {
            textArea.setText("<html><p style=\"width:600px; font-size: " + 16 + "px\">" + ControllerSessao.getAtual().getResposta()
                    + "</p></html>"); // Aumenta a largura do texto para evitar quebras de linha
            painelTexto.setBackground(new Color(173, 216, 230));
            ControllerSessao.setvirou(false);
        } else {
            textArea.setText("<html><p style=\"width:600px; font-size: " + 16 + "px\">" + ControllerSessao.getAtual().getPergunta()
                    + "</p></html>"); // Aumenta a largura do texto para evitar quebras de linha
            painelTexto.setBackground(new Color(220, 220, 220));
            ControllerSessao.setvirou(true);
        }
    }

    public void atualizarContagem() {
        quantidadeLabel.setText("Flashcards:  " + (ControllerSessao.getContador()) + "/" + ControllerSessao.getTotal());
    }
}
