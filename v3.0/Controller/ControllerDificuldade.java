package Controller;

import javax.swing.JOptionPane;

import Model.Flashcard;
import Model.SistemaLeitner;
import View.*;

public class ControllerDificuldade {
    // Atributos -> imlementação
    private static int nivel;
    private static int ultimaPontuacao;
    private static int nivelRecomendado;
    private static String ultimoAcesso;
    private static SistemaLeitner sistema;
    private static JanelaPrincipal j_principal;

    //-----------------------------------------------------------------
    //  <<interface>>
    public static void iniciar() {
        nivel = 0;
        JanelaDificuldade j_dificuldade = new JanelaDificuldade();
        atualizarDados();
        j_dificuldade.setVisible(true);
    }

    public static void setJanelaPai(JanelaPrincipal janela) {
        ControllerDificuldade.j_principal = janela;
    }

    public static JanelaPrincipal getJanelaPai() {
        return j_principal;
    }

    public static void setSistema(SistemaLeitner sistema) {
        ControllerDificuldade.sistema = sistema;
    }

    public static SistemaLeitner getSistema() {
        return sistema;
    }

    public static void setNivel(int nivel) {
        ControllerDificuldade.nivel = nivel;
    }

    public static int getNivel() {
        return nivel;
    }

    public static void setUltimaPontuacao(int pontuacao) {
        ultimaPontuacao = pontuacao;
    }

    public static int getUltimaPontuacao() {
        return ultimaPontuacao;
    }

    public static void setNivelRecomendado(int nivel) {
        nivelRecomendado = nivel;
    }

    public static int getNivelRecomendado() {
        return nivelRecomendado;
    }

    public static void setUltimoAcesso(String acesso) {
        ultimoAcesso = acesso;
    }

    public static String getUltimoAcesso() {
        return ultimoAcesso;
    }

    public static void iniciarSessao(JanelaPrincipal pai) {
        try {
            ControllerSessao.setJanelaPai(pai);
            Flashcard flash = sistema.getBaralho().sortearFlashcard(nivel);
            ControllerSessao.iniciar(flash, nivel);
            JanelaSessao j_sessao = new JanelaSessao();
            j_sessao.setVisible(true);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void atualizarDados() {
        ultimaPontuacao = sistema.getUltimaPontuacao();
        nivelRecomendado = sistema.recomendarDificuldade();
        ultimoAcesso = sistema.getUltimaData();
    }
}
