
# Interface Path

Um objeto que pode ser usado para localizar um arquivo em um sistema de arquivos. Normalmente representará um caminho de arquivo dependente 
do sistema.

Um **Path** representa um caminho hierárquico e composto por uma sequência de elementos de diretório e nome de arquivo
separados por um separador ou delimitador especial. Um componente raiz, que identifica uma hierarquia do sistema de arquivos,
também pode estar presente. O elemento name que está mais distante da raiz da hierarquia de diretórios é o nome de um arquivo ou diretório.
Os outros elementos de nome são nomes de diretório. Um Caminho pode representar uma raiz, uma raiz e uma sequência de nomes, ou simplesmente um 
ou mais elementos de nome. Um Path é considerado um caminho vazio se consistir apenas em um elemento de nome vazio. Acessar um arquivo usando um
caminho vazio equivale a acessar o diretório padrão do sistema de arquivos. Path define os métodos **getFileName**, **getParent**, **getRoot** e **subpath**
para acessar os componentes do caminho ou uma subsequência de seus elementos de nome.

Além de acessar os componentes de um caminho, um Path também define os métodos **resolve** e **resolveSibling** para combinar caminhos. 
O método **relativize** que pode ser usado para construir um caminho relativo entre dois caminhos. Os caminhos podem ser comparados e testados entre si 
usando os métodos **startWith** e **endsWith**.

Esta interface estende a interface **Watchable** para que um diretório localizado por um caminho possa ser registrado com um **WatchService** e entradas no diretório monitoradas.

**AVISO**: Esta interface destina-se apenas a ser implementada por aqueles que desenvolvem implementações de sistemas de arquivos personalizados. 
Métodos podem ser adicionados a esta interface em versões futuras.

### Acessando arquivos

Os caminhos podem ser usados com a classe Files para operar em arquivos, diretórios e outros tipos de arquivos. Por exemplo, suponha que queremos que um BufferedReader leia texto de um arquivo "access.log". O arquivo está localizado em um diretório "logs" relativo ao diretório de trabalho atual e é codificado em UTF-8.

```Path path = FileSystems.getDefault().getPath("logs", "access.log");```
```BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);```

Interoperabilidade

Os caminhos associados ao provedor padrão geralmente são interoperáveis com a classe java.io.File. É improvável que os caminhos criados por outros provedores sejam interoperáveis com os nomes de caminhos abstratos representados por java.io.File. O método toPath pode ser usado para obter um Path a partir do nome do caminho 
abstrato representado por um objeto java.io.File. O caminho resultante pode ser usado para operar no mesmo arquivo que o objeto java.io.File. Além disso, o método toFile é útil para construir um Arquivo a partir da representação String de um Caminho.

Os caminhos associados ao [provedor padrão](https://docs.oracle.com/javase/8/docs/api/java/nio/file/spi/FileSystemProvider.html) geralmente são interoperáveis com a classe java.io.File. É improvável que os caminhos criados por outros provedores sejam interoperáveis com os nomes de caminhos abstratos representados por java.io.File. O método toPath pode ser usado para obter um Path 
a partir do nome do caminho abstrato representado por um objeto java.io.File. O caminho resultante pode ser usado para operar no mesmo arquivo que o objeto java.io.File. Além disso, o método toFile é útil para construir um Arquivo a partir da representação String de um Caminho.

| Tipo de retorno  | Método e descrição                                                                                                                                                       |
|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `int`            | `compareTo(Path other)`<br/>Compara dois caminhos abstratos lexicograficamente.                                                                                            |
| `boolean`        | `endsWith(Path other)`<br/>Testa se este caminho termina com o caminho fornecido.                                                                                          |
| `boolean`        | `endsWith(String other)`<br/>Testa se este caminho termina com um Path, construído pela conversão da string de caminho fornecida, exatamente da maneira especificada pelo<br/>`endsWith(Path)` método. |
| `Path`           | `getFileName()` <br/>Retorna o nome do arquivo ou diretório denotado por este caminho como um objeto Path.                                                               |
| `FileSystem`     | `getFileSystem()`<br/>Retorna o sistema de arquivos que criou este objeto.                                                                                               |
| `Path`           | `getName(int index)`<br/>Retorna um elemento de nome deste caminho como um objeto Path.                                                                                       |
| `int`            | `getNameCount()`<br/>Retorna o número de elementos de nome no caminho.                                  |
| `Path`           | `getParent()`<br/>Retorna o caminho pai ou `null` se este caminho não tiver um pai.                  |
| `Path`           | `getRoot()`<br/>Retorna o componente raiz deste caminho como um objeto Path, ou `null` se não tiver. |
| `int`            | `hashCode()`Calcula um código hash para este caminho.                                             |
| `boolean`        | `isAbsolute()`<br/>Indica se este caminho é absoluto ou não.                                             |
| `Iterator<Path>` | `iterator()`<br/>Retorna um iterador sobre os elementos de nome deste caminho.                         |
| `Path`           | `normalize()`<br/>Retorna um caminho com os elementos de nome redundantes eliminados.                   |
| `WatchKey`       | `register(WatchService watcher, WatchEvent.Kind<?>... events)`<br/>Registra o arquivo localizado por este caminho em um serviço de observação.           |
