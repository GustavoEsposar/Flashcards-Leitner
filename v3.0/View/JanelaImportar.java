package View;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ControllerImportar;

public class JanelaImportar extends JFrame {
    // Construtor
    public JanelaImportar() {
        configurarJanela();
        exibirJanela();
    }

    // Métodos

    private void configurarJanela() {
        setTitle("Importar Arquivo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());
    }

    private void exibirJanela() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Importação (*.fex, *.fexc)", "fex", "fexc"));
        fileChooser.setAcceptAllFileFilterUsed(false); // Desabilitar o filtro "Todos os arquivos"
    
        int resposta = fileChooser.showOpenDialog(this);
        if (resposta == JFileChooser.APPROVE_OPTION) {
            ControllerImportar.setDiretorio(fileChooser.getSelectedFile().getAbsolutePath());
            dispose(); // Fechar automaticamente a janela após a seleção do arquivo
        } else if (resposta == JFileChooser.CANCEL_OPTION) {
            ControllerImportar.setCancelar(true);
        }
    }
}
