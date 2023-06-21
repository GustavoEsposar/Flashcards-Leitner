package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.ControllerDificuldade;
import Controller.ControllerPontuacao;

public class JanelaPontuacao extends JFrame {

    // Construtor
    public JanelaPontuacao() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Torna a janela principal visível novamente
                ControllerDificuldade.getJanelaPai().setVisible(true);
                dispose();
            }
        });
        configurarJanela();
        adicionarComponentes();
    }

    //------------------------------------------------------------------
    // <<Implementação>>
    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sessão Finalizada!");
        setLayout(new GridBagLayout());

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());
    }

    private void adicionarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JLabel labelTotal = new JLabel("Total no Baralho: " + ControllerPontuacao.getTotal());
        JLabel labelAcertos = new JLabel("Acertos: " + ControllerPontuacao.getAcertos());
        JLabel labelErros = new JLabel("Erros: " + ControllerPontuacao.getErros());
        JLabel labelPassados = new JLabel("Passados: " + ControllerPontuacao.getPassados());

        Font fonte = new Font(labelTotal.getFont().getName(), Font.BOLD, 20);
        labelTotal.setFont(fonte);
        labelAcertos.setFont(fonte);
        labelErros.setFont(fonte);
        labelPassados.setFont(fonte);

        gbc.gridy = 0;
        add(labelTotal, gbc);

        gbc.gridy = 1;
        add(labelAcertos, gbc);

        gbc.gridy = 2;
        add(labelErros, gbc);

        gbc.gridy = 3;
        add(labelPassados, gbc);
    }
}
