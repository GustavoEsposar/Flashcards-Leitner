package View;
import javax.swing.*;

import Controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

public class JanelaPrincipal extends JFrame {

    // Construtor
    public JanelaPrincipal() {
        configurarJanela();
        adicionarBotoes();
    }

    //-------------------------------------------------------
    //  <<Implementacao>>
    private void configurarJanela() {
        setTitle("Flashcards - Menu");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        setLayout(new GridBagLayout());
    }

    private void adicionarBotoes() {
        JButton botaoGerenciar = new JButton("Gerenciar Baralho");
        JButton botaoPraticar = new JButton("Praticar");

        botaoGerenciar.setPreferredSize(new Dimension(200, 50));
        botaoPraticar.setPreferredSize(new Dimension(200, 50));

        botaoGerenciar.setMargin(new Insets(15, 0, 15, 0));
        botaoPraticar.setMargin(new Insets(15, 0, 15, 0));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 15, 0); // Espaçamento vertical de 15px
        constraints.anchor = GridBagConstraints.CENTER;
        add(botaoGerenciar, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(15, 0, 0, 0); // Espaçamento vertical de 15px
        add(botaoPraticar, constraints);

        botaoGerenciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControllerPrincipal.abrirJanelaGerenciar();
            }
        });

        botaoPraticar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControllerPrincipal.abrirJanelaDificuldade();
            }
        });
    }

}
