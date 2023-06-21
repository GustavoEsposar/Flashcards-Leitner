package Controller;

import javax.swing.JOptionPane;

import Model.SistemaLeitner;
import View.JanelaGerenciar;
import View.JanelaPrincipal;

public class ControllerPrincipal {
    // Atributos -> implementação
    private static SistemaLeitner sistema;
    private static JanelaPrincipal j_principal;

    //-------------------------------------------------------
    //  <<Interface>>
    public static void setSistema(SistemaLeitner sistema) {
        ControllerPrincipal.sistema = sistema;
    }

    public static void setJanelaPai(JanelaPrincipal jan) {
        ControllerPrincipal.j_principal = jan;
    }

    public static void abrirJanelaGerenciar() {
        JanelaGerenciar j_gerenciar = new JanelaGerenciar(j_principal);
        ControllerGerenciar.setSistema(sistema);
        ControllerGerenciar.setJanela(j_gerenciar);
        j_gerenciar.atualizarTabela();
        j_gerenciar.setVisible(true);
        j_principal.dispose();
    }

    public static void abrirJanelaDificuldade() {
        try{
            if(sistema.getBaralho().getQtdFlashcards() == 0) {
                throw new IllegalStateException("Adicione um baralho para praticar!");
            }
            ControllerDificuldade.setJanelaPai(j_principal);
            ControllerDificuldade.iniciar();
            j_principal.dispose();
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}