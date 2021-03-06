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

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 Considerações Iniciais

 [1] Uma aplicação interage com uma data source (fonte de dados) por meio de
     operações CRUD (create, retrieve, update e delete). Essas operações permitem criar,
     recuperar, atualizar e deletar objetos. As fontes de dados podem ser as mais
     variadas com destaque para: banco de dados relacional; banco orientado a
     objetos; banco de dados NoSQL; sistema de arquivos; web services baseados nos
     protocolos SOAP ou REST; repositório baseado no protocolo LDAP (Lightweight
     Directory Access Protocol - exemplo de LDAP uma lista telefônica = diretório
     de telefones)

     Desta necessidade de interação entre a classe Dao e a fonte de dados, iremos
     considerar a nossa Dao como uma Interface. Para compreender o conceito de Interface
     pense na classe Dao como o controle remoto para ligar a TV, ou seja, a interface
     entre você e a TV.

 [2] O padrão Data Access Object (DAO, Objetos de acesso a dados) tem como objetivo
     encapsular o acesso ao data source fornecendo uma interface para que as diversas
     outras camadas da aplicação possam se comunicar com o data source.

 [3] Objetos de acesso a dados são as principais classes nas quais você define suas
     interações com o banco de dados. Eles podem incluir uma variedade de métodos de
     consulta, inserção, atualização e exclusão física das linhas de uma tabela.

 [4] É recomendável ter várias classes Dao na sua base de código, dependendo das
     tabelas que utilizamos e suas relações.
 */

/**
 Anotação '@Dao'

 A classe marcada com @Dao deve ser uma interface ou uma classe abstrata para funcionar
 com a Room. Em tempo de compilação, o Room irá gerar uma implementação dessa classe
 quando for referenciada para utilizar a fonte de dados com o insert, update, delete e select.
 */
@Dao
public interface MuseumDao {

    /**
     Anotação '@Query'

     Marca um método em uma classe Dao como um método de consulta ou para executar
     uma instrução T-SQL.

     O valor da anotação inclui a consulta (ou instrução T-SQL) que será executada
     quando esse método for chamado. Essa consulta é verificada no momento da
     compilação pelo Room para garantir que seja compilada corretamente no banco de dados.

     É possivel criar Querie’s personalizadas para os select’s, insert’s, delete’s e
     update’s.
     */
    @Query("SELECT * FROM museums ORDER BY UPPER(museumName) ASC")
    LiveData<List<Museum>> getAllMuseums();

    /**
     Anotação '@Insert'

     Marca um método em uma classe Dao como um método de inserção.

     A implementação do método irá inserir seus parâmetros no banco de dados. Se a
     entidade de destino contiver uma PrimaryKey ela será gerada automaticamente

     Observação - não se faz necessário utilizar para o método 'void insert()' a
     o modificador de acesso 'public'
     */
    @Insert
    void insert(Museum museum);

    /**
     Anotação '@Update'

     Marca um método em uma classe Dao como um método de atualização.

     A implementação do método irá atualizar seus parâmetros no banco de dados, se
     eles já existirem. Se eles ainda não existirem, esse opção não alterará
     o banco de dados

     Observação - não se faz necessário utilizar para o método 'void updateMuseum()' a
     o modificador de acesso 'public'
     */
    @Update
    void updateMuseum(Museum museum);

    /**
     Anotação '@Delete'

     Marca um método em uma classe Dao como um método de exclusão. A implementação do
     método excluirá a linha da tabela no banco de dados.

     Veja o código 1 - executa a exclusão:
     @Delete
     void delete(Museum museum);

     Veja o código 2 - executa a exclusão e obtém um inteiro indicando a exclusão que
     pode ser recebido no método 'onPostExecute' da classe 'AsyncTask' presente em
     nossa codificação na classe 'MuseumRepository' que talvez ainda não esteja codificada
     @Delete
     int deleteMuseum(Museum museum);

     Ele excluirá o objeto exato que é armazenado no banco de dados com os mesmos
     valores. 'Museum' é a classe de modelo que representa a entidade e 'museum' é
     o objeto.

     */
    @Delete
    int deleteMuseum(Museum museum);

    @Query("DELETE FROM museums WHERE museumName = :eraseMuseumName")
    void deleteMuseumName(String eraseMuseumName);

    @Query("DELETE FROM museums")
    void deleteAllMuseums();

}
