package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ControllerAdicionar;

public class JanelaAdicionar extends JFrame {
    private JTextField txtPergunta;
    private JTextField txtResposta;

    public JanelaAdicionar() {
        exibirJanela();
    }

    private void exibirJanela() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Flashcard");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20, 20, 0, 0);

        JLabel lblPergunta = new JLabel("Pergunta:");
        add(lblPergunta, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(20, 0, 0, 20);

        txtPergunta = new JTextField(20);
        add(txtPergunta, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(20, 20, 0, 0);

        JLabel lblResposta = new JLabel("Resposta:");
        add(lblResposta, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(20, 0, 0, 20);

        txtResposta = new JTextField(20);
        add(txtResposta, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 0, 0, 0);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setPreferredSize(new Dimension(100, 30));
        constraints.insets = new Insets(10, 0, 0, 0);
        add(btnAdicionar, constraints);

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String perguntaInput = txtPergunta.getText().trim();
                String respostaInput = txtResposta.getText().trim();

                if (perguntaInput.isEmpty() || respostaInput.isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaAdicionar.this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    ControllerAdicionar.adicionarFlashcard(perguntaInput, respostaInput);
                    dispose();
                    reset();
                }
            }
        });

        pack();
    }

    private void reset() {
        txtPergunta.setText("");
        txtResposta.setText("");
    }
}