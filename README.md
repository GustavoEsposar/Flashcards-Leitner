# **1. Introdução** 

## **1.1 Contexto** 

 Para o desenvolvimento desta aplicação, 2 conceitos alicerces precisam ser compreendidos antes de qualquer tomada de decisão arquitetural, sendo eles o entendimento do que são Flashcards e o funcionamento do sistema Leitner de aprendizagem.
 Flashcards são muito utilizados em diferentes métodos de estudo, memorização e jogos. Simplificando, são pequenos cartões em que são utilizados ambos os lados, na frente tem-se a pergunta e no verso a respectiva resposta esperada.

 Já o Sistema Leitner de aprendizagem é uma metodologia que se utiliza dos flashcards para reduzir o tempo de estudo. Sua lógica é feita de tal maneira que dentro de uma rotina de estudos, os flashcards com perguntas mais difíceis, de menor conhecimento ou maior dificuldade de memorização sejam revistas mais vezes do que as perguntas fáceis ou que já são de maior conhecimento do indivíduo. Para tal, utiliza-se caixas enumeradas, correspondentes ao nível de domínio da questão. Quando uma questão é acertada, ela então passa para o próximo nível, quando é errada, independente do nível que estiver, volta para a caixa de nível 1. O diferencial está no tempo em que cada nível deve ser revisado, sendo que quanto maior, mais demorará para rever as questões da caixa, quanto menor, mais frequentemente deve ser revisto para intensificar a memorização. O Sistema Leitner padrão utiliza 5 caixas como níveis de proficiência.

## **1.2 Problema** 

 Baseando-se nos 2 conceitos apresentados, esta aplicação deve suportar a ideia de flashcards com alguns requisitos visados, sendo eles:

 1. carregar um conjunto contendo 10 flashcards, pelo menos; 
 2. virar um flashcard a cada solicitação do usuário; 
 3. passar para outro flashcard a pedido do usuário; 
 4. sortear um flashcard, quando o usuário desejar; 
 5. mostrar quantos flashcards o usuário corretamente se lembrou;
 6. suportar a inserção e remoção de flashcards; 
 7. ser executado em desktops e celulares;
 8. permitir que um usuário troque flashcards com outro usuário; 
 9. a aplicação deverá “animar” um flashcard sempre que ele for virado;
 10. basear-se na proposta de Leitner quando do sorteio de flashcards.

# **2. Solução Lógica** 

Os tópicos estão organizados de acordo com a ordem de desenvolvimento por parte da equipe em alcançar uma solução lógica.

## **2.1 Propósito** 

 Esta vista lógica do problema tem por objetivo desenvolver conceitos e fundamentos de arquitetura de software e elaborar uma aplicação funcional de flashcards, aplicável para estudos com base no Sistema Leitner de aprendizagem, visando satisfazer o máximo de requisitos propostos e sem restrições com relação aos conteúdo e temas abordados pelos flashcards, cabendo este dever ao usuário da aplicação (implementação parcial do Sistema Leitner).

## **2.2 ICA** 

 As figuras 1 e 2 referênciam a visualização do modelo criado [*(link para a correspondente plataforma de desenvolvimento)*](https://lucid.app/lucidchart/1f8ecf87-4cc4-4d9e-9e69-07acf79f4c33/edit?viewport_loc=-5297%2C-952%2C9787%2C4537%2C0_0&invitationId=inv_31681b96-f09f-4455-801e-0881cdba1649).

![ICA gerenciador](./figuras/ica_gerenciador.png) 

![ICA flashcards](./figuras/ica_flashcards.png)

## **2.3 UML - Diagrama de Estados** 

 As figuras 3, 4, 5 e 6 referênciam o diagrama de transição de estados desenvolvido [*(link para a correspondente plataforma de desenvovimento)*](https://pucsp-my.sharepoint.com/:u:/r/personal/ra00297810_pucsp_edu_br/Documents/diagramaUML.vsdx?d=wa516c5e6f8ae4dd68424e44d7f148946&csf=1&web=1&e=rZepdv).

![Sumário das Referências](./figuras/sumario.png)

![Diagrama principal](./figuras/DiagramaUML_principal.png)

![Diagrama gerenciar](./figuras/DiagramaUML_gerenciar.png)

![Diagrama praticar](./figuras/DiagramaUML_praticar.png)

## **2.4 Análise de Domínio** 

- **Usuários:** Indivíduos que necessitam de um método de memorização para estudo, não havendo restrição de tema ou conteúdo. Há restição apenas à idade do público álvo, já que os conceitos que fundamentam a aplicação, quando aplicados, requerem um nível de maturidade intelectual para uso individualizado, portanto, idade maior ou igual a *10 anos*.

- **Requisitos funcionais:**
 1. **(1)** carregar um conjunto contendo 10 flashcards, pelo menos; 
 2. **(2)** virar um flashcard a cada solicitação do usuário; 
 3. **(3)** passar para outro flashcard a pedido do usuário; 
 4. **(4)** sortear um flashcard, quando o usuário desejar; 
 5. **(5)** mostrar quantos flashcards o usuário corretamente se lembrou;
 6. **(6)** suportar a inserção e remoção de flashcards; 
 8. **(8)** permitir que um usuário troque flashcards com outro usuário; 
 9. **(9)** a aplicação deverá “animar” um flashcard sempre que ele for virado;
 10. **(10)** basear-se na proposta de Leitner quando do sorteio de flashcards.

- **Requisitos não-funcionais:**
 7. ser executado em desktops e celulares;

- **Justificativas:**
 1. (10) - **Implementação parcial**, o sistema Leitner deve estar associado com um controle na rotina de estudos e de temas do baralho, o controle da rotina de estudos foi imlementado porém a funcionalidade de gerenciamento do baralho por temas não, cabendo ao usuário segregar por temas.
 2. (7) - **Não implementado**,  a equipe optou apenas pelo desenvolvimento de uma aplicação Desktop. Assumir o compromisso de desenvolvimento mobile implica na elaboração e documentação de outra vista lógica, ferramentas e seus próprios desafios, causando uma preocupação coletiva em elaborar, dentro do prazo de desenvolvimento, um modelo lógico consistente, bem estruturado e documentado, abordando o domínio de requisitos da aplicação. Fatores como a inexperiência do grupo no âmbito da arquitetura de software e pouco contato com plataformas e linguagens de desenvolvimento mobile intensificam o problema, o que pode acarretar num desenvolvimento pouco proveitoso e de baixo aprendizado na disciplina de Arquitetura de Software. 

## **2.5 Cenários de Uso** 

-  **2.3.1 Cenário: Adicionar flashcard ao baralho** 

    **Usuário:** Estudante

    **Ações:**
    1. Iniciar aplicação.
    2. Janela principal é inicializada.
    3. Clica no botão para a janela de gerenciamento.
    4. Clicar no botão de criar um novo flashcard.
    5. Janela para popular os atributos do flashcard é intânciada.
    6. Após preenchida, clicar no botão para salvar o flashcard.

    **Entradas:**
    - Atributos do flashcard (frente e verso).

    **Saídas:**
    - Mensagem de erro (caso os atributos sejam inválidos).
    - Aviso de sucesso de conclusão.

    **Requisitos funcionais identificados:**
    - A aplicação deve permitir adição de flashcards ao baralho (já existente ou não)
    - A aplicação deve permitir o preenchimento dos atributos do flashcard pelo usuário.
    - A aplicação deve permitir o usuário desistir do processo de adição do flashcard.

-  **2.3.2 Cenário: Remoção de um flashcard do baralho**  

    **Usuário:** Estudante

    **Ações:**
    1. Iniciar aplicação.
    2. Janela principal é inicializada.
    3. Clicar no botão para janela de gerenciamento.
    4. Clicar no botão de deletar flashcard.
    5. Janela para identificação de qual flashcard deseja-se remover.
    6. Confirmação da ação de deletar.

    **Entradas:**
    - Índice do flahscard que deve ser removido.

    **Saídas:**
    - Emissão de mensagem de erro caso o índice fornecido seja inválido.
    - Aviso de sucesso do processo.

    **Requisitos funcionais identificados:**
    - A aplicação deve suportar um sistema de associação de índices aos flashcards para processos de seleção.
    - A aplicação deve suportar um sistema de visualização dos flashcards existentes para possibilitar a seleção e conseguinte exclusão.
    - A aplicação deve permitir o usuário desistir do processo de exclusão do flashcard.

-  **2.3.3Cenário: Salvar arquivo com o estado do baralho atual**  

    **Usuário:** Estudante

    **Ações:**
    1. Iniciar aplicação
    2. Janela principal é inicializada.
    3. Clicar no botão para janela de gerenciamento.
    4. Clicar no botão para salvar o baralho atual.
    5. Janela para usuário indicar local e nome do arquivo gerado.
    6. confirmação das escolhas.

    **Entradas:**
    - Nome do arquivo e local de salvamento.

    **Saídas:**
    - Mensagem de erro caso não haja flashcards no baralho.
    - Mensagem de erro caso o nome do arquivo ou local seja inválido.
    - Aviso de conclusão do processo.

    **Requisitos funcionais identificados:**
    - A aplicação deve ser capaz de salvar o baralho atual.
    - O arquivo exportado deve ter apenas atributos relacionados ao baralho (índice, frente e verso).
    - Percebe-se a necessidade da existência de 2 tipos de arquivos salvos, os dados de exportção e os dados relativos à seção de estudo do usuário que os exportou.
    - É um absurdo exportar um baralho composto por 0 flashcards.
    - Devido aos requisitos da aplicação, baralhos com valor < 10 cartões não serão considerados flashcards.
    - A aplicação deve permitir a desistência do processo.

-  **2.3.4 Cenário: Carregar arquivo de um baralho exportado** 

    **Usuários:** Estudante

    **Ações:**
    1. Iniciar aplicação.
    2. Janela principal é inicializada.
    3. Clicar no botão para janela de gerenciamento.
    4. Clicar no botão para carregar arquivo de baralho.
    5. Janela para usuário indicar arquivo a ser carregado.
    6. Confirmação do arquivo selecionado.
    7. Confirmação se deseja continaur com o processo que irá sobreescrever os estadodo baralho.

    **Entradas:**
    - Arquivo com dados do baralho.

    **Saídas:**
    - Mensagem de erro caso arquivo selecionado seja inválido.
    - Aviso de conclusão do processo de carregamento.
    - A aplicação deve permitir a desistência do processo.

    **Requisitos funcionais identificados:**
    - É um absurdo o arquivo de exportação conter informações relativas a um usuário em específico, portanto deve haver 2 formatos de exportação.
    - A aplicação deve permitir carregar arquivos de um determinado formato de forma a sobrescrever o estado do baralho atual.
    - A aplicação deve permitir a desistência do processo.

-  **2.3.5 Cenário: Iniciar sessão prática de estudos** 

    **Usuários:** Estudante

    **Ações:**
    1. Iniciar aplicação.
    2. Janela principal é inicializada.
    3. Clicar no botão para janela de prática.
    4. Janela de seleção do nível de estudo (Sistema Leitner).
    5. Confirmação de escolha do nível.
    6. Janela de seção exibe flashcard inicial (frente).

    **Entradas:**
    - Nível de estudo desejado.

    **Saídas:**
    - Aviso caso o usuário clique no botão e não haja um baralho adicionado em memória.

    **Requisitos funcionais identificados:**
    - A aplicação só deve permitir entrar na janela de prática se houver um baralho na memória (é um absurdo iniciar seção sem um baralho).
    - É um absurdo iniciar seção de estudos com apenas um flashcard no baralho (Nº de flashcards do baralho > 1).
    - A partir da escolha do nível, todas as caixas com valor menor ou igual ao selecionado entrarão na seção de estudo.
    - A escolha do flashcard inicial ocorre através de um sorteio.
    - A aplicação deve permitir a desistência do inicio da seção na etapa de escolha de nível do estudo.
    - A aplicação deve permitir o encerramento da seção de estudos mesmo sem responder flashcard algum.

-  **2.3.6 Cenário: Responder flashcard na seção de estudo** 

    **Usuários:** Estudante

    **Ações:**
    1. Iniciar aplicação
    2. Janela principal é inicializada.
    3. Clicar no botão para janela prática.
    4. Janela de seleção do nivel de estudo (Sistema Leitner).
    5. Confirmação de escolha do nível.
    6. Janela de seção exibe flashcard inicial.
    7. Clique no botão virar flashcard.
    8. Indicação de acerto ou erro da resposta.

    **Entradas:**
    - Seleção do nível de estudo.
    - Acerto ou erro associado a um flashcard do baralho.

    **Saídas:** Não há.

    **Requisitos funcionais identificados:**
    - A aplicação deve suportar a ação virar flashcard para exibir o verso (resposta).
    - A ação virar é quem possibilita indicar acerto ou erro da pergunta (frente).
    - Todas as outras ações, exceto virar o flashcard, devem estar indisponíveis até ocorrer uma indicação de acerto ou erro da pergunta.
    - A aplicação deve associar a acada flashcard armazenando o atributo de acerto/erro.
    - A indicação de acerto ou erro feita pelo usuário não o obriga a passar
    para outro flashcard, o usuário pode virar e desvirar quantas vezes desejar.
    - A aplicação não deve permitir que o usuário altere sua resposta depois de indicada.
    - A aplicação deve permitir o encerramento da seção de estudos após responder o flashcard.

-  **2.3.7 Cenário: Sortear flashcard do baralho** 

    **Usuários:** Estudante

    **Ações:**
    1. Iniciar aplicação
    2. Janela principal é inicializada.
    3. Clicar no botão para janela prática.
    4. Janela de seleção do nivel de estudo (Sistema Leitner).
    5. Confirmação de escolha do nível.
    6. Janela de seção exibe flashcard inicial.
    7. Clique no botão virar flashcard.
    8. Indicação de acerto ou erro da resposta.
    9. Clicar no botão de sorteio.

    **Entradas:**
    - Seleção do nível de estudo.
    - Acerto ou erro associado a um flashcard do baralho.

    **Saídas:** Não há.

    **Requisitos funcionais identificados:**
    - A aplicação deve suportar a aplicação de um algoritmo de sorteio de forma que possa ser aplicado em diferentes contextos de uso do baralho.
    - O processo gerado pela ação sortear não deve repetir flashcards já sorteados anteriormente.
    - Cada flashcard está associado a uma caixa (pertinência).
    - A aplicação deve tornar indisponível a ação de sorteio quando todos os flashcards do baralho forem selecionados na seção atual.

-  **2.3.8 Cenário: Passar flashcard do baralho** 

    **Usuários:** Estudante

    **Ações:**
    1. Iniciar aplicação
    2. Janela principal é inicializada.
    3. Clicar no botão para janela prática.
    4. Janela de seleção do nivel de estudo (Sistema Leitner).
    5. Confirmação de escolha do nível.
    6. Janela de seção exibe flashcard inicial.
    7. Clique no botão virar flashcard.
    8. Indicação de acerto ou erro da resposta.
    9. Clicar no botão de Passar flashcard

    **Entradas:**
    - Seleção do nível de estudo.
    - Acerto ou erro associado a um flashcard do baralho.

    **Saídas:**
    - Alerta de todos já foram acessados caso o baralho tenha sido percorrido

    **Requisitos funcionais identificados:**
    - A aplicação através da ação passar deve atualizar o status de resposta como ignorada.
    - Para acessar outro flashcar, a ação passar utilizará o método de sorteio.
    - A aplicação deve tornar indisponível a ação passar caso todos os flashcards do baralho já tenham sido selecionados na seção atual.

-  **2.3.9 Cenário: Finalizar seção prática de estudos** 

    **Usuários:** Estudante

    **Ações:**
    1. Iniciar aplicação
    2. Janela principal é inicializada.
    3. Clicar no botão para janela prática.
    4. Janela de seleção do nivel de estudo (Sistema Leitner).
    5. Confirmação de escolha do nível.
    6. Janela de seção exibe flashcard inicial.
    7. Clique no botão virar flashcard.
    8. Indicação de acerto ou erro da resposta.
    9. Clicar no botão de encerramento da seção.
    10. Confirmação do fim da seção.
    11. Janela de indicação dos resultados da seção de estudos.
    12. Clicar no botão de término de leitura dos resultados.

    **Entradas:**
    - Seleção do nível de estudo.
    - Acerto ou erro associado a um flashcard do baralho.

    **Saídas:** Não há.

    **Requisitos funcionais identificados:**
    - A aplicação deve suportar um contador de acertos associado à seção atual de estudos.
    - Também deve contar a quantidade de erros e passados.

## **2.6 UML - Diagrama de Classes**

Para o desenvolvimento, utilizamos combinações de conceitos base de arquitetura de software. A arquitetura desenvolvida, visando abranger os requisitos da aplicação, é um resultado da combinação de arquiteturas base, sendo elas arquitetura em camadas, orientada a objetos e Model-View-Controller.

Como consequência, obtivemos 3 módulos funcionais e segregados nomeados Model, View e Controller, visando serem auto-explicativas as classes gerenciadas por cada um.

A figura 7 referencía o diagrama de classes simplificado. As figuras 8, 9 e 10 por sua vez representam cada um dos módulos elaborados na arquitetura mais detalhadamente.

![Diagrama simplificado](./figuras/classes_total.png)

![Diagrama do módulo `<<Model>>`](./figuras/classes_model.png)

![Diagrama do módulo `<<View>>`](./figuras/classes_view.png)

![Diagrama do módulo `<<Controller>>`](./figuras/classes_controller.png)
