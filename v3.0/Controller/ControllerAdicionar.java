package Controller;

import Model.SistemaLeitner;
import View.*;

public class ControllerAdicionar {
    // Atributos -> implementação
    private static SistemaLeitner sistema;
    private static JanelaGerenciar janela;

    //-----------------------------------------------------------------
    //  <<interface>>
    public static void adicionarFlashcard(String pergunta, String resposta) {
        sistema.getBaralho().adicionarFlashcard(pergunta, resposta, 2, sistema.getBaralho().prox_id());
        janela.atualizarTabela();
    }

    public static void setJanelaPai(JanelaGerenciar jan) {
        janela = jan;
    }

    public static void setSistema(SistemaLeitner sis) {
        sistema = sis;
    }
}
