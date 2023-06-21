package Model;
import java.util.ArrayList;

public class Caixa {
    // Atributos -> implementação
    private int numero;
    private ArrayList<Flashcard> sub_baralho;
    private int qtd_flashcards;

    // Construtor
    public Caixa(int numero) {
        sub_baralho = new ArrayList<>();
        this.qtd_flashcards = 0;
        this.numero = numero;
    }

    //------------------------------------------
    //  <<Interface>>

    /* Este numero da caixa é a partir de 1, não de 0 */
    public int getNumero() {
        return this.numero;
    }

    public int getQtdFlashcards() {
        return this.qtd_flashcards;
    }

    public void setQtdFlashcards(int num) {
        qtd_flashcards = num;
    }

    public ArrayList<Flashcard> getSubBaralho() {
        return this.sub_baralho;
    }

    /* O parametro informado deve ser a partir de 0 */
    public Flashcard getFlashcard(int num) {
        return this.sub_baralho.get(num);
    }
    
    public void adicionarFlashcard(String pergunta, String resposta, int acerto, int id) {
        Flashcard flashcard = new Flashcard(pergunta, resposta, id);
        flashcard.setIndice(sub_baralho.size());
        if(acerto != 2) {
            flashcard.setAcerto(acerto);
        }
        sub_baralho.add(flashcard);
        atualizarQtdSub_Baralho(false);
    }

    public void removerFlashcard(int indice) {
        if(qtd_flashcards == 0) {
            throw new IllegalArgumentException("Caixa está vazia");
        }
        try {
            sub_baralho.remove(indice);
            atualizarIndices();
            atualizarQtdSub_Baralho(true);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Índice inexistente.");
        }
    }  
    
    /* Método utilizado no processo de importação de um flashcard em arquivos exportados */
    public void lerFlashcard(String pergunta, String resposta, int indice, int id) {
        Flashcard flashcard = new Flashcard(pergunta, resposta, id);
        flashcard.setIndice(indice);
        sub_baralho.add(flashcard);
        atualizarQtdSub_Baralho(false);
    }
    //------------------------------------------
    //  <<Implementação>>
    private void atualizarIndices() {
        for (int i = 0; i < sub_baralho.size(); i++) {
            Flashcard flashcard = sub_baralho.get(i);
            flashcard.setIndice(i);
        }
    }

    private void atualizarQtdSub_Baralho(boolean remover) {
        if(remover == true) {
            this.qtd_flashcards -= 1;
        } else {
            this.qtd_flashcards += 1;
        }
    }
}   