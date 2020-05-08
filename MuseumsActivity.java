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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 Considerações Iniciais

 1 - Crie na pasta res/layout o arquivo                   activity_museums.xml

     Utilize o link para o código do arquivo
     https://github.com/1268marcos/youtube_android/blob/master/activity_museums.xml
     Obs.: Alguns outros itens poderão ser necessários no arquivo

 2 - Crie o recurso de menu em res/menu o arquivo         main_menu_museums.xml

     Utilize o link para o código do arquivo
     https://github.com/1268marcos/youtube_android/blob/master/main_menu_museums.xml
     Obs.: Alguns outros itens poderão ser necessários no arquivo

 3 - Revisar e criar os elementos ausentes no arquivo     strings.xml   na pasta res/values

         <string name="save_success">Sucesso ao Gravar</string>
         <string name="save_fail">Falha ao Gravar</string>
         <string name="update_fail">Falha ao Alterar</string>
         <string name="update_success">Sucesso ao Alterar</string>
         <string name="failure_has_occurred">Ocorreu uma Falha</string>

     Utilize o link para o código do arquivo
     https://github.com/1268marcos/youtube_android/blob/master/strings.xml
     Obs.: Alguns outros itens poderão ser necessários no arquivo

 4 - Crie na pasta res/layout o arquivo                   recyclerview_item_museums.xml

     Utilize o link para o código do arquivo
     https://github.com/1268marcos/youtube_android/blob/master/recyclerview_item_museums.xml
     Obs.: Alguns outros itens poderão ser necessários no arquivo

 5 - Verificar se a classe    MuseumViewModel.java         está no mesmo package (pacote)
     Essa classe irá trabalhar com o observador nos dados (em vez de pesquisar mudanças) e
     atualizar apenas a interface do usuário (View) quando os dados sofrerem algum tipo de mudança.

 6 - Verificar se a classe    MuseumAdapter.java           está no mesmo package (pacote)
     É a responsável pela ligação do RecyclerView com os itens que serão exibibidos na lista
     através do método 'onBindViewHolder()'. Em caso de 'crash' do aplicativo ao abrir a atividade
     'ActitivyMuseums' sugiro verficar a programação do método 'onBindViewHolder()',
     principalmente, se os dados a serem ligados não são do tipo String (é apenas uma dica).

 7 - Verificar se a classe    MuseumAddEditActivity.java   está no mesmo package (pacote)

 8 - Configurar a interface de pesquisa (filtro)

     O uso do widget SearchView como item na barra de apps é a maneira preferencial para oferecer o
     recurso de pesquisa no app. Assim como em todos os itens da barra de apps, você pode definir
     que a SearchView seja sempre exibida, seja exibida apenas quando houver espaço ou como uma
     ação recolhível, que exibe a SearchView como um ícone inicialmente e depois ocupa toda a barra
     de apps como um campo de pesquisa quando o usuário clica no ícone.

     Para adicionar um widget SearchView à barra de apps, crie um arquivo na pasta res/menu com o
     nome de     main_menu_museums.xml     no projeto e adicione o seguinte código no arquivo
     entre a marcação <menu xmlns:android="http://schemas.android.com/apk/res/android">    </menu>

         <item
         android:id="@+id/action_search_museums"
         app:actionViewClass="androidx.appcompat.widget.SearchView"
         android:icon="@drawable/ic_search"
         android:orderInCategory="100"
         android:title="@string/action_search"
         app:showAsAction="ifRoom" />

         <item
         android:id="@+id/delete_all_museums"
         android:title="@string/delete_all_item"
         app:showAsAction="never"
         />

     Utilize o link para o código do arquivo
     https://github.com/1268marcos/youtube_android/blob/master/main_menu_museums.xml
     Obs.: Alguns outros itens poderão ser necessários no arquivo

 9 - Configurar o arquivo AndroidManifest.XML para incluir o recurso de filtro para a atividade

     Localize <activity android:name=".MuseumsActivity"></activity>

     Acrescente a intent do recurso como apresentado abaixo
         <activity android:name=".MuseumsActivity">

              <intent-filter>
                  <action android:name="android.intent.action.SEARCH" />
              </intent-filter>

          </activity>

 10 - Ciclo de vida de uma atividade

      À medida que o usuário navega no aplicativo, sai dele e retorna a ele, as instâncias
      Activity no aplicativo transitam entre diferentes estados no ciclo de vida. A classe
      Activity fornece uma quantidade de callbacks que permite que a atividade saiba sobre a
      mudança do estado: informa a respeito da criação, interrupção ou retomada de uma atividade
      ou da destruição do processo em que ela reside por parte do sistema.

      Dentro dos métodos de callback do ciclo de vida, você pode declarar como a atividade deve
      se comportar quando o usuário sai e retorna dela. Por exemplo, se você estiver criando um
      reprodutor de streaming de vídeo, poderá pausar o vídeo e encerrar a conexão de rede quando
      o usuário alternar para outro aplicativo. Quando o usuário retornar, será possível
      reconectar à rede e permitir que ele retome o vídeo de onde parou. Ou seja, cada callback
      permite que você realize o trabalho específico adequado a determinada mudança de estado.
      Fazer o trabalho certo no momento apropriado e gerenciar as transições da maneira correta
      faz com que seu aplicativo seja mais robusto e tenha melhor desempenho. Por exemplo, uma
      boa implementação dos callbacks de ciclo de vida pode ajudar a garantir que seu aplicativo
      evite os seguintes problemas:

      - Falhas se o usuário receber uma chamada telefônica ou mudar para outro aplicativo enquanto
        estiver usando seu aplicativo.

      - Consumo de recursos importantes do sistema quando o usuário não estiver usando ativamente
        o aplicativo.

      - Perda do progresso do usuário se ele sair do aplicativo e retornar mais tarde.

      - Falhas ou perda do progresso do usuário quando a orientação da tela mudar entre paisagem
        e retrato.

      A classe 'Activity' fornece um conjunto principal de seis callbacks: onCreate(), onStart(),
      onResume(), onPause(), onStop() e onDestroy(). Conforme a atividade entra em um novo estado,
      o SO invoca cada um desses callbacks. Veja um esquema em  https://bit.ly/3dnoq0C

      O ciclo de vida "mais simples" (ou seja, o menor número de chamadas de método) é
      onCreate() -> onStart() -> onResume() -> onPause()

      Para esclarecer como tudo acontece, aqui estão alguns cenários da vida real:

               (i) A atividade está em execução, outras atividades vêm em cima dela, onPauseé
                   chamada. O sistema fica sem memória, chama onSaveInstanceState, mata
                   atividade. Usuário pressionado algumas vezes, a atividade deve ser
                   re-instanciada (de preferência usando os dados salvos em onSaveInstanceState).

               (ii) A atividade está em execução, o usuário pressiona de volta. Neste ponto,
                    onPause -> onDestroy são chamados, sem chamar onSaveInstanceState.

      Você deve entender a diferença essencial entre onPause e onSaveInstanceState. O primeiro é
      sempre chamado, enquanto o último é chamado apenas quando a instância da atividade pode
      ser re-instanciada no futuro. Seguindo essa linha de pensamento, seus usuários esperam
      duas coisas:

               (i) Quando eles saem da sua atividade e depois voltam a ela, eles querem exatamente
                   na mesma instância em que a deixaram (isso seria alcançado usando
                   onSaveInstanceState). Eles não esperam isso se abandonarem sua atividade.
                   Contudo:

               (ii) Eles esperam que os dados inseridos sejam mantidos (o que será feito em
                    onPause). Por exemplo, se eles começaram a escrever uma mensagem, esperam
                    vê-la como um rascunho na próxima vez que voltarem, mesmo que tenham saído
                    da atividade.

      Você deve entender como esses métodos devem ser usados ​​para obter o que seus usuários
      esperam. Como você realmente usá-los é com você, suas necessidades, e da natureza de seu
      aplicativo.

      Mas se mesmo assim você ainda permanecer com dúvidas como isso ocorre, recomendo uma
      visita ao diagrama que apresenta o processo completo do que acontece quando você inicia
      qualquer atividade, a partir do onCreate neste link https://i.stack.imgur.com/ImckV.png

 11 - Modificadores de acesso

      Para começar existem somente 3 modificadores (private, protected e public), e com isso temos
      4 níveis de visibilidade. Os níveis são: private, default, protected e public.

            - Private: A única classe que tem acesso ao atributo é a própria classe que o define,
                       ou seja, se uma classe Pessoa declara um atributo privado chamado nome,
                       somente a classe Pessoa terá acesso a ele.

            - Default: Tem acesso a um atributo default (identificado pela ausência de
                       modificadores) todas as classes que estiverem no mesmo pacote que a classe
                       que possui o atributo.

            - Protected: Esse é o que pega mais gente, ele é praticamente igual ao default, com a
                         diferença de que se uma classe (mesmo que esteja fora do pacote) estende
                         da classe com o atributo protected, ela terá acesso a ele. Então o acesso
                         é por pacote e por herança.

            - Public: Esse é fácil, todos tem acesso.

      O nível de visibilidade envolve encapsulamento (Conceito de POO). É sempre dito como boa
      prática que atributos e métodos internos da classe devem ser privados, pois classes externas
      nem devem saber que ele existe. O que a classe expõe são suas funcionalidades, sua API, se
      preferir. Expor atributos internos pode causar sérios problemas de segurança. Se tem algo
      que é inerente à implementação e que pode vir a mudar no futuro, provavelmente, deve ser
      privado.

      Se precisarem ser acessados externamente, o uso de métodos de acesso (getters e setters) como
      fizemos na classe 'Museum.java' mantém o controle da classe (para validação, por exemplo).
      Para os métodos que não devem ser acessados externamente, deve-se procurar utilizar
      protected, por exemplo, como implementado na método sobrescrito 'onCreate()' aqui nesta
      classe.

      A documentação oficial está disponível em https://bit.ly/3bbq8Rc

 12 - Dependendo da versão do Android será necessário realizar no arquivo 'build.gradle' a
      declaração -> implementation 'com.android.support:recyclerview-v7:28.0.0'

 */

/**
 Classe 'MuseumsActitivy'

 Responsável por exibir a atividade principal referente aos Museu, onde é exibindo em uma lista do
 tipo (recyclerview e seus itens) e gerenciar o que ocorre com a próxima atividade (intenção) que
 permite adicionar um novo (inserir) museu e ou editar (alterar) os dados de um já existente.

 Recebemos o resultado da intenção (inserir ou alterar). Tudo OK ou não com as operacões.

 Ao configurar a exibição da lista de museus no RecyclerView adicionamos o comportamento
 associado ao item da lista (deslizar para esquerda ou direita e remover da lista). Aqui surge uma
 questão relacionada a utilização desta prática em confronto com a experiência do usuário.

 É configurada a barra do aplicativo com o botões voltar e pesquisar (padrão) e o menu onde há uma
 opção para excluir todos os museus da lista.

 Essa classe herda a classe AppCompatActitity que permite usar o ActionBar.

 */

public class MuseumsActivity extends AppCompatActivity {

    /**
     Constante 'ADD_MUSEUM_ACTIVITY_REQUEST_CODE'

     Constante que representa a atividade Inserir (ADD) definida com o código de solcitação 1

     Uma constante é definida com o uso da palavra reservada 'final' que ao ser aplicada a uma
     variável significa que ela não pode ser alterada após a sua inicialização, ou seja, seu
     valor inicial não pode ser alterado. Quando aplicamos 'final' a um método indicamos que esse
     método não pode ser sobreescrito (sobrecarregado). Aplicando 'final' em uma classe indica que
     não podemos substituí-la.
     */
    public static final int ADD_MUSEUM_ACTIVITY_REQUEST_CODE = 1;

    /**
     Constante 'EDIT_MUSEUM_ACTIVITY_REQUEST_CODE'

     Constante que representa a atividade Inserir (ADD) definida com o código de solcitação 2
     */
    public static final int EDIT_MUSEUM_ACTIVITY_REQUEST_CODE = 2;

    /**
     Variável 'TEXT_SEARCH_RESULT'

     Essa variável irá conter o texto que utilizamos em uma pesquisa/filtro
     */
    public String TEXT_SEARCH_RESULT = "com.example.agendiario.TEXT_SEARCH_RESULT";

    /**
     Objeto 'mMuseumViewModel'

     Criado a partir da classe 'MuseumViewModel'

     Contém os métodos para ações de CRUD no banco de dados
     */
    private MuseumViewModel mMuseumViewModel;

    /**
     Objeto 'fabMuseums'

     Criado a partir da classe 'FloatingActionButton'

     Representa o botão de ação flutante para inserir um novo museu
     */
    private FloatingActionButton fabMuseums;

    /**
     Objeto 'adapter'

     Criado a partir da classe 'MuseumAdapter'

     Esse objeto faz o ligação entre o RecyclerView e o ViewHolder. O adapter permitem personalizar
     a forma como um item de uma lista (um componente) é apresentado para o usuário.

     Lembrando que o ViewHolder é uma abordagem utilizada para guardar um conjunto de views para
     que possam ser eficientemente acessadas e reutilizadas, quando necessário, ou seja, ela guarda
     cada item a ser exibido na recyclerview.

     Importante - As listas formam uma categoria de componentes no Android. A abordagem recomendada
     é fazer uso das RecyclerViews, porém, há a as ListViews.

     Usamos o adapter para definir os dados na lista.

     */
    private MuseumAdapter adapter;

    /**
     Método 'setupRecyclerView()'

     Temos a configuração da recyclerview e o comportamento de cada item exibido do na lista.

     Atenção - Ao definir o método 'getFullMuseums()' verifique antes de importar qual classe será
               utilizada. Serão sugeridas:

                      -> import java.util.Observer;             irá ocasionar ERRO
                      e
                      -> import androidx.lifecycle.Observer;    usar esta

     Um RecyclerView, como o nome sugere, recicla as visualizações que estão fora da visibilidade
     do usuário. Por exemplo, se um usuário rolou para baixo até uma posição em que os itens 4 e 5
     estão visíveis; os itens 1, 2 e 3 seriam limpos da memória para reduzir o consumo de memória
     pelo aplicativo.

     */

    private void setupRecyclerView() {
        /**
         Objeto 'recyclerView'

         Criado a partir da classe 'RecyclerView' é associado com o componente 'recycler_museums'
         presente no layout 'activity_museums.xml'

         Nosso código pode evitar que o método 'findViewById()' que é demorado para atualizar os
         widgets com novos dados seja repetido por várias vezes e isso justifica o uso do
         RecyclerView.

         */
        RecyclerView recyclerView = findViewById(R.id.recycler_museums);

        /**
         Método 'setLayoutManager()'

         Define um arranjo de layout personalizado para visualização do tipo listagem
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /**
         Método 'setHasFixedSize()'

         O objetivo do método 'setHasFixedSize' é que se soubermos antecipadamente que nossa
         lista sempre terá o mesmo tamanho, essa chamada tornará o gerenciamento da lista muito
         mais eficiente.

         */
        recyclerView.setHasFixedSize(true);

        /**
         adapter é responsável por converter cada objeto em um item da lista.
         */
        adapter = new MuseumAdapter(this);

        /**
         Método 'setAdapter()'

         Define o adapter para o recyclerview
         */
        recyclerView.setAdapter(adapter);

        /**
         Objeto 'mMuseumViewModel'

         Esse objeto obtém (get) a lógica principal definida na classe 'MuseumViewModel'.

         Um ViewModel é a principal entidade da camada de lógica de negócio da arquitetura
         recomendada pelo Google Android. É nessa camada que há as invocações à APIs de busca de
         dados, camada de modelo, há também inicializações de algumas classes, principalmente as
         que têm atualizações que devem ser refletidas na camada de visualização. O ViewModel pode
         ser vinculado a dois tipos de componentes Android: activity e fragment. Depois deste
         vinculo o objeto ViewModel somente é removido da memória caso o componente vinculado seja
         destruído permanentemente.

         A principal característica do ViewModel é a capacidade de manter dados em memória
         enquanto, por exemplo, há uma reconstrução da atividade a qual ele está vinculado.

         Faz uso de recursos da classe 'LiveData' - que permite o uso simples do react / observer
                                       no projeto, evitando o uso de APIs de terceiros e maior
                                       dependência entre as camadas da arquitetura em uso;
                                       Essa classe foi utilizada em 'MuseumViewModel'

         Importante revisar que um ViewModel é um representante da camada de negócio, responsável
         por realizar as chamadas das camadas inferiores (banco de dados) e entregar os dados
         corretos a camada superior, está última a camada de visualização, Activity / Fragment.

         Quanto a camada inferior, usamos a Room, que é uma API que facilita o trabalho com a
         camada de modelo e persistência de dados (SQlite).

         */
        mMuseumViewModel = ViewModelProviders.of(this).get(MuseumViewModel.class);

        /**
         Método sobrescrito 'onChanged()'

         Criado a partir da combinação do objeto/método 'mMuseumViewModel.getFullMuseums()' e
         do método 'observe()' da classe LiveData.

         Em cada mudança realizada (observada) na lista de museus é feita a comunicação para o
         adaptador atualizar o conjunto (set) de museus apresentados na recyclerview.

         CUIDADO E ATENÇÃO - Ao importar a classe Observer com o recurso autocompletar do Android
                             Studio.

                             Correta -> import androidx.lifecycle.Observer;

                             Incorreta (vai gerar erro) -> import java.util.Observer;
         */
        mMuseumViewModel.getFullMuseums().observe(this, new Observer<List<Museum>>(){
            @Override
            public void onChanged(@Nullable final List<Museum> museums){
                adapter.setMuseums(museums);
            }
        });

        /**
         Implementação 'new ItemTouchHelper()'

         É um utilitário poderoso que cuida de tudo o que você precisa para adicionar
         funcionalidades como arrastar e soltar (onMove) e deslizar para dispensar (onSwiped) no
         RecyclerView. É uma subclasse de RecyclerView.ItemDecoration, o que significa que é
         facilmente adicionada a quase todos os LayoutManager e Adaptadores existentes. Também
         funciona com animações de itens existentes e permite arrastar com restrição.

         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mMuseumViewModel.delete(adapter.getMuseumAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        /**
         Implementação 'setOnItemClickListener()'

         Essa implementação faz uso do adapter, uma vez, que ele gerencia os itens na recyclerview
         e está aguardando algo que o usuário faça (escutando e observando).

         A implementação obtém o item que foi clicado. Transferindo (put) os dados do museu
         escolhido com o clique para uma nova intenção (new Intent) representada pela classe
         'MuseumAddEditActivity' que será aberta (chamada) tendo como origem a classe da atividade
         atual (pai) 'MuseumsActivity'.

         Essa atividade (intent) será aberta através do método 'startActivityForResult()' para que
         se faça a  edição dos dados representada pela constante 'EDIT_MUSEUM_ACTIVITY_REQUEST_CODE'
         que contém o requestCode 2 (definido no início do código) e que posteriormente será
         necessário no método 'onActivityResult()' para determinar qual atividade (intent) está
         enviando dados para ela.

         O método 'startActivityForResult()' aguarda o resultado da edição de dados.

         */
        adapter.setOnItemClickListener(new MuseumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Museum museum) {
                Intent intent = new Intent(MuseumsActivity.this, MuseumAddEditActivity.class);
                intent.putExtra(MuseumAddEditActivity.EXTRA_ID, museum.getId());
                intent.putExtra(MuseumAddEditActivity.EXTRA_NAME, museum.getName());
                intent.putExtra(MuseumAddEditActivity.EXTRA_STYLE, museum.getStyle());
                intent.putExtra(MuseumAddEditActivity.EXTRA_SCORE, museum.getScore());
                startActivityForResult(intent, EDIT_MUSEUM_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    /**
     Método 'onActivityResult()'

     Recupera toda as ações realizadas na segunda atividade (MuseumAddEditActivity.java) ao
     retornar para a primeira atividade (MuseumsActivity) que é a atividade pai.

     O método possui 3 argumentos:
         - int requestCode (variável do tipo int)
         - int resultCode  (variável do tipo int)
         - Intent data     (objeto data criado a partir da classe Intent)

     Usamos o 'requestCode' da segunda atividade para comparar com o 'REQUEST_CODE' da primeira
     atividade (lembrando que são as constantes 'ADD_MUSEUM_ACTIVITY_REQUEST_CODE' e
     'EDIT_MUSEUM_ACTIVITY_REQUEST_CODE').

     Avaliamos o 'resultCode' da segunda atividade, pois, ao inserir um novo museu ou editar os
     dados de um museu existente esperamos que tudo corra bem e o 'resultCode' seja igual a 'tudo
     correu bem na segunda atividade', ou seja, 'RESULT_OK'.

     O objeto 'data' carrega os dados com a intenção de regressar para a atividade pai. Geralmente,
     usamos os dados quando definimos (put) EXTRAS na intent que foi chamada. Em nosso exemplo,
     isso foi feito no método 'saveMuseum()' na classe 'MuseumAddEditActivity.java'.

     Mas o nosso usuário pode se arrepender de chamar a segunda atividade, ou seja, ele quer
     cancelar o que seria feito ou até mesmo fechar a segunda atividade. Nesse caso o 'resultCode'
     é igual a operação cancelada, ou seja, seu valor é 'RESULT_CANCEL'.

     Se ação do usuário não foi OK ou CANCEL, podemos ter algo mais grave acontecendo na segunda
     atividade e nesse caso iremos dar a mensagem para ele que pode ter ocorrido uma falha.

     A palavra-chave 'super' refere-se à instância da classe 'AppCompatActivity' (classe pai
     principal) do objeto atual na memória representante da classe 'MuseumsActivity'. Usar 'super'
     é útil quando você substitui um método em uma subclasse, mas ainda deseja chamar o método
     definido na classe pai principal.

     */

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_MUSEUM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            // Teste para saber se correu tudo bem ao inserir um novo museu (ainda em memória)

            /**
             Objeto 'museum'

             Criado a partir de uma instância (= new) da classe 'Museum'.

             Passa os valores obtidos na segunda atividade através do objeto 'data' utilizando os
             métodos 'getStringExtra()' e 'getIntExtra()' para o constutor da classe que possui, em
             nosso exemplo, 4 argumentos. São eles:
                  - name            MuseumAddEditActivity.EXTRA_NAME).trim()
                  - style           MuseumAddEditActivity.EXTRA_STYLE).trim()
                  - score           MuseumAddEditActivity.EXTRA_SCORE
                  - creationDate    MuseumAddEditActivity.EXTRA_CREATE

             Código do construtor da classe 'Museum'
             public Museum(@Nullable String name, String style, int score, String creationDate) { }

             IMPORTANTE - a implementação feita aqui tem caráter didático. Ao fazermos para a
                          situação de edição de dados será apresentda outra forma de implementação.
             */
            Museum museum = new Museum(data.getStringExtra(MuseumAddEditActivity.EXTRA_NAME).trim(),
                    data.getStringExtra(MuseumAddEditActivity.EXTRA_STYLE).trim(),
                    data.getIntExtra(MuseumAddEditActivity.EXTRA_SCORE,0),
                    data.getStringExtra(MuseumAddEditActivity.EXTRA_CREATE));

            /**
             Método 'insert()'

             Utilizando o membro 'mMuseumViewModel' a operação (transação) para inserir os
             dados no banco de dados tem início. Aqui ocorre a persistência de dados no SQlite.
             */
            mMuseumViewModel.insert(museum);

            /**
             Método 'makeText()'

             Toast é uma pequena mensagem exibida na tela, semelhante a uma dica de ferramenta ou
             outra notificação pop-up semelhante. Um Toast é exibido no conteúdo principal
             de uma atividade e permanece visível apenas por um curto período de tempo.

             */
            Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_MUSEUM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Teste para saber se correu tudo bem ao editar os dados do museu (ainda em memória)

            /**
             Variável 'id'

             Valor obtido a partir da segunda atividade através do objeto 'data' utilizando o
             método 'getIntExtra()' e representa o identificador único do museu.

             Fato é que o valor do id foi passado desta atividade 'MuseumsActivity' para
             a atividade 'MuseumAddEditActivity'

             Destaco que o objeto 'data' é um representante da classe 'Intent' como pode ser
             observado mais acima em 'public void onActivityResult(....) {'

             */
            int id = data.getIntExtra(MuseumAddEditActivity.EXTRA_ID, -1);

            // Teste para saber se o id é igual ao valor -1, ou seja, não foi possível recuperar
            // o identificador único
            if(id == -1){
                Toast.makeText(this, R.string.update_fail, Toast.LENGTH_SHORT).show();
                return;
            }

            /**
             Variáveis name, style, score e create

             Passa os valores obtidos na segunda atividade através do objeto 'data' utilizando os
             métodos 'getStringExtra()' e 'getIntExtra()' São eles:

                 - name            MuseumAddEditActivity.EXTRA_NAME).trim()
                 - style           MuseumAddEditActivity.EXTRA_STYLE).trim()
                 - score           MuseumAddEditActivity.EXTRA_SCORE
                 - creationDate    MuseumAddEditActivity.EXTRA_CREATE

             */
            String name = data.getStringExtra(MuseumAddEditActivity.EXTRA_NAME).trim();
            String style = data.getStringExtra(MuseumAddEditActivity.EXTRA_STYLE).trim();
            int score = data.getIntExtra(MuseumAddEditActivity.EXTRA_SCORE, 0);
            String create = data.getStringExtra(MuseumAddEditActivity.EXTRA_CREATE);

            /**
             Objeto 'museum'

             Criado a partir de uma instância (= new) da classe 'Museum'.

             Passa os valores obtidos na segunda atividade para o construtor da classe 'Museum'.

             */
            Museum museum = new Museum(name, style, score, create);

            /**
             Método 'setId()'

             Como o 'id' não está presente no construtor da classe

             public Museum(@Nullable String name, String style, int score, String creationDate) { }

             Definimos o id fazendo o uso do método 'setId()' para que seja possível realizar a
             alteração dos dados do museu.

             IMPORTANTE - a implementação feita aqui tem caráter didático. Ao fazermos para a
             situação de inclusão de um novo museu fizemos de outra forma.

             */
            museum.setId(id);

            /**
             Método 'update()'

             Utilizando o membro 'mMuseumViewModel' e o objeto 'musuem' a operação (transação) para
             atualizar os dados no banco de dados tem início. Aqui ocorre a persistência de dados
             no SQlite.
             */
            mMuseumViewModel.update(museum);

            Toast.makeText(this, R.string.update_success, Toast.LENGTH_SHORT).show();

        } else {
            // Teste para identificar uma falha considerando-se que não foi utilizado o botão de
            // navegação VOLTAR do dispositivo (Ação cancelada) ou botão FECHAR [X] no aplicativo
            // (Ação cancelada)
            if(resultCode != RESULT_CANCELED) {
                Toast.makeText(this, R.string.failure_has_occurred, Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     Método 'onCreate()'

     É o responsável por carregar os layouts e outras operações de inicialização. O ciclo de vida
     completo da Activity ocorre entre a primeira chamada no 'onCreate(Bundle)' até a chamada do
     onDestroy(). Sendo assim, uma Activity irá executar tudo o que for "global" no onCreate() e
     liberar todos os recursos no onDestroy(). Este método mantem o estado da atividade antes
     dela ser encerrada. Bundle, de acordo a tradução para o português, significa "pacote", que
     são geralmente utilizados para a passagem de dados entre as várias atividades (Activity)
     do Android.

     Esse callback 'onCreate()' precisa ser implementado. Ele é acionado assim que o sistema cria a
     atividade. Quando a atividade é criada, ela insere o estado Criado. No método onCreate(), você
     executa a lógica básica de inicialização do aplicativo. Isso deve acontecer somente uma vez
     durante a totalidade do período que a atividade durar. Por exemplo, sua implementação de
     onCreate() pode vincular dados a listas, associar a atividade a um ViewModel e instanciar
     algumas variáveis com escopo de classe. Esse método recebe o parâmetro o objeto
     'savedInstanceState', um objeto Bundle que contém o estado anteriormente salvo da atividade.
     Se a atividade nunca existiu, o valor do objeto Bundle será nulo.

     Com o objeto 'savedInstanceState' é possível sair do aplicativo e voltar posteriormente com
     ele no mesmo estado, obviamente que de acordo com o trabalho bem feito com os dados
     recuperados desta API. Porém, o 'savedInstanceState' é para pequenos conjuntos de dados, caso
     haja uma lista bem grande de objetos e esses contendo ainda outros objetos, o recomendado é
     que não seja utilizado o 'savedInstanceState' devido a limitação em memória para esta API.

     Reforçando que o objeto 'savedInstanceState' faz com que os dados durem por mais tempo em
     memória, porém suporta uma quantidade bem menor deles. Um opção, para grande volume de dados,
     é fazer uso de uma ViewModel que é mais temporária e suporta maior quantidade de dados em
     memória.

     O uso da palavra-reservada 'final' indica que o objeto 'savedInstanceState' é constante.

     O modificador 'protected' torna o membro ou método acessível às classes do mesmo pacote
     (com.example.agendiario). E se, utilizarmos herança, seus membros herdados não são acessíveis
     a outras classes fora do pacote em que foram declarados.

     Na declaração deste método até que se faça a inclusão da superclasse 'AppCompatActivity' com
     o uso da palavra reservada 'super' haverá a sinalização de erro.

     'savedInstanceState' é um parâmetro do método onCreate() que recebe um argumento do tipo
     Bundle. Ele é usado pelo sistema para, ao recriar uma Activity, permitir restaurar o estado
     que ela tinha na altura em que foi destruída, por exemplo, devido ao usuário ter girado o
     dispositivo.

     Entenda-se que estado aqui apenas se refere ao conteúdo das views do layout da Activity, ou
     seja, o sistema apenas guarda automaticamente o estado do layout. Qualquer outro tipo de
     informação que a Activity tenha será perdida, a não ser que seja explicitamente guardada
     nesse Bundle.

     Imagine que você esteja desenvolvendo um jogo e quer manter o placar e o nível em que se
     encontra. Se girar o dispositivo esse placar e nível serão zerados. Desejamos que esse efeito
     não ocorra, certo. Para isso a Activity disponibiliza o método 'onSaveInstanceState()' que é
     chamado antes da Activity poder vir a ser destruída. O sistema passa a ele o objeto Bundle
     que mais tarde é recuperado no método onCreate() quando a Activity for recriada, permitindo
     que seja guardada e depois recuperada outro tipo de informação. Veja a codificação:

         static final String STATE_SCORE = "playerScore";
         static final String STATE_LEVEL = "playerLevel";
         ...

         @Override
         public void onSaveInstanceState(Bundle savedInstanceState) {
             // Salvando o estado atual do jogo do usuário
             savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
             savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

             // Sempre chame a superclasse para salvar o estado da hierarquia de exibição
             super.onSaveInstanceState(savedInstanceState);
         }

     Que depois são recuperadas no onCreate(), se a Activity tiver sido recriada:

         ...
         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState); // Sempre chame a superclasse primeiro

             // Verificamos se estamos recriando uma instância destruída anteriormente
             if (savedInstanceState != null) {
                 // Restaurar o valor dos membros do estado salvo
                 mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
                 mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
             } else {
                // Provavelmente inicialize membros com valores padrão para uma nova instância
             }
             ...
         }

     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // definir o layout a ser criado e aberto para visualização
        setContentView(R.layout.activity_museums);

        /**
         Método 'getSupportActionBar().setDisplayHomeAsUpEnabled()'

         Definir a 'seta para voltar padrão' na barra do aplicativo. Essa solicitação atende a
         experiência do usuário Daniel (meu filho) que notou na versão 0.1.1 do aplicativo que
         não havia o botão voltar como numa conversa do Whatsapp. O que tornava a experência do
         usuário ruim, uma vez que, o usuário deveria utilizar o botão do voltar do dispositivo.

         O comportamento da 'seta para voltar' será definido no método 'onSupportNavigateUp()' e
         isso é importante frizar uma vez que poderia também ser definido em outro método
         'onOptionsItemSelected()' o que exigiria um pouco mais de programação .

         */

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // método que vai configurar o RecyclerView e o comportamento de cada item da lista
        setupRecyclerView();

        /**
         Objeto 'fabMuseums'

         Binding (Ligação) entre os elementos do layout com a classe através do método
         'findViewById()'. Esse método é o responsável por ligar o elemento de tela que está
         no XML com o objeto declarado no código Java.

         Por isso a importância ao dar o nome do elemento de tela através do atributo 'ID'. Em
         nosso exemplo o 'ID' definido no layout XML foi 'fab_museums' que aqui no código
         Java é acessado através de 'R.id.fab_museums' que se você ainda não notou durante a
         codificação é um número inteiro alocado na memória.

         */

        fabMuseums = findViewById(R.id.fab_museums);

        /**
         Método 'setOnClickListener()'  - versão apresentada em 05.05.2020

         O método 'setOnClickListener()' é um dos métodos mais utilizáveis no Android. Ele é o que
         nos ajuda a vincular um ouvinte a determinados atributos para realizar alguma ação.

         Esse método será utilizado pelo objeto 'fabMuseums' que é instanciado (criado) a partir
         da classe 'FloatingActionButton' que contém este método.

         'setOnClickListener()' é um método no Android usado basicamente com botões, botões de
         imagem, etc. Você pode iniciar esse método facilmente como, por exemplo em:

            fabMuseums.setOnClickListener(new View.OnClickListner() {
                 @Override
                 public void onClick(View v){
                     Intent intent = new Intent(MuseumsActivity.this, MuseumAddEditActivity.class);
                     startActivityForResult(intent, ADD_MUSEUM_ACTIVITY_REQUEST_CODE);
                 }
            });

         Ao invocar (chamar) esse método, uma função de retorno de chamada será executada. Também
         é possível criar uma classe para mais de um ouvinte, o que pode levar à reutilização
         do código.

         */

        // A implementação abaixo foi apresentada no vídeo https://youtu.be/-kMnwGyban8
        // Funciona corretamente, porém, dando uma nova característica a atividade
        // foi refeita a implementação em 08.05.2020

//        fabMuseums.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(MuseumsActivity.this, MuseumAddEditActivity.class);
//                startActivityForResult(intent, ADD_MUSEUM_ACTIVITY_REQUEST_CODE);
//            }
//        });


        /**
         Método 'setOnClickListener()' - nova versão 08.05.2020

         Esse método será utilizado pelo objeto 'fabMuseums' que é instanciado (criado) a partir
         da classe 'FloatingActionButton' que contém este método.

         Nessa implementação o método tem como argumento a subclasse 'NewMuseumClick()'. Com essa
         subclasse podemos utilizá-la em várias outras ações do aplicativo. Em nosso caso iremos
         deixar disponível um item de menu 'Novo Museu'.

         Por exemplo, uma outra possibilidade com essa abordagem da subclasse é se queremos manter
         uma contagem de quantas vezes 'onClick()' foram invocadas (chamados/usados) e para isso
         deverá ser adicionadas 'int count = 0;' a essa subclasse e, em seguida, incrementar essa
         contagem seria simples.

         Outra razão pela qual usaremos assim, eu gosto dessa opção, é que ajuda a organizar seu
         código. Você pode facilmente recolher esta subclasse e esquecê-la até precisar
         analisá-la, e provavelmente você definirá todos os seus outros métodos para a atividade
         antes desta subclasse.

         Assista ao vídeo em https://youtu.be/C3X1Lv4nyOI

         */

        fabMuseums.setOnClickListener(new NewMuseumClick());

    }

    /**
     Método 'newMuseumClicked()'

     Esse método foi criado para atender a implementação de 'setOnClickListener()' em 08.05.2020.

     A nomeação do método considera que o usuário 'clicou em novo museu'.

     Nele temos a intenção de iniciar uma atividade.

     Essa atividade (intent) será aberta através do método 'startActivityForResult()' para que
     se faça a  edição dos dados representada pela constante 'ADD_MUSEUM_ACTIVITY_REQUEST_CODE'
     que contém o requestCode 1 (definido no início do código) e que posteriormente será
     necessário no método 'onActivityResult()' para determinar qual atividade (intent) está
     enviando dados para ela.

     O método 'startActivityForResult()' aguarda o resultado da digitação de dados do novo museu.

     */
    private void newMuseumClicked(){
        Intent intent = new Intent(MuseumsActivity.this, MuseumAddEditActivity.class);
        startActivityForResult(intent, ADD_MUSEUM_ACTIVITY_REQUEST_CODE);
    }

    /**
     Subclasse 'NewMuseumClick'

     Essa classe foi criada para atender a implementação de 'setOnClickListener()' em 08.05.2020.

     A nomeação da classe considera que o usuário 'clique em novo museu'.

     A subclasse é implementada (tem as características) com a classe 'View.OnClickListener' que
     é a responsável por escutar o que ocorre na atividade.

     O método 'onClick()' sobrescrito necessita saber qual a visualização ativa (view) e só assim,
     estará apto a fazer a chamada para o método 'newMuseumClicked()'.

     Eu já sei o que você está pensando: "Que complicado?!" antes dessa implementação e assistindo
     o vídeo em https://youtu.be/-kMnwGyban8 parecia mais fácil.

     */

    class NewMuseumClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            newMuseumClicked();
        }
    }

    /**
     Método 'onSupportNavigateUp()'

     Esse método é sobrescrito para que possamos usar a 'seta para voltar padrão' na barra do
     aplicativo e realize a finalização da atividade 'finish()'.

     Seu uso está associado ao método 'getSupportActionBar().setDisplayHomeAsUpEnabled()' que
     foi comentando anteriormente.

     Como não é um método do tipo 'void' e sim do tipo 'boolean' temos a necessidade de retornar
     um valor 'true'.

     */

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    /**
     Método 'onCreateOptionsMenu()'

     Esse método é sobrescrito para que possamos usar digitar o texto pesquisável da busca que
     iremos utilizar para encontrar um museu considerando parte do nome.

     Utilizamos esta função para substituir (sobreescrever) a função padrão para personalizar
     nosso próprio menu, como adicionar botões e textos, imagens, etc., no menu.

     A pesquisa é um recurso existente em um grande número de aplicativos Android. É muito
     importante fornecer uma maneira de pesquisar conteúdo no seu aplicativo, pois ajuda os
     usuários a encontrar o conteúdo que desejam.

     'onCreateOptionsMenu()' é chamado em tempo de execução do Android quando é necessário criar
     o menu de opções.

     Será necessário importar a classe 'Menu' com o uso do ALT+ENTER.

     Usamos 'onCreateOptionsMenu()' para especificar o menu de opções para uma atividade. Nesse
     método, você pode inflar (aumentar) o recurso de menu (definido em XML) no retorno da
     chamada com 'return true;'. Insisto: você deve retornar 'true' para que o menu seja
     exibido; se você retornar false, não será mostrado.

     RECOMENDO que logo após construir o código:

                 @Override
                 public boolean onCreateOptionsMenu(Menu menu){ }

     INCLUA o retorno como demonstrado a seguir:

                 @Override
                 public boolean onCreateOptionsMenu(Menu menu){

                   return true;
                 }

     O método necessita como argumento a classe 'Menu' e um objeto 'menu' que irá representar
     o menu XML criado nos recursos do aplicativo.

     O 'onCreateOptionMenu(Menu)' é chamado quando o botão de menu do dispositivo é pressionado,
     ou seja Activity.openOptionsMenu() é chamada.

     A renderização real (exibição) do menu é tratada pela atividade. Pouco antes de ser exibido,
     a Activity passa o menu para você, para que você possa preenchê-lo com seus próprios itens e o
     mostra.

     Portanto, o Android assume que, como não é da sua conta renderizar o menu, você não deve
     controlar qual menu é realmente passado para você em 'onCreateOptionsMenu', por este motivo
     você deve construir o recurso com o arquivo XML do menu.


     O botão de menu são os '3 pontos' à direita da barra de ação. Veja o que acontece:

                 O comportamento real é: Você pressiona o botão como dito. Este botão não é um
                 Menu é apenas um botão. A Activity é notificada e chama 'onCreateOptionsMenu()' e
                 e 'onPrepareOptionsMenu()'. Ambas as funções irão receber o MenuInflater da
                 Activity (Activity.getMenuInflater). O Menu é um objeto que é passado pela
                 atividade.

     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // até incluir return true; irá indicar erro, pois, o método exige um retorno

        /**
         Classe 'MenuInflater' e objeto 'menuInflater'

         Com a classe 'MenuInflater' criamos um objeto 'menuInflater' que é capaz de criar o menu
         a partir de recursos xml (é claro, apenas recursos de menus), ou seja: constrói uma nova
         instância de Menu determinado no identificador de recurso de menu.

         Resumindo: criar o menu na AppBar (barra de menu do aplicativo)

         */
        MenuInflater menuInflater = getMenuInflater();

        // inflar o menu definido no recurso de menu 'main_menu_museums'
        menuInflater.inflate(R.menu.main_menu_museums, menu);

        /**
         Classe 'SearchManager' e objeto 'searchManager'

         Esta classe fornece acesso aos serviços de pesquisa do sistema.

         Na prática, você não interagirá diretamente com essa classe, pois os serviços de pesquisa
         são fornecidos pelos métodos da Activity na intent ACTION_SEARCH. Não esqueça de fazer
         isso no arquivo 'AndroidManifest.xml'

         */

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        /**
         Classe 'SearchView' e objeto 'searchView'

         O SearchView é a interface que o usuário do aplicativo tem para entrar com a busca
         desejada, essa interface é configurada por um arquivo xml 'action_search_museums',
         que criamos na pasta de recursos. Esse arquivo vai permitir que o Android instancie
         um método 'setSearchableInfo()' no qual todas as Acivities que vão permitir que uma
         busca seja realizada devem utilizar.

         Atenção para importar a biblioteca correta  import androidx.appcompat.widget.SearchView;

         Aqui obtemos o SearchView e definimos a configuração para que seja pesquisável
         */

        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search_museums).getActionView();

        // assume que a atividade atual é a atividade pesquisável
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // esteja atento após o new para usar o autocompletar do código
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             Método 'onQueryTextSubmit()'

             É acionado quando a pesquisa é pressionada.
             */

            @Override
            public boolean onQueryTextSubmit(String query) {
                // variável TEXT_SEARCH_RESULT recebe o que será pequisável
                TEXT_SEARCH_RESULT = query;
                return false;
            }

            /**
             Método 'onQueryTextChange()'

             É chamado quando o usuário digita cada caractere no campo de texto.

             Você pode implementar o que quiser nesses métodos para realizar uma pesquisa como
             acessar um banco de dados SQLite (nosso caso) ou um serviço da web.

             Pesquisa usando a voz veja um exemplo em https://intel.ly/2WhjhRU

             */
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter( newText);
                return false;
            }

        });

        return  true;
    }

    /**
     Método 'onOptionsItemSelected()'

     Esse método é sobrescrito para que possamos usar obter qual item do menu foi selecionado e
     assim tomar uma ação relacionda a escolha feita.

     */


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // até return true; indica que há erro para o método
        switch (item.getItemId()){
            case R.id.delete_all_museums:
                mMuseumViewModel.deleteAll();
                Toast.makeText(this, R.string.message_delete_all, Toast.LENGTH_SHORT ).show();
                return true;
            case R.id.add_new_museum:
                newMuseumClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
