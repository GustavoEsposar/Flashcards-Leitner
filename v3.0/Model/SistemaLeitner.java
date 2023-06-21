package Model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SistemaLeitner {
    // Atributos
    private Baralho baralho;
    private String ultima_data;
    private int ultima_pontuacao;
    private int ultima_dificuldade;

    // Construtor
    public SistemaLeitner() {
        baralho = new Baralho();
        this.ultima_data = "";
        this.ultima_pontuacao = 0;
        this.ultima_dificuldade = 0;
        setData();
    }

    //------------------------------------------
    //  <<Interface>>
    public void setPontuacao(int num) {
        this.ultima_pontuacao = num;
    }

    public int getUltimaPontuacao() {
        return this.ultima_pontuacao;
    }

    public void setDificuldade(int dif) {
        this.ultima_dificuldade = dif;
    }

    public void setData() {
        // Obtem a data atual do sistema
        LocalDate dataAtual = LocalDate.now();

        // Define o formato desejado para a data
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formata a data atual de acordo com o formato desejado
        String dataFormatada = dataAtual.format(formatoData);

        // Armazena a data formatada na variavel ultima_data
        this.ultima_data = dataFormatada;
    }

    public String getUltimaData() {
        return this.ultima_data;
    }

    public Baralho getBaralho() {
        return this.baralho;
    }

    public int recomendarDificuldade() {
        int saida;
        saida = this.ultima_dificuldade + 1;
        if (this.ultima_dificuldade > 4) {
            saida = 1;
        }
        return saida;
    }
    
    public void exportarBaralho(String nome, boolean compartilhar) {
        String nomeArquivo = nome + (compartilhar ? ".fexc" : ".fex");
    
        if (this.baralho.getQtdFlashcards() == 0) {
            throw new IllegalStateException("Não há flashcards para exportar.");
        }
    
        try (Writer escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            if (!compartilhar) {
                if (nomeArquivo.endsWith(".fex")) {
                    escritor.write(this.ultima_pontuacao + ";" + this.ultima_dificuldade);
                    escritor.write(";" + this.ultima_data);
                }
                escritor.write(System.lineSeparator());
            }
    
            for (Caixa caixa : baralho.getCaixas()) {
                for (int i = 0; i < caixa.getQtdFlashcards(); i++) {
                    Flashcard flashcard = caixa.getFlashcard(i);
                    String linha = formatarLinhaFlashcard(caixa, flashcard, compartilhar);
                    escritor.write(linha);
                    escritor.write(System.lineSeparator());
                }
            }
            escritor.write("EOF");
        } catch (IOException e) {
            //e.printStackTrace();
            throw new IllegalStateException("Erro no processo de exportação.");
        }
    }
    
    
    
    public void importarBaralho(String nomeArquivo) {
        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            String linha;
            String formatoArquivo = nomeArquivo.endsWith(".fexc") ? ".fexc" : ".fex";
            boolean encontrouEOF = false; // Verifica se o marcador "EOF" foi encontrado
    
            if (!formatoArquivo.equals(".fexc") && !formatoArquivo.equals(".fex")) {
                throw new IllegalArgumentException("Formato de arquivo inválido. Deve ser .fexc ou .fex.");
            }
    
            if (formatoArquivo.equals(".fexc")) {
                while ((linha = leitor.readLine()) != null && !linha.equals("EOF")) {
                    String[] partes = linha.split(";");
                    String pergunta = partes[0];
                    String resposta = partes[1];
                    baralho.getCaixas()[0].adicionarFlashcard(pergunta, resposta, 2, baralho.prox_id()); // Adiciona os flashcards na primeira caixa
                    baralho.atualizarQtdFlashcards();
                }
                if(linha.equals("EOF")) encontrouEOF = true;
            } else if (formatoArquivo.equals(".fex")) {
                linha = leitor.readLine();
                String[] info = linha.split(";");
                this.ultima_pontuacao = Integer.parseInt(info[0]);
                this.ultima_dificuldade = Integer.parseInt(info[1]);
                this.ultima_data = info[2];
    
                while ((linha = leitor.readLine()) != null && !linha.equals("EOF")) {
                    String[] partes = linha.split(";");
                    int caixa = Integer.parseInt(partes[0]);
                    int indice = Integer.parseInt(partes[1]);
                    String pergunta = partes[2];
                    String resposta = partes[3];
                    int acerto = Integer.parseInt(partes[4]);
                    baralho.getCaixas()[caixa - 1].lerFlashcard(pergunta, resposta, indice, baralho.prox_id());
                    baralho.getCaixas()[caixa - 1].getFlashcard(indice).setAcerto(acerto);
                    baralho.atualizarQtdFlashcards();
                }
                if(linha.equals("EOF")) encontrouEOF = true;
            }
    
            if (!encontrouEOF) {
                throw new IllegalStateException("Falha no processo. Não foi encontrado o marcador 'EOF'.");
            }
    
        } catch (IOException e) {
            throw new IllegalStateException("Erro no processo de importação.");
        }
    }    

    //------------------------------------------
    //  <<Implementação>>    
    private String formatarLinhaFlashcard(Caixa caixa, Flashcard flashcard, boolean compartilhar) {
        String nivel = String.valueOf(caixa.getNumero());
        String indice = String.valueOf(flashcard.getIndice());
        String pergunta = flashcard.getPergunta();
        String resposta = flashcard.getResposta();
        int acerto = flashcard.getAcerto();
    
        pergunta = pergunta.replaceAll("\"", "");
        resposta = resposta.replaceAll("\"", "");

        if(compartilhar == true) {
            return pergunta + ";" + resposta;
        } else {
            return nivel + ";" + indice + ";" + pergunta + ";" + resposta + ";" + acerto;
        }
    }

}