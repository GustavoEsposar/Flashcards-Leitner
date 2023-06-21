package Controller;

import Model.SistemaLeitner;

public class ControllerRemover {
    // Atributos -> implemnetação
    private static SistemaLeitner sistema;

    //-----------------------------------------------------------------
    //  <<interface>>
    public static void setSistema(SistemaLeitner sistema) {
        ControllerRemover.sistema = sistema;
    }

    public static void removerFlashcard(int caixa, int indice) {
        if (sistema != null) {
            sistema.getBaralho().removerFlashcard(caixa, indice);
        } else {
            throw new IllegalStateException("Sistema não configurado");
        }
    }
}
