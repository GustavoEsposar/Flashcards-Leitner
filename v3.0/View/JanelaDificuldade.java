package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.ControllerDificuldade;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JanelaDificuldade extends JFrame {
    // Atributos
    private JLabel acessoLabel;
    private JLabel pontuacaoLabel;
    private JLabel recomendadoLabel;

    // Construtor
    public JanelaDificuldade() {
        configurarJanela();
        adicionarComponentes();
        atualizarDados();
    }

    // Configurar a janela
    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Seleção de Caixas");

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Torna a janela principal visível novamente
                ControllerDificuldade.getJanelaPai().setVisible(true);
            }
        });
    }

    // Adicionar componentes na janela
    private void adicionarComponentes() {
        // Cria um EmptyBorder com padding de 30 pixels
        EmptyBorder padding = new EmptyBorder(30, 30, 30, 30);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 0, 5, 0);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(padding); // Aplica o padding ao JPanel principal

        // Label "Último Acesso"
        acessoLabel = new JLabel("Último Acesso: ");
        acessoLabel.setFont(acessoLabel.getFont().deriveFont(acessoLabel.getFont().getSize() + 6f));
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(acessoLabel, constraints);

        // Label "Última Pontuação"
        constraints.gridy++;
        pontuacaoLabel = new JLabel("Última Pontuação: -");
        pontuacaoLabel.setFont(pontuacaoLabel.getFont().deriveFont(pontuacaoLabel.getFont().getSize() + 6f));
        panel.add(pontuacaoLabel, constraints);

        // Label "Nível Recomendado"
        constraints.gridy++;
        recomendadoLabel = new JLabel("Nível Recomendado: ");
        recomendadoLabel.setFont(recomendadoLabel.getFont().deriveFont(recomendadoLabel.getFont().getSize() + 6f));
        panel.add(recomendadoLabel, constraints);

        // Painel com os botões dos níveis
        constraints.gridy++;
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new GridBagLayout());
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 0;
        buttonConstraints.insets = new Insets(5, 0, 5, 0);
        buttonConstraints.anchor = GridBagConstraints.CENTER;

        //int dificuldadeRecomendada = ControllerDificuldade.getSistema().recomendarDificuldade();
        for (int i = 1; i <= 5; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(e -> {
                ControllerDificuldade.getSistema().setDificuldade(Integer.parseInt(button.getText()));
                ControllerDificuldade.setNivel(Integer.parseInt(button.getText())); // passa a seleção de caixas sem subtrair pois nao precisa
            });
            panelBotoes.add(button, buttonConstraints);
            buttonConstraints.gridx++; // Avança para a próxima coluna
        }

        // Botão "Concluir"
        JPanel panelConcluir = new JPanel(); // JPanel para o botão "Concluir"
        panelConcluir.setLayout(new GridBagLayout());
        GridBagConstraints concluirConstraints = new GridBagConstraints();
        concluirConstraints.gridx = 0;
        concluirConstraints.gridy = 0;
        concluirConstraints.insets = new Insets(5, 5, 5, 5);
        concluirConstraints.anchor = GridBagConstraints.CENTER;

        JButton concluirButton = new JButton("Concluir");
        concluirButton.addActionListener(e -> {
            if (ControllerDificuldade.getNivel() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um nível para continuar.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try{
                    ControllerDificuldade.iniciarSessao(ControllerDificuldade.getJanelaPai());
                    dispose();
                } catch(RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panelConcluir.add(concluirButton, concluirConstraints);
        panelBotoes.add(panelConcluir, buttonConstraints); // Adiciona o JPanel do botão "Concluir" ao painel dos botões
        panel.add(panelBotoes, constraints);
        add(panel);

        pack();
    }

    public void atualizarDados() {
        this.acessoLabel.setText("Último Acesso: " + ControllerDificuldade.getSistema().getUltimaData());
        if(ControllerDificuldade.getSistema().getUltimaPontuacao() != 0) {
            this.pontuacaoLabel.setText("Última Pontuação: " + ControllerDificuldade.getSistema().getUltimaPontuacao());
        }
        this.recomendadoLabel.setText("Nível Recomendado: " + ControllerDificuldade.getSistema().recomendarDificuldade());
    }
}
