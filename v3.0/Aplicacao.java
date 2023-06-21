import Controller.*;
import Model.SistemaLeitner;
import View.JanelaPrincipal;

public class Aplicacao {
    public static void main(String[] args) {
        inicializar();
    }
    
    public static void inicializar() {
        SistemaLeitner sistema = new SistemaLeitner();
        ControllerPrincipal.setSistema(sistema);
        ControllerGerenciar.setSistema(sistema);
        ControllerAdicionar.setSistema(sistema);
        ControllerRemover.setSistema(sistema);
        ControllerMover.setSistema(sistema);
        ControllerDificuldade.setSistema(sistema);
        ControllerSessao.setSistema(sistema);
        ControllerPontuacao.setSistema(sistema);
        JanelaPrincipal j_principal = new JanelaPrincipal();
        ControllerPrincipal.setJanelaPai(j_principal);
        
        j_principal.setVisible(true);
    }
}