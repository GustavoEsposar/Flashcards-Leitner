package View;

import javax.swing.*;

import Controller.ControllerGerenciar;
import Controller.ControllerRemover;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaRemover extends JFrame {
    // Atributos
    private JLabel labelCaixa;
    private JTextField txtCaixa;
    private JLabel labelIndice;
    private JTextField txtIndice;

    // Construtor
    public JanelaRemover() {
        configurarJanela();
        adicionarComponentes();
    }

    //----------------------------------------------------------------
    // <<Implementação>>
    private void configurarJanela() {
        setTitle("Remover Flashcard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());
    }

    private void adicionarComponentes() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0); 

        labelCaixa = new JLabel("Caixa:");
        txtCaixa = new JTextField(10);
        labelIndice = new JLabel("Índice:");
        txtIndice = new JTextField(10);
        JButton btnRemover = new JButton("Remover");

        // Label e campo de input para Caixa
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelCaixa, constraints);

        constraints.gridx = 1;
        add(txtCaixa, constraints);

        // Label e campo de input para Índice
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelIndice, constraints);

        constraints.gridx = 1;
        add(txtIndice, constraints);

        // Botão Remover
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Ocupa duas colunas
        add(btnRemover, constraints);

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para remover o flashcard
                try {
                    int caixa = Integer.parseInt(txtCaixa.getText()) - 1;
                    int indice = Integer.parseInt(txtIndice.getText()) - 1;

                    if(caixa < 0 || caixa > 4) {
                        throw new IllegalArgumentException("Caixa inválida");
                    }

                    ControllerRemover.removerFlashcard(caixa, indice);
                    ControllerGerenciar.getJanela().atualizarTabela();

                    dispose(); // Fecha a janela após a remoção
                    reset();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Índice inválido ou caixa vazia", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void reset() {
        this.txtCaixa.setText("");
        this.txtIndice.setText("");
    }
}
