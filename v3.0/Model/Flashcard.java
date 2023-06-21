package Model;
public class Flashcard {
    // Atributos -> implementação
    private String pergunta;
    private String resposta;
    private int acerto;
    private int id;
    private int indice;

    // Construtor
    public Flashcard(String pergunta, String resposta, int id) {
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.id = id;
        this.acerto = 0;
    }

    //---------------------------------------------------------
    //  <<Interface>>
    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public int getAcerto() {
        return acerto;
    }

    public int getIndice() {
        return indice;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public void setAcerto(int resposta) {
        this.acerto = resposta;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getId() {
        return this.id;
    }
}
