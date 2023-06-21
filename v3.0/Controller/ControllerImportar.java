package Controller;

public class ControllerImportar {
    // Atributos -> implementação
    private static String diretorio;
    private static boolean cancelar = false;

    //-----------------------------------------------------------------
    //  <<interface>>
    public static boolean getCancelar() {
        return cancelar;
    }

    public static void setCancelar(boolean can) {
        ControllerImportar.cancelar = can;
    }

    public static String getDiretorio() {
        return diretorio;
    }

    public static void setDiretorio(String dir) {
        ControllerImportar.diretorio = dir;
    }
}
