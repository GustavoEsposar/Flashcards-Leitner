package Controller;

import Model.SistemaLeitner;
import View.JanelaPontuacao;

public class ControllerPontuacao {
    //Atributos -> implementação
    private static SistemaLeitner sistema;
    private static JanelaPontuacao j_pont;
    private static int total;
    private static int acertos;
    private static int erros;
    private static int passados;
    
    //-----------------------------------------------------------------
    //  <<interface>>
    public static void iniciarJanela() {
        total = Integer.parseInt(ControllerSessao.getTotal());
        acertos = ControllerSessao.getQtdAcertos();
        erros = ControllerSessao.getQtdErros();
        passados = calcularPassados();
        sistema.setPontuacao(acertos);
        sistema.getBaralho().resetSorteados();
        j_pont = new JanelaPontuacao();
        j_pont.setVisible(true);
    }

    public static void setSistema(SistemaLeitner sistema) {
        ControllerPontuacao.sistema = sistema;
    }

    public static int getAcertos() {
        return acertos;
    }

    public static int getErros() {
        return erros;
    }

    public static int getPassados() {
        return passados;
    }

    public static int getTotal() {
        return total;
    }

    private static int calcularPassados() {
        return total - acertos - erros;
    }
}
