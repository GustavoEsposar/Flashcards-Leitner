package Controller;

import View.JanelaExportar;

public class ControllerExportar {
    // Atributos -> implementação
    private static boolean cancelar = false;
    private static String diretorio;
    private static boolean compartilhar;

    //-----------------------------------------------------------------
    //  <<interface>>
    public static void exportarArquivo() {
        JanelaExportar janelaExportar = new JanelaExportar();
        janelaExportar.setVisible(true);
    }

    public static boolean getCancelar() {
        return cancelar;
    }

    public static void setCancelar(boolean can) {
        ControllerExportar.cancelar = can;
    }

    public static String getDiretorio() {
        return diretorio;
    }

    public static void setDiretorio(String dir) {
        ControllerExportar.diretorio = dir;
    }

    public static boolean getCompartilhar() {
        return compartilhar;
    }

    public static void setCompartilhar(boolean comp) {
        ControllerExportar.compartilhar = comp;
    }
}
