package Controller;

import Model.SistemaLeitner;
import View.JanelaGerenciar;

public class ControllerMover {
    // Atributos -> implementação
    private static SistemaLeitner sistema;
    private static JanelaGerenciar j_gerenciar;
    private static int caixa;
    private static int indice;
    private static int caixa_alvo;

    //-----------------------------------------------------------------
    //  <<interface>>
    public static void setSistema(SistemaLeitner sistema) {
        ControllerMover.sistema = sistema;
    }

    public static SistemaLeitner getSistema() {
        return ControllerMover.sistema;
    }

    public static void setJanelaPai(JanelaGerenciar jan) {
        ControllerMover.j_gerenciar = jan;
    }

    public static JanelaGerenciar getJanelaPai() {
        return ControllerMover.j_gerenciar;
    }

    public static void setCaixa(int caixa) {
        ControllerMover.caixa = caixa;
    }

    public static int getCaixa() {
        return ControllerMover.caixa;
    }

    public static void setIndice(int indice) {
        ControllerMover.indice = indice;
    }

    public static int getIndice() {
        return ControllerMover.indice;
    }

    public static void setCaixaAlvo(int caixa) {
        ControllerMover.caixa_alvo = caixa;
    }

    public static int getCaixaAlvo() {
        return ControllerMover.caixa_alvo;
    }

    public static void moverFlashcard(int caixa, int indice, int caixa_alvo) {
        if (caixa < 1 || caixa > 5) {
            throw new IllegalArgumentException("Caixa inválida");
        }
        if (caixa_alvo < 1 || caixa_alvo > 5) {
            throw new IllegalArgumentException("Caixa alvo inválida");
        }    
        if (indice < 1 || indice > sistema.getBaralho().getCaixas()[caixa-1].getQtdFlashcards()) {
            throw new IllegalArgumentException("Índice inválido ou caixa vazia");
        }
        setCaixa(caixa-1);
        setIndice(indice-1);
        setCaixaAlvo(caixa_alvo-1);
        sistema.getBaralho().moverFlashcard(caixa-1, indice-1, caixa_alvo-1, 2);
        j_gerenciar.atualizarTabela();
    }
}
