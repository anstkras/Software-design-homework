Start CLI: `./gradlew run`

##### 1. 
Для парсинга комманд был выбран ANTLR, что заметно повлияло на архитектуру, так как для формирования команд используется сгенерированный `visitor` дерева разбора. Команды формируются с помощью `CommandBuilder`, чтобы иметь возможность проставлять потоки ввода и вывода уже при работе `Pipeline`. Для подстановки переменных реализован отдельных `Substitutor`. Таким образом, `CommandLineInterpreter` читает строки с консоли, делает подстановку с помощью `Substitutor`, затем с помощью `visitor` для дерева разбора формирует команды в `Pipeline`(в случае, если это не присвоение). `Pipeline` отвечает за работу с потоками ввода/вывода у команд и за их последовательное выполнение.

##### 2. Рассмотренные библиотеки для парсинга аргументов grep:
 
  1. [Clikt](https://ajalt.github.io/clikt/): 
      * понятный и очень краткий синтаксис, много возможностей(ненужных в этом задании)
      * довольно полная документация(представленная в виде отдельного сайта)
      * почти 1000 звездочек на гитхабе.
  2. [Kotlin-argparser](https://github.com/xenomachina/kotlin-argparser): 
      * похожа по синтаксису на предыдущую библиотеку, но он не такой продуманный и краткий
      * В read.me написано "This library should be considered to be very beta."
      * Нет такой удобной и полной документации
      * 387 звездочек на гитхабе
  3. [kotlin.cli](https://github.com/Kotlin/kotlinx.cli)
      * Еще меньше документации и звездочек на гитхабе
 
 Была выбрана библиотека Clikt, так как благодаря полной документации после быстрого просмотра стало понятно, что ее возможностей точно хватит для реализации задания. Кроме того, ее синтаксис более краток и понятен, чем у рассмотренных конкурентов. 
