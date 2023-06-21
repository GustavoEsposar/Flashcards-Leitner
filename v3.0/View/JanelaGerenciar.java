package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerGerenciar;
import Model.*;

import java.awt.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;

public class JanelaGerenciar extends JFrame {
    // Atributos
    private JTable tabela;
    private JButton botaoExportar;
    private JButton botaoImportar;
    private JButton botaoAdicionar;
    private JButton botaoRemover;
    private JButton botaoMover;

    // Construtor
    public JanelaGerenciar(JanelaPrincipal j_principal) {
        this.tabela = new JTable();
        configurarJanela(j_principal);
        adicionarComponentes();
        adicionarActionListeners();
    }

    //------------------------------------------------------------------
    // <<Implementação>>
    private void configurarJanela(JanelaPrincipal j_principal) {
        setTitle("Janela de Gerenciamento");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Torna a janela principal visível novamente
                j_principal.setVisible(true);
            }
        });
    }

    private void adicionarComponentes() {
        adicionarBotoes();
        adicionarTabela(this.tabela);
    }

    private void adicionarBotoes() {
        // Criar botões
        botaoExportar = new JButton("Exportar");
        botaoImportar = new JButton("Importar");
        botaoAdicionar = new JButton("Adicionar");
        botaoRemover = new JButton("Remover");
        botaoMover = new JButton("Mover");

        botaoExportar.setToolTipText("Salvar e compartilhar baralho atual.");
        botaoImportar.setToolTipText("Importar um baralho de flashcards.");
        botaoAdicionar.setToolTipText("Adicionar novo flashcard ao baralho atual.");
        botaoRemover.setToolTipText("Remover flashcard selecionado da caixa específica.");
        botaoMover.setToolTipText("Mover flashcard para caixa alvo.");

        // Configurar o layout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        constraints.anchor = GridBagConstraints.CENTER; // Alinhar ao centro horizontalmente

        // Adicionar os botões na primeira linha
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(botaoExportar, constraints);
        constraints.gridx++;
        buttonPanel.add(botaoImportar, constraints);
        constraints.gridx++;
        buttonPanel.add(botaoAdicionar, constraints);
        constraints.gridx++;
        buttonPanel.add(botaoRemover, constraints);
        constraints.gridx++;
        buttonPanel.add(botaoMover, constraints);

        // Adicionar o painel de botões
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 0;
        buttonConstraints.gridwidth = 5;
        buttonConstraints.weightx = 1;
        buttonConstraints.weighty = 0;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.insets = new Insets(10, 10, 10, 10); // Espaçamento para os limites

        add(buttonPanel, buttonConstraints);
    }
    
    private void adicionarTabela(JTable table) {
        // Configurar a tabela
        table.setEnabled(false); // Desabilitar interação com as células
        JScrollPane scrollPane = new JScrollPane(table);
        table.setCellSelectionEnabled(false); // Desabilitar interação com células
    
        // Configurar o layout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 6; // Alterado para 6 para acomodar a nova coluna
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10); // Espaçamento para os limites
    
        // Configurar as colunas da tabela
        String[] colunas = { "Caixa", "Índice", "Pergunta", "Resposta", "Status" }; // Nova coluna adicionada
        table.setModel(new DefaultTableModel(colunas, 0));
    
        // Personalizar o renderer das células
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer questionAnswerRenderer = new DefaultTableCellRenderer();
        questionAnswerRenderer.setHorizontalAlignment(JLabel.CENTER);
        questionAnswerRenderer.setVerticalAlignment(JLabel.CENTER);
        questionAnswerRenderer.setFont(questionAnswerRenderer.getFont().deriveFont(Font.BOLD));
    
        // Definir espaçamento vertical interno das células
        int paddingVertical = 3;
        questionAnswerRenderer.setBorder(BorderFactory.createEmptyBorder(paddingVertical, 0, paddingVertical, 0));
    
        // Definir largura das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
    
        // Aplicar o renderer personalizado às colunas
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(questionAnswerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(questionAnswerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Novo renderer para a coluna "Status"
    
        // Aumentar a fonte do texto da tabela
        Font tableFont = table.getFont();
        table.setFont(tableFont.deriveFont(tableFont.getSize() + 6f)); // Aumentar a fonte em 2 pontos
    
        // Definir espaçamento vertical das linhas
        table.setRowHeight(table.getRowHeight() + (paddingVertical * 2));

        // Adicionar o tooltip às células
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(new TableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component component = questionAnswerRenderer.getTableCellRendererComponent(table, value, isSelected,
                            hasFocus, row, column);
                    if (component instanceof JComponent) {
                        ((JComponent) component).setToolTipText("<html><p style='width:300px; font-size: 14px;'>"
                                + String.valueOf(value).replace("\n", "<br>") + "</p></html>");
                    }
                    return component;
                }
            });
        }
    
        // Adicionar a tabela com scroll pane
        add(scrollPane, constraints);
    }
    
    

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0); // Limpa todas as linhas existentes no modelo

        // Preenche o modelo com os dados atualizados
        for (Caixa caixa : ControllerGerenciar.getSistema().getBaralho().getCaixas()) {
            for (int i = 0; i < caixa.getQtdFlashcards(); i++) {
                Flashcard flashcard = caixa.getFlashcard(i);
                String acerto = "";
                if(flashcard.getAcerto() == 0) acerto = "-";
                else if(flashcard.getAcerto() == 1) acerto = "Acertou";
                else if(flashcard.getAcerto() == -1) acerto = "Errou";

                Object[] rowData = { caixa.getNumero(), flashcard.getIndice() + 1, flashcard.getPergunta(),
                        flashcard.getResposta(), acerto };
                model.addRow(rowData);
            }
        }
    }

    private void adicionarActionListeners() {
        botaoExportar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(ControllerGerenciar.getSistema().getBaralho().getQtdFlashcards() < 10) {
                        throw new IllegalStateException("Tenha ao menos 10 flahscards para poder exportar!");
                    }
                    ControllerGerenciar.exportarBaralho();
                } catch(IllegalStateException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoImportar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControllerGerenciar.importarBaralho();
            }
        });

        botaoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControllerGerenciar.adicionarFlashcard();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControllerGerenciar.removerFlashcard();
            }
        });

        botaoMover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControllerGerenciar.moverFlashcard();
            }
        });
    }
}
