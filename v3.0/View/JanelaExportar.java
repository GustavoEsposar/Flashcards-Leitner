package View;

import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ControllerExportar;

public class JanelaExportar extends JFrame {

    public JanelaExportar() {
        construirJanela();
    }

    private void construirJanela() {
        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar Arquivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Adicionar filtros para os formatos de exportação     
        FileNameExtensionFilter filtroFormato1 = new FileNameExtensionFilter("Compartilhável (*.fexc)", "fexc");
        FileNameExtensionFilter filtroFormato2 = new FileNameExtensionFilter("Pessoal (*.fex)", "fex");
        fileChooser.setAcceptAllFileFilterUsed(false); // Desabilitar o filtro "Todos os arquivos"
        fileChooser.addChoosableFileFilter(filtroFormato1);
        fileChooser.addChoosableFileFilter(filtroFormato2);

        int resposta = fileChooser.showSaveDialog(this);
        if (resposta == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            ControllerExportar.setDiretorio(arquivoSelecionado.getAbsolutePath());
            String extensao = ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];

            if (extensao.equals("fexc")) {
                ControllerExportar.setCompartilhar(true);
            } else {
                ControllerExportar.setCompartilhar(false);
            }

            dispose(); // Fechar automaticamente a janela após a seleção do arquivo
        } else if (resposta == JFileChooser.CANCEL_OPTION) {
            ControllerExportar.setCancelar(true);
        }
    }
}
