package Model;
import java.util.ArrayList;
import java.util.Random;

import Controller.ControllerSessao;

public class Baralho {
    // Atributos
    private Caixa[] caixas;
    private int qtd_total_flashcards;
    private ArrayList<Integer> flash_sorteados;
    private int contador_id;

    // Construtor
    public Baralho() {
        this.qtd_total_flashcards = 0;
        contador_id = 0;
        this.flash_sorteados = new ArrayList<>();

        caixas = new Caixa[5];
        for (int i = 0; i < caixas.length; i++) {
            caixas[i] = new Caixa(i + 1);
        }
    }

    //------------------------------------------
    //  <<Interface>>

    public int prox_id() {
        return ++contador_id;
    }

    public void resetSorteados() {
        this.flash_sorteados.clear();
    }

    public int getQtdFlashcards() {
        return this.qtd_total_flashcards;
    }

    /* Retorna a doma de flahscards até uma seleção de caixas */
    public int getSomaQtd(int nivel) {
        int soma = 0;
        for(int i = 0; i < nivel ;i++) {
            soma += caixas[i].getQtdFlashcards();
        }
        return soma;
    }

    public Caixa[] getCaixas() {
        return this.caixas;
    }
    
    public void atualizarQtdFlashcards() {
        int total = 0;
        for (Caixa caixa : caixas) {
            total += caixa.getQtdFlashcards();
        }
        this.qtd_total_flashcards = total;
    }
    
    public void adicionarFlashcard(String pergunta, String resposta, int acerto, int id) {
        caixas[0].adicionarFlashcard(pergunta, resposta, acerto, prox_id());
        atualizarQtdFlashcards();
    }
    
    public void removerFlashcard(int caixa, int indice) {
        if (caixa < 0 || caixa > caixas.length) {
            throw new IllegalArgumentException("Caixa inválida");
        }
    
        if (indice < 0 || indice > caixas[caixa].getQtdFlashcards()) {
            throw new IllegalArgumentException("Índice inválido");
        }

        caixas[caixa].removerFlashcard(indice);
        atualizarQtdFlashcards();
    }

    public void moverFlashcard(int caixa, int indice, int caixa_alvo, int acerto) {
        String pergunta = caixas[caixa].getFlashcard(indice).getPergunta();
        String resposta = caixas[caixa].getFlashcard(indice).getResposta();
        int id = caixas[caixa].getFlashcard(indice).getId();

        caixas[caixa_alvo].adicionarFlashcard(pergunta, resposta, acerto, id);
        caixas[caixa].removerFlashcard(indice);
    }

    public Flashcard sortearFlashcard(int qtd_caixas) {
        int selecionadas = qtd_caixas;
        Caixa caixa_selecionada;
        ArrayList<Integer> caixas_nao_vazias = new ArrayList<>();

        for(int i = 0; i < selecionadas; i++) {
            caixa_selecionada = caixas[i];
            if(caixa_selecionada.getQtdFlashcards() != 0) {
                caixas_nao_vazias.add(i);
            }
        }
        
        if (caixas_nao_vazias.isEmpty()) {
            throw new RuntimeException("Não há flashcards até esta caixa.\n Parabéns, está aprendendo bem!");
        }
        else {
            Random random = new Random();
            int cont = 0;
            Flashcard flash;
            boolean encontrou;
        
            do {
                encontrou = false;

                int indice_sorteado = random.nextInt(caixas_nao_vazias.size());
                indice_sorteado = caixas_nao_vazias.get(indice_sorteado);
                caixa_selecionada = caixas[indice_sorteado];
                cont++;
                int total_flashcards = caixa_selecionada.getQtdFlashcards();
                ControllerSessao.setCaixaAtual(indice_sorteado);
        
                int indice_flashcard_sorteado;
                int contador = 0;
        
                do {
                    indice_flashcard_sorteado = random.nextInt(total_flashcards);
                    contador++;
                    flash = caixa_selecionada.getFlashcard(indice_flashcard_sorteado);
                    //System.out.println("flash: " + flash.getPergunta() + "ID: "+ flash.getId() + " sorteados: " + flash_sorteados);

                    if(!flash_sorteados.contains(flash.getId())) {
                        encontrou = true;
                    }

                } while (!encontrou && contador <= total_flashcards);             
        
                if (encontrou && contador <= total_flashcards) {
                    flash_sorteados.add(flash.getId());
                    return flash;
                }
            } while(cont <= caixas_nao_vazias.size() && !encontrou);
            
            return null;
        }
    }

    public void esvaziarBaralho() {
        this.contador_id = 0;
        for (Caixa caixa : caixas) {
            caixa.getSubBaralho().clear();
            caixa.setQtdFlashcards(0);;
        }
        qtd_total_flashcards = 0;
    }
}