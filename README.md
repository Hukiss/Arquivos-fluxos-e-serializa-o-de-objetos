# Arquivos, fluxos e serialização de objetos

## 15.1 Introdução

Dados armazenados em variáveis e arrays são temporários - eles são perdidos quando uma variável local "sai do escopo" ou quando o programa termina.
Para retenção de longo prazo dos dados, mesmo depóis de os programas terminarem a sua execução, computadores usam Arquivos.

Computadores armazenam arquivos em dispositivos de armazenamento secundário, incluindo discos rígidos, pen-drive, DVD's, etc. Os dados mantidos nos 
arquivos são dados persistentes - eles contínuam existindo mesmo depóis da execução do programa. Os dados podem ser armazenados em arquivos de texto e arquivos binários.
Para fazer tais armazenamentos o Java usa as classes Paths e Files, e também as interfaceis Path e DirectoryStream(todos do pacote java.nio.file).

## Arquivos e fluxos

O Java vê cada arquivo como um fluxo de bytes sequencial. Cada sistema operativo fornece um mecanismo
para determinar o fim de um arquivo, como um marcador de fim de arquivo ou uma contagem dos bytes totais no arquivo que é
registrado em uma estrutura de dados administrativa mantida pelo sistema. Um programa Java que processa um fluxo de bytes sim-
plesmente recebe uma indicação do sistema operativo quando ele alcança o fim desse fluxo — o programa não precisa saber como
a plataforma subjacente representa arquivos ou fluxos. Em alguns casos, a indicação de fim de arquivo ocorre como uma exceção.
Em outros, a indicação é um valor de retorno de um método invocado sobre um objeto que processa o fluxo.

### Fluxos baseados em caracteres e em bytes

Fluxos de arquivos podem ser utilizados para entrada e saída de dados como bytes ou caracteres.

    Fluxos baseados em bytes geram e inserem dados em um formato binário — um char tem dois bytes, um int tem quatro
    bytes, um double tem oito bytes etc.

    Fluxos baseados em caracteres geram e inserem dados como uma sequência de caracteres na qual cada caractere tem
    dois bytes — o número de bytes para determinado valor depende do número de caracteres nesse valor. Por exemplo, o valor
    2000000000 requer 20 bytes (10 caracteres a dois bytes por caractere), mas o valor 7 só demanda dois bytes (um caractere a dois
    bytes por caractere).

Arquivos criados com base nos fluxos de bytes são chamados arquivos binários, e aqueles criados com base nos fluxos de carac-
teres são arquivos de texto. Estes últimos podem ser lidos por editores de texto, enquanto os primeiros, por programas que entendem
o conteúdo específico do arquivo e seu ordenamento. Um valor numérico em um arquivo binário pode ser usado em cálculos, ao
passo que o caractere 5 é simplesmente um caractere que pode ser utilizado em uma string de texto, como em "Sarah Miller is
15 years old".

### Fluxos de entrada, saída e erro padrão

Um programa Java abre um arquivo criando e associando um objeto ao fluxo de bytes ou de caracteres. O construtor do objeto
interage com o sistema operacional para abrir esse arquivo. O Java também pode associar fluxos a diferentes dispositivos. Quando
um programa Java começa a executar, ele cria três objetos de fluxo que estão relacionados com dispositivos — System.in, System.
out e System.err. O objeto System.in (fluxo de entrada padrão) normalmente permite que um programa insira bytes a partir do
teclado. Já o objeto System.out (o objeto de fluxo de saída padrão), em geral, possibilita que um programa envie caracteres para
a tela. Por fim, o objeto System.err (o objeto de fluxo de erro padrão) na maioria das vezes autoriza que um programa gere men-
sagens de erro baseadas em caracteres e as envie para a tela. Cada fluxo pode ser redirecionado. Para System.in, essa capacidade
libera o programa a fim de ler bytes a partir de uma origem diferente. Para System.out e System.err, ele permite que a saída
seja enviada a um local diferente, como a um arquivo em disco. A classe System fornece os métodos setIn, setOut e setErr para
redirecionar os fluxos de entrada, saída e erro padrão, respectivamente.

### Pacotes java.io e java.nio

Programas Java executam o processamento baseado em fluxo com classes e interfaces do pacote java.io e subpacotes do java.
nio — novas APIs de E/S do Java introduzidas pela primeira vez no Java SE 6 e que desde então foram aprimoradas. Há também
outros pacotes por todas as APIs Java que contêm classes e interfaces baseadas naquelas dos pacotes java.io e java.nio.
A entrada e saída baseada em caracteres pode ser realizada com as classes Scanner e Formatter, como veremos na Seção 15.4.
Você usou a classe Scanner extensivamente para inserir dados a partir do teclado. Scanner também pode ler dados de um arquivo.
A classe Formatter permite que a saída de dados formatados seja enviada para qualquer fluxo baseado em texto de uma maneira
semelhante ao método System.out.printf.

## 15.3 Usando classes e interfaces NIO para obter informações de arquivo e diretório

As interfaces Path e DirectoryStream e as classes Paths e Files (todas do pacote java.nio.file) são úteis para recuperar
informações sobre arquivos e diretórios no disco:

    Interface Path — os objetos das classes que implementam essa interface representam o local de um arquivo ou diretório. Objetos
    Path não abrem arquivos nem fornecem capacidades de processamento deles.

    Classe Paths — fornece os métodos static utilizados para obter um objeto Path representando um local de arquivo ou diretório.

    Classe Files — oferece os métodos static para manipulações de arquivos e diretórios comuns, como copiar arquivos; criar e
    excluir arquivos e diretórios; obter informações sobre arquivos e diretórios; ler o conteúdo dos arquivos; obter objetos que permitem
    manipular o conteúdo de arquivos e diretórios; e mais.
    
    Interface DirectoryStream — os objetos das classes que implementam essa interface possibilitam que um programa itere pelo
    conteúdo de um diretório.

### Criando objetos Path

Você usará a classe static do método **get** da classe Paths para converter uma String que representa o local de um arquivo ou diretório em um Path.
Você pode então usar os métodos da interface Path e classe Files para determinar informações sobre o arquivo ou diretório especificado.

    htt://docs.oracle.com/javase/7/docs/api/java/nio/file/Path.html
    http://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html

### caminhos absolutos versus relativos

Um caminho de arquivo ou diretório especifica sua localização em disco. Ele inclui alguns ou todos os principais diretórios
que levam ao arquivo ou diretório. Um caminho absoluto contém todos os diretórios, desde o diretório raiz, que encaminha a um
arquivo ou diretório específico. Cada arquivo ou diretório em uma unidade de disco particular tem o mesmo diretório-raiz em seu
caminho. Um caminho relativo é “relativo” a outro diretório; por exemplo, um caminho relativo para o diretório em que o aplicativo
começou a executar.

### Obtendo objetos Path de URIs

versão sobrecarregada do método static Files usa um objeto URI para localizar o arquivo ou diretório. Um Uniform Resource Identifier (URI) 
é uma forma mais geral dos Uniform Resource Locators (URLs) que são utilizados para pesquisar sites na web.
Por exemplo, o URL <http://www.deitel.com/> direciona para o site da Deitel & Associates. Os URIs para encontrar
arquivos variam em diferentes sistemas operacionais. Em plataformas Windows, o URI
        
    file://C:/data.txt

identifica o arquivo data.txt armazenado no diretório-raiz da unidade C:. Em plataformas UNIX/Linux, o URI

    file:/home/student/data.txt

identifica o arquivo data.txt armazenado no diretório home do usuário student.

### Exemplo: obtendo informações de arquivo e diretório

A Figura 15.2 solicita que o usuário insira um nome de arquivo ou diretório, então usa as classes Paths, Path, Files e
DirectoryStream para produzir informações sobre esse arquivo ou diretório. O programa começa solicitando ao usuário um arqui-
vo ou diretório (linha 16). A linha 19 insere o nome de arquivo ou diretório e o passa para o método Paths static get, que converte
a String em um Path. A linha 21 invoca o método Files static exists, que recebe um Path e determina se ele existe (como um
arquivo ou como um diretório) no disco. Se o nome não existir, o controle passa para a linha 49, que exibe uma mensagem contendo
a representação String de Path seguida por “does not exist”. Caso contrário, as linhas 24 a 45 executam:
        
    • O método Path getFileName (linha 24), que recebe o nome String do arquivo ou diretório sem nenhuma informação sobre o
    local.

    •O método Files static isDirectory (linha 26), que recebe um Path e retorna um boolean indicando se esse Path repre-
    senta um diretório no disco.

    •O método Path isAbsolute (linha 28), que retorna um boolean indicando se esse Path representa um caminho absoluto para
    um arquivo ou diretório.

    •O método Files static getLastModifiedTime (linha 30), que recebe um Path e retorna um FileTime (pacote java.nio.
    file.attribute), indicando quando o arquivo foi modificado pela última vez. O programa gera uma saída da representação
    String padrão de FileTime.

    •O método Files static size (linha 31), que recebe um Path e retorna um long representando o número de bytes no arquivo
    ou diretório. Para diretórios, o valor retornado é específico da plataforma.

    •O método Path toString (chamado implicitamente na linha 32), que retorna uma String representando o Path.

    •O método Path toAbsolutePath (linha 33), que converte o Path em que ele é chamado para um caminho absoluto.

Se o Path representa um diretório (linha 35), as linhas 40 e 41 usam o método Files static newDirectoryStream (linhas
40 e 41) para obter um DirectoryStream<Path> contendo os objetos Path ao conteúdo do diretório. As linhas 43 e 44 exibem
a representação String de cada Path em DirectoryStream<Path>. Observe que DirectoryStream é um tipo genérico como
ArrayList (Seção 7.16).
A primeira saída desse programa demonstra um Path para a pasta que contém os exemplos deste capítulo. Já a segunda saída
aponta um Path para o arquivo de código-fonte desse exemplo. Nos dois casos, especificamos um caminho absoluto.