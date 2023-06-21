package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.ControllerMover;

public class JanelaMover extends JFrame {
    private JLabel labelCaixa;
    private JLabel labelIndice;
    private JLabel labelCaixaAlvo;
    private JTextField inputCaixa;
    private JTextField inputIndice;
    private JTextField inputCaixaAlvo;
    private JButton botaoMover;

    public JanelaMover() {
        configurarJanela();
        adicionarComponentes();
        adicionarActionListeners();
        reset();
    }

    //---------------------------------------------------------------
    // <<Implementação>>
    private void configurarJanela() {
        setTitle("Mover Flashcard");
        setSize(250, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());
    }

    private void adicionarComponentes() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 10, 0); 

        // Label e campo de input para Caixa
        labelCaixa = new JLabel("Caixa:");
        add(labelCaixa, constraints);

        constraints.gridx++;
        inputCaixa = new JTextField(10);
        add(inputCaixa, constraints);

        // Label e campo de input para Índice
        constraints.gridx = 0;
        constraints.gridy++;
        labelIndice = new JLabel("Índice:");
        add(labelIndice, constraints);

        constraints.gridx++;
        inputIndice = new JTextField(10);
        add(inputIndice, constraints);

        // Label e campo de input para Caixa Alvo
        constraints.gridx = 0;
        constraints.gridy++;
        labelCaixaAlvo = new JLabel("Caixa Alvo:");
        add(labelCaixaAlvo, constraints);

        constraints.gridx++;
        inputCaixaAlvo = new JTextField(10);
        add(inputCaixaAlvo, constraints);

        // Botão Mover
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2; // Ocupa duas colunas
        botaoMover = new JButton("Mover");
        add(botaoMover, constraints);
    }

    private void adicionarActionListeners() {
        botaoMover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int caixa = Integer.parseInt(inputCaixa.getText());
                    int indice = Integer.parseInt(inputIndice.getText());
                    int caixaAlvo = Integer.parseInt(inputCaixaAlvo.getText());
                    ControllerMover.moverFlashcard(caixa, indice, caixaAlvo);
                    reset();
                    dispose(); // Fecha a janela após a ação de mover
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valores inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void reset() {
        this.inputCaixa.setText("");
        this.inputIndice.setText("");
        this.inputCaixaAlvo.setText("");
    }
}
