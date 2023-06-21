package Controller;

import Model.Flashcard;
import Model.SistemaLeitner;
import View.JanelaPrincipal;

public class ControllerSessao {
    //Atributos -> implementação
    private static SistemaLeitner sistema;
    private static JanelaPrincipal j_principal;
    private static int qtd_acertos;
    private static int qtd_erros;
    private static int qtd_caixas;
    private static boolean virou;
    private static boolean clicou;
    private static boolean todos_sorteados;
    private static int contador;
    private static Flashcard f_atual;
    private static int caixa_atual;
    private static Flashcard f_primeiro;
    private static String total;

    //-----------------------------------------------------------------
    //  <<interface>>
    public static void iniciar(Flashcard sorteado, int nivel) {
        sistema.setData();
        qtd_caixas = nivel;
        f_primeiro = sorteado;
        f_atual = f_primeiro;
        contador = 1;
        qtd_acertos = 0;
        qtd_erros = 0;
        clicou = false;
        virou = false;
        todos_sorteados = false;
        total = Integer.toString(sistema.getBaralho().getSomaQtd(qtd_caixas));
    }

    public static String getTotal() {
        return total;
    }

    public static boolean getTodosSorteados() {
        return todos_sorteados;
    }

    public static void setClicou(boolean clicou) {
        ControllerSessao.clicou = clicou;
    }

    public static boolean getClicou() {
        return clicou;
    }

    public static void setCaixaAtual(int caixa) {
        ControllerSessao.caixa_atual = caixa;
    }

    public static void setJanelaPai(JanelaPrincipal j_principal) {
        ControllerSessao.j_principal = j_principal;
    }

    public static JanelaPrincipal getJanelaPai() {
        return j_principal;
    }

    public static void setSistema(SistemaLeitner sis) {
        ControllerSessao.sistema = sis;
    }

    public static SistemaLeitner getSistema() {
        return sistema;
    }

    public static int getQtdAcertos() {
        return qtd_acertos;
    }

    public static int getQtdCaixas() {
        return qtd_caixas;
    }

    public static int getContador() {
        return contador;
    }

    public static void setvirou(boolean value) {
        virou = value ? true : false;
    }

    public static boolean getVirou() {
        return virou;
    }
    
    public static Flashcard getAtual() {
        return f_atual;
    }

    public static void incrementarAcertos() {
        qtd_acertos++;
    }

    public static void incrementarErros() {
        qtd_erros++;
    }

    public static int getQtdErros() {
        return qtd_erros;
    }

    public static void sortearFlashcard() {
        if(contador == sistema.getBaralho().getQtdFlashcards() || contador == Integer.parseInt(total)) {
            virou = false;
            throw new IllegalArgumentException("Todos os flashcards já foram acessados.");
        }
        if(contador < sistema.getBaralho().getQtdFlashcards()) {
            
            
            if(contador == Integer.parseInt(total)) {
                virou = false;
                todos_sorteados = true;
            }else {
                contador++;
                
                do {
                    f_atual = sistema.getBaralho().sortearFlashcard(qtd_caixas);
                } while (f_atual == null);
    
                virou = false;
            }

        }
    }

    public static void avancarFlashcard() {
        int indice = f_atual.getIndice();
        int caixa_alvo = caixa_atual;

        if(caixa_atual < 4) {
            caixa_alvo++;
        }

        sistema.getBaralho().moverFlashcard(caixa_atual, indice, caixa_alvo, 1);
    }

    public static void retrocederFlashcard() {

        sistema.getBaralho().moverFlashcard(caixa_atual, f_atual.getIndice(), 0, -1);
    }

    public static void passarFlashcard() {
        f_atual.setAcerto(0);
        sortearFlashcard();
    }

}