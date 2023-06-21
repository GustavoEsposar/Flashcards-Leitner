package Controller;


import Model.*;
import View.*;

public class ControllerGerenciar {
    // Atributos -> implementação
    private static SistemaLeitner sistema;
    private static JanelaGerenciar j_gerenciar;
    private static JanelaExportar j_exportar;
    private static JanelaImportar j_importar;
    private static JanelaAdicionar j_add;
    private static JanelaRemover j_remover;
    private static JanelaMover j_mover;
    
    //-------------------------------------------------------
    //  <<Interface>>
    public static void setSistema(SistemaLeitner sistema) {
        ControllerGerenciar.sistema = sistema;
    }

    public static SistemaLeitner getSistema() {
        return ControllerGerenciar.sistema;
    }

    public static void setJanela(JanelaGerenciar jan) {
        ControllerGerenciar.j_gerenciar = jan;
    }

    public static JanelaGerenciar getJanela() {
        return ControllerGerenciar.j_gerenciar;
    }

    public static void exportarBaralho() {
        j_exportar = new JanelaExportar();
        j_exportar.setVisible(true);
        
        if (!ControllerExportar.getCancelar()) {
            sistema.exportarBaralho(ControllerExportar.getDiretorio(), ControllerExportar.getCompartilhar());
        }
        j_exportar.dispose();
        
    }

    public static void importarBaralho() {
        j_importar = new JanelaImportar();
        j_importar.setVisible(true);

        if (!ControllerImportar.getCancelar()) {
            sistema.getBaralho().esvaziarBaralho();
            sistema.importarBaralho(ControllerImportar.getDiretorio());
            j_gerenciar.atualizarTabela();
        }
        j_importar.dispose();
    }

    public static void adicionarFlashcard() {
        j_add = new JanelaAdicionar();
        ControllerAdicionar.setJanelaPai(j_gerenciar);
        j_add.setVisible(true);
    }

    public static void removerFlashcard() {
        j_remover = new JanelaRemover();
        j_remover.setVisible(true);
    }

    public static void moverFlashcard() {
        j_mover = new JanelaMover();
        ControllerMover.setJanelaPai(j_gerenciar);
        j_mover.setVisible(true);
    }
}
