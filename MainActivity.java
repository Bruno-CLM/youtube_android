package com.example.agendiario;

/**
 *
 * Creative Commons (CC) 2019 Marcos Vinícius da Silva Santos and Marcos Antonio dos Santos
 *
 * Licensed under the Creative Commons, Version 4.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://creativecommons.org/licenses/by-nc-sa/4.0/
 *    https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 *    Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)
 *
 *    https://creativecommons.org/licenses/by-nc-sa/4.0/deed.pt_BR
 *    https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.pt
 *    Atribuição-NãoComercial-CompartilhaIgual 4.0 Internacional (CC BY-NC-SA 4.0)
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

/**
 Considerações Iniciais

 1 - Sobre Atividades

     Uma atividade representa uma única tela no seu aplicativo com a qual seu usuário pode executar
     uma tarefa única e focada, como discar o telefone, tirar uma foto, enviar um email, entrar
     com dados (por exemplo do museu) ou visualizar um mapa. As atividades geralmente são
     apresentadas ao usuário como janelas de tela cheia.

     Um aplicativo geralmente consiste em várias atividades que são fracamente ligadas entre
     si. Normalmente, uma atividade em um aplicativo é especificada como a atividade "principal",
     (MainActivity.java) que é apresentada ao usuário quando o aplicativo é iniciado. Cada
     atividade pode então iniciar outras atividades para executar ações diferentes.

     Sempre que uma nova atividade é iniciada, a atividade anterior é interrompida, mas o
     sistema preserva a atividade em uma pilha (a "pilha de retorno"). Quando uma nova atividade
     é iniciada, essa nova atividade é colocada na pilha de trás e leva o foco do usuário. A pilha
     reversa obedece ao mecanismo básico da pilha "último a entrar, primeiro a sair"; portanto,
     quando o usuário termina a atividade atual e pressiona o botão Voltar, essa atividade atual é
     removida da pilha (e destruída) e a atividade anterior retoma o foco.

     As atividades do Android são iniciadas ou ativadas com uma intenção. As intenções (Intents) são
     mensagens assíncronas que você pode usar em sua atividade para solicitar uma ação de outra
     atividade (ou outro componente do aplicativo). Você usa intenções para iniciar uma outra
     atividade e para passar dados entre as atividades.

     Existem dois tipos de intenções: explícita e implícita . Uma intenção explícita é aquela em
     que você conhece o destino dessa intenção, ou seja, você já sabe o nome completo da classe
     dessa atividade específica. Uma intenção implícita é aquela em que você não tem o nome do
     componente de destino, mas tem uma ação geral a executar.

     Aqui usaremos muito as intenções explícitas - ativando uma atividade específica em seu
     aplicativo ou em um aplicativo diferente enviando uma intenção com o nome completo da classe
     dessa atividade.

     As intenções implícitas permitem ativar uma atividade se você conhecer a ação, mas não o
     aplicativo ou atividade específica que manipulará essa ação. Por exemplo, se você deseja que
     seu aplicativo tire uma foto, envie um email ou exiba um local em um mapa, normalmente não se
     importa com o aplicativo ou atividade específica que realmente executa essas ações.

     Por outro lado, suas atividades podem declarar um ou mais filtros de intenção no manifesto
     do Android (AndroidManifest.xml) que anunciam a capacidade dessa atividade de aceitar
     intenções implícitas e definir o tipo específico de intenções que ela aceitará.

     Para corresponder sua solicitação a um aplicativo específico instalado no dispositivo, o
     sistema Android associa sua intenção implícita a uma atividade cujos filtros de intenção
     indicam que eles podem executar essa ação. Se houver vários aplicativos instalados
     correspondentes, o usuário receberá um seletor de aplicativos que permitirá selecionar o
     aplicativo que deseja usar para lidar com essa intenção.

 2 - Um pouco mais sobre o 'Ciclo de vida da atividade'

     O ciclo de vida da atividade é o conjunto de estados em que uma atividade pode estar durante
     toda a sua vida útil, desde o momento em que foi criada inicialmente até a destruição e o
     sistema recupera os recursos dessa atividade. À medida que um usuário navega entre as
     atividades em seu aplicativo (bem como dentro e fora do aplicativo), essas atividades passam
     a cada transição entre estados diferentes no ciclo de vida da atividade.

     Cada estágio do ciclo de vida de uma atividade possui um método de retorno de chamada
     correspondente (onCreate(), onStart(), onPause(), onResum(), onDestroy() e assim por diante).
     Quando uma atividade muda de estado, o método de retorno de chamada associado é chamado. Você
     já viu e usou um destes métodos: onCreate(). Ao substituir qualquer um dos métodos de retorno
     de chamada do ciclo de vida em suas classes de atividades, você pode alterar o comportamento
     padrão de como sua atividade se comporta em resposta a diferentes ações do usuário ou do
     sistema.

     Alterações no estado da atividade também podem ocorrer em resposta a alterações na
     configuração do dispositivo, como girar o dispositivo de retrato para paisagem. Essas
     mudanças na configuração resultam na atividade sendo destruída e totalmente recriada em seu
     estado padrão (original), o que pode causar a perda de informações que o usuário inseriu nessa
     atividade. É importante desenvolver seu aplicativo para evitar isso, para evitar confusão do
     usuário.

 3 - Sobre Layouts

     Quando você inicia um projeto do Android Studio, o modelo escolhido fornece um layout básico
     com a visualização de componente.

     É possível criar uma interface do usuário usando o ConstraintLayout no editor de layout, que
     coloca os elementos da interface do usuário em um layout usando conexões de restrição com
     outros elementos e com as bordas do layout. ConstraintLayout foi projetado para facilitar o
     arrastamento de elementos da interface do usuário para o editor de layout no Android Studio.

     ConstraintLayout é a ViewGroup, uma View especial que pode conter outros objetos do tipo View
     (chamados filhos ou visualizações filhos).

     Outras subclasses de ViewGroup:

           - LinearLayout: Um grupo que alinha na View elementos filho dentro dele horizontalmente
                           ou verticalmente.

           - RelativeLayout: Um grupo de View no qual elementos filho de cada View é
                             posicionado e alinhado em relação a outro elementdo da View dentro do
                             ViewGroup. As posições dos elementos filhos na View são descritas em
                             relação uma à outra ou ao pai da ViewGroup.

 4 - As 'views' (ou a interface do usuário - UI)

     A interface do usuário exibida na tela de um dispositivo Android móvel consiste em uma
     hierarquia de "visualizações". As visualizações são os componentes básicos da interface de
     usuário do Android. Você especifica as visualizações nos arquivos de layout XML. Por exemplo,
     as visualizações podem ser componentes (widgets) que:

     - exibem texto (classe TextView)
     - permitem inserir e editar texto (classe EditText)
     - representam botões clicáveis ​​(classe Button) e outros componentes interativos como
       RadioButton, CheckBox e Spinner
     - contém texto rolável (ScrollView) e itens roláveis ​​(RecyclerView)
     - mostrem imagens (ImageView)
     - contém outras visualizações e as posiciona (LinearLayout e ConstraintLayout)

     Você pode explorar a hierarquia de exibição do seu aplicativo no painel Component Tree (Árvore
     de Componentes) do Editor de Layout e selecioná-los a partir do painel paleta (Palette) para
     fazerem parte da visualização.

     O código Java que exibe e gerencia a interface com o usuário está contido em uma classe que
     estende Activity (AppCompatActivity) e contém métodos para aumentar as visualizações, ou seja,
     pegar o layout XML das visualizações e exibi-lo na tela. Por exemplo, a classe 'MainActivity'
     infla uma exibição de um conjunto de elementos 'CardViews'.

     Em aplicativos mais complexos, uma atividade pode implementar cliques e outros manipuladores
     de eventos, solicitar dados de um banco de dados ou da Internet ou desenhar conteúdo gráfico.

     O Android simplifica a separação clara dos elementos e dados da interface do usuário e usa a
     atividade para reuni-los novamente. Essa separação é uma implementação de um padrão MVP
     (Model-View-Presenter) .

 5 - A importância da ScrollView

     Se você tiver mais informações do que as que cabem na tela do dispositivo, poderá criar uma
     exibição de rolagem para que o usuário possa rolar verticalmente deslizando para cima ou para
     baixo ou horizontalmente deslizando para a direita ou esquerda.

     Você usaria normalmente uma exibição de rolagem para notícias, artigos ou qualquer texto longo
     que não caiba completamente na exibição. Você também pode usar uma exibição de rolagem para
     permitir que os usuários insiram várias linhas de texto ou combinar elementos da interface do
     usuário (como um campo de texto e um botão) em uma exibição de rolagem.

     A classe ScrollView fornece o layout para a exibição de rolagem. ScrollView é uma subclasse de
     FrameLayout. Coloque apenas uma vista como filho nela - uma vista filho contém a totalidade do
     conteúdo a ser rolado. Essa visão filho pode, por si só, ser um ViewGroup (como LinearLayout)
     contendo elementos da interface do usuário.

     Layouts COMPLEXOS podem sofrer problemas de desempenho com visualizações filho, como imagens.
     Uma boa opção para a View dentro de a ScrollView é a LinearLayout que é organizada em uma
     orientação vertical, apresentando itens pelos quais o usuário pode rolar (como elementos
     do tipo TextView).

     Com a ScrollView, todos os elementos da interface do usuário estão na memória e na hierarquia
     de exibição, mesmo que não sejam exibidos na tela. Isso é ideal para a ScrollView rolar
     páginas de texto de forma livre sem problemas, porque o texto já está na memória. No entanto,
     a ScrollView pode consumir muita memória, o que pode afetar o desempenho do restante do seu
     aplicativo. Para exibir longas listas de itens que os usuários podem adicionar, excluir ou
     editar, considere usar a RecyclerView.

     ScrollView é fácil de usar, mas não é recomendado para listas longas e roláveis.

 6 - A justificativa para o uso de RecyclerView

      Deixar o usuário exibir, rolar e manipular uma lista de itens de dados semelhantes é um
      recurso comum do aplicativo. Exemplos de listas roláveis ​​incluem listas de contatos, listas
      de museus, jogos salvos, diretórios de fotos, dicionários, listas de compras e índices de
      documentos.

      RecyclerView é uma subclasse de ViewGroup. É uma maneira mais econômica de exibir listas
      roláveis. Em vez de criar um View para cada item da lista que pode ou não estar visível na
      tela, o RecyclerView cria um número limitado de itens da lista e os reutiliza para o
      conteúdo visível.

 7 - Arquitetura adotada

     Os componentes da arquitetura do Android formam um conjunto de bibliotecas que ajuda você a
     projetar apps robustos, testáveis e de fácil manutenção. Utilizaremos classes para gerenciar
     o ciclo de vida do seu componente de UI (User Interface =  interface do usuário) e lidar com
     a persistência de dados (Room).

     (a) UI Controller (activity / fragment)
         - Exibe dados e encaminha em eventos da interface do usuário

     (b) ViewModel (LiveData's)
         - Mantém todos os dados necessários para a interface do usuário
         - A interface do usuário é notificada de alterações usando a observação
         - Uma vez notificada volta para (a)

     (c) Repository
         - Fonte única de verdade para todos os dados do aplicativo;
         - Conjunto de rotinas onde a interface do usuário se comunicar com banco de dados (d)
         - É uma API limpa da interface do usuário para se comunicar utilizando o Room (d)

     (d) RoomDatabase
         - Gerencia fonte de dados SQLite
         - Os dados são locais
         - Usando objetos DAO e Entity
         - DAO interage com o SQLite
         - Entity representa o objeto real (ou n-objetos)

     Entidade: no contexto de componentes de arquitetura, a entidade é uma classe anotada que
               descreve uma tabela de banco de dados. (ex. Museum.java)

     Banco de dados SQLite: no dispositivo, os dados são armazenados em um banco de dados SQLite.
                            A biblioteca de persistência Room cria e mantém esse banco de dados
                            para você. (ex. 'db_agendiario' na classe DbRoomDatabase.java)

     DAO: abreviação de objeto de acesso a dados. Um mapeamento de consultas SQL para funções.
          Você costumava definir essas consultas em uma classe auxiliar. Quando você usa um DAO,
          seu código chama as funções (métodos) e os componentes cuidam do resto.
          (ex.MuseumDao.java, que é uma interface)

     Room database: camada de banco de dados crida em cima de um banco de dados SQLite. Cuida de
                    tarefas comuns que você costumava lidar com uma classe auxiliar. O banco de
                    dados Room usa o DAO para emitir consultas ao banco de dados SQLite com base
                    nas funções chamadas. (ex. DbRoomDatabase.java)

     Repositório: Uma classe que você cria para gerenciar várias fontes de dados. Além de um banco
                  de dados Room, o Repositório pode gerenciar fontes de dados remotas, como um
                  servidor da Web. (ex. MuseumRepository.java)

     ViewModel: Fornece dados à interface do usuário e atua como um centro de comunicação entre o
                repositório e a interface do usuário. Oculta o back-end da interface do usuário.
                Instâncias da ViewModel sobrevivem a alterações na configuração do dispositivo.
                (ex. MuseumViewModel.java)

     LiveData: Uma classe detentora de dados que segue o padrão 'Observer', o que significa que
               pode ser observado. Sempre mantém / armazena em cache a versão mais recente dos
               dados. Notifica seus observadores quando os dados foram alterados. Geralmente, os
               componentes da interface do usuário observam dados relevantes. LiveData possui
               reconhecimento do ciclo de vida, e gerencia automaticamente a parada e a retomada
               da observação com base no estado de sua atividade ou fragmento de observação.
               (está presente em MuseumViewModel.java, MuseumDao.java, MuseumRepository.java)

     IMPORTANTE: Aqui o nosso padrão de arquitetura de software irá separar dados da interface
                 do usuário. O que é uma boa prática em termos de programação. Obviamente que isso
                 terá um impacto no número de classes a serem programadas.

 8 - Para um melhor entendimento do padrão 'Observer' considere o aplicativo 'Whatsapp'
    (https://en.wikipedia.org/wiki/Observer_pattern)

    Pattern Observer (padrão 'Observer') é um padrão de design de software no qual um objeto,
    chamado de sujeito, mantém uma lista de seus dependentes, chamados de observadores, e os
    notifica automaticamente sobre qualquer alteração de estado, geralmente chamando um de seus
    métodos.

    Pattern Observer é usado principalmente para implementar a manipulação de eventos em sistemas
    distribuídos, em software "orientado a eventos". Nesses sistemas, geralmente é chamado de
    "fluxo de eventos" qualquer solicitação que chega "aleatoriamente" a CPU (HTTP, entrada pelo
    teclado/mouse, bancos de dados distribuídos, blockchains, etc.)

    O padrão Observer aborda os seguintes problemas:

        - Uma dependência de um para muitos entre objetos deve ser definida sem tornar os
          objetos fortemente acoplados.

        - Deve-se garantir que, quando um objeto for alterado, um número aberto de objetos
          dependentes seja atualizado automaticamente.

        - É possível que um objeto possa notificar um número aberto de outros objetos.

    Em resumo, o padrão observador define uma dependência de um para muitos entre objetos. Sempre
    que um objeto altera seu estado, todos os dependentes do objeto são notificados e atualizados
    automaticamente. O objeto principal é chamado de "sujeito" e seus dependentes são chamados de
    "observadores". Geralmente, o sujeito notifica os observadores chamando um dos métodos dos
    observadores. O sujeito sabe quais métodos chamar, porque os observadores são "registrados"
    no assunto e especifica os métodos a serem chamados.

 9 - A classe 'R'

     A classe 'R' é criada automaticamente a após o Build do seu projeto, ou seja, se algum Build
     seu terminou com erro, essa classe não existirá mais e você terá que dar um ReBuild no app
     novamente.

     Os erros mais comum dizem respeito aos 'IDs' declarados nos arquivos XML e que são
     referenciados nas classe Java incorretamente.

     Faça sempre uso do menu Build > Clean Project e menu Build > Rebuild Project e observe a
     janela de erro. Tenha paciência para localizar o local

 10 - Sugestão de treinamento

      O curso 'Fundamentos do Desenvolvedor Android' foi criado pela equipe de treinamento do
      Google Developers. Para fazer o curso, você deve ter experiência com a linguagem de
      programação Java. Disponível em

      https://developer.android.com/courses/fundamentals-training/overview-v2

  */


/**
 Classe 'MainActivity'

 É um classe derivada (extends) da classe 'AppCompatActivity' e que foi implementada com recurso
 'View.OnClickListener', para que possa ficar aguardando algo ser clicado.

 AppCompatActivity, é uma classe filha de Activity, possui todos os comportamentos da classe pai,
 contudo ela é uma classe que funciona nas versões mais antigas do Android, além da mais recente, é
 uma classe de suporte.

 Essa classe será a primeira a ser inicializada. Pois, em 'AndroidManifest.xml' dizemos isso em:

     <activity android:name=".MainActivity"
                    android:launchMode="singleTop">
                         <intent-filter>
                            <action android:name="android.intent.action.MAIN" />

                            <category android:name="android.intent.category.LAUNCHER" />
                         </intent-filter>
     </activity>

 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     Classe 'CardView'

     É um componente visual, faz parte dos componentes do Material Design que mais veio a agregar
     à todas as possibilidades em interface gráfica desse sistema operacional Android.

     O CardView nos permite criar as conhecidas "caixas flutuantes" em layouts.

     Muitas vezes, os apps precisam exibir dados em contêineres com estilo semelhante. Esses
     contêineres são frequentemente usados em listas para armazenar as informações de cada item. O
     sistema operacional Android fornece a API CardView como uma maneira fácil de mostrar
     informações dentro de cards que têm uma aparência consistente em toda a plataforma. Esses
     cards têm uma elevação padrão acima do grupo de visualização que os contém, de modo que o
     sistema desenha sombras abaixo deles. Os cards são uma maneira fácil de conter um grupo de
     visualizações e, ao mesmo tempo, fornecer um estilo consistente para o contêiner.

     É necessário adicionar as dependências do widget CardView. Ele faz parte das Bibliotecas de
     Suporte v7. Para usá-lo no seu projeto, adicione a seguinte dependência ao arquivo
     'build.gradle' do módulo do app:

          implementation "androidx.cardview:cardview:1.0.0"

     */

    private CardView mcardBooks, mcardCities, mcardComics, mcardEconomy,
            mcardEvents, mcardIdeas, mcardIdentity, mcardMovies,
            mcardMuseums, mcardPersons, mcardPhrases, mcardPlayList,
            mcardRegister;

    /**
     Método 'onCreate()'

     Esse método é sobrescrito.

     É o responsável por carregar os layouts e outras operações de inicialização.

     Definimos a view que será apresentada 'R.layout.activity_dashboard'.

     Associamos os objetos cardviews aos seus correspondentes no layout com 'findViewById()'.

     Adicionamos o comportamento para cada objeto quando ocorrer um clique sobre ele nesta
     atividade com o uso do método 'setOnClickListener(this)'.

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mcardBooks = findViewById(R.id.card_books);
        mcardComics = findViewById(R.id.card_comics);
        mcardEconomy = findViewById(R.id.card_economy);
        mcardEvents = findViewById(R.id.card_events);
        mcardIdeas = findViewById(R.id.card_ideas);
        mcardMovies = findViewById(R.id.card_movies);
        mcardMuseums = findViewById(R.id.card_museums);
        mcardPersons = findViewById(R.id.card_persons);
        mcardPlayList = findViewById(R.id.card_playList);
        mcardRegister = findViewById(R.id.card_register);
        mcardIdentity = findViewById(R.id.card_identity);
        mcardPhrases = findViewById(R.id.card_phrases);
        mcardCities = findViewById(R.id.card_cities);

        mcardBooks.setOnClickListener(this);
        mcardComics.setOnClickListener(this);
        mcardEconomy.setOnClickListener(this);
        mcardEvents.setOnClickListener(this);
        mcardIdeas.setOnClickListener(this);
        mcardMovies.setOnClickListener(this);
        mcardMuseums.setOnClickListener(this);
        mcardPersons.setOnClickListener(this);
        mcardPlayList.setOnClickListener(this);
        mcardRegister.setOnClickListener(this);
        mcardIdentity.setOnClickListener(this);
        mcardPhrases.setOnClickListener(this);
        mcardCities.setOnClickListener(this);
    }

    /**
     Método 'onClick()'

     Esse método é sobrescrito usando a implementação 'View.OnClickListener'.

     Aqui está a intenção do usuário do aplicativo. Fez a escolha com o clique sobre o cardview
     correspondente.

     A estrutura 'switch' irá direcionar qual a próxima atividade a ser inicializada
     'startActivity(intent)', considerando o 'ID' do cardview correspondente 'view.getId()'

     */

    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.card_books:
                intent = new Intent(this, BooksActivity.class);
                startActivity(intent);
                break;
            case R.id.card_comics: intent = new Intent(this, ComicsActivity.class); startActivity(intent); break;
            case R.id.card_economy: intent = new Intent(this, EconomyActivity.class); startActivity(intent); break;
            case R.id.card_events: intent = new Intent(this, EventsActivity.class); startActivity(intent); break;
            case R.id.card_ideas: intent = new Intent(this, IdeasActivity.class); startActivity(intent); break;
            case R.id.card_movies: intent = new Intent(this, MoviesActivity.class); startActivity(intent); break;
            case R.id.card_museums: intent = new Intent(this, MuseumsActivity.class); startActivity(intent); break;
            case R.id.card_persons: intent = new Intent(this, PersonsActivity.class); startActivity(intent); break;
            case R.id.card_playList: intent = new Intent(this, PlayListActivity.class); startActivity(intent); break;
            case R.id.card_register: intent = new Intent(this, RegisterActivity.class); startActivity(intent); break;
            case R.id.card_identity: intent = new Intent(this, MyPersonalActivity.class); startActivity(intent); break;
            case R.id.card_phrases: intent = new Intent(this, PhrasesActivity.class); startActivity(intent); break;
            case R.id.card_cities: intent = new Intent(this, CitiesActivity.class); startActivity(intent); break;
            default: break;
        }
    }

}

