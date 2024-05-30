### Задание


разработать Spring Boot Starter, который предоставит возможность логировать HTTP запросы в стороннем приложении на базе Spring Boot.


### Общая информация о приложении

Основная функция стартера – логирование входящих запросов, приходящих на эндпоинты основного приложения, и ответов на них. Данный функционал в стартере имеет в своей основе механизм Spring Interceptor. 
Для внедрения этого механизма в стартере был реализован интерфейс HandlerInterceptor с переопределением методов этого интерфейса: preHandle(), postHandle() – в стартере опущен, afterCompletion(). 

Вывод логов осуществляется в следующем формате:

INFO - 2024-05-30T02:54:39.043660500

HTTP LOG SET START

Тип запроса:                GET

URL:                        http://localhost:8080/api/v1/products

Параметры запроса:          null

ContentType запроса:        application/json

ContentType ответа:         application/json

Код статуса:                200

Заголовки запроса:          {content-length=164, postman-token=5567bc40-befc-4a2a-bd21-1739a7090db3, host=localhost:8080, content-type=application/json}

Заголовки ответа:           {Transfer-Encoding=chunked, Keep-Alive=timeout=60, Connection=keep-alive, Date=Wed, 29 May 2024 22:54:39 GMT, Content-Type=application/json}

Запрос пришел:              2024-05-30T02:54:39.010658800

Ответ отправлен:            2024-05-30T02:54:39.043660500

Запрос обработан за:        33 мс

HTTP LOG SET END

В стартере по умолчанию реализована функция одновременного вывода логов продуцируемых стартером в консоль и в файл с названием HTTPLOGGING.log с базовой настройкой записи. 
При необходимости формат вывода/записи логов можно настроить путем добавления в папку src/main/resources файла параметров с названием log4j.properties, 
в котором можно переопределить следующие свойства (даже если требуется изменить только один параметр, в файле нужно указать все эти параметры):

log4j.rootLogger = INFO, HTTPLOGGERCONSOLE, HTTPLOGGERFILE

log4j.appender.HTTPLOGGERFILE.layout=org.apache.log4j.SimpleLayout

log4j.appender.HTTPLOGGERFILE = org.apache.log4j.RollingFileAppender

log4j.appender.HTTPLOGGERFILE.File=HTTPLOGGING.log

log4j.appender.HTTPLOGGERFILE.MaxFileSize=1MB

log4j.appender.HTTPLOGGERFILE.MaxBackupIndex=3

log4j.appender.HTTPLOGGERCONSOLE=org.apache.log4j.ConsoleAppender

log4j.appender.HTTPLOGGERCONSOLE.threshold=INFO

log4j.appender.HTTPLOGGERCONSOLE.layout=org.apache.log4j.SimpleLayout

Также можно прописать дополнительные настройки параметров. 

Для проверки корректности работы стартера основная логика (содержится в классе интерсептора HttpLoggingInterceptor.class) покрыта unit-тестами.

### Запуск в стороннем приложении

Для включения функционала стартера по записи логов в стороннем приложении необходимо произвести следующие действия:
1) Добавить пакет стартера в локальный репозиторий

2) В pom.xml файл основного приложения в раздел dependencies добавить зависимость:

   <pre><code>&lt;dependency&gt;
    &lt;groupId&gt;com.alekseyz.testtask&lt;/groupId&gt;
    &lt;artifactId&gt;http-logger-spring-boot-starter&lt;/artifactId&gt;
    &lt;version&gt;0.0.1-SNAPSHOT&lt;/version&gt;
&lt;/dependency&gt;</code></pre>

3) В файле настроек основного приложения application.properties (application.yaml) добавить свойство:

http-logger-spring-boot-starter.enabled=true


Для демонстрации возможностей стартера в пакете проекта имеется отдельное приложение, которое представляет собой простое CRUD Spring Boot приложение, 
состоящее из контроллера, сервиса и вспомогательных классов. Сохранение объектов приложения осуществляется в коллекцию List сразу на уровне сервиса без использования репозитория и БД. 

Базовый набор адресов для взаимодействия с тестовым приложением:

GET           http://localhost:8080/api/v1/products

GET           http://localhost:8080/api/v1/products/1

POST         http://localhost:8080/api/v1/products

PUT           http://localhost:8080/api/v1/products/1

DELETE      http://localhost:8080/api/v1/products/1



### Быстрый запуск

A.	Основной вариант

1)	Склонировать репозиторий проекта

2)	С помощью терминала перейти в каталог стартера

3)	Набрать команду ниже и подождать окончания сохранения стартера в локальный репозиторий:
   
mvn clean install
5)	Далее перейти в корневой каталог проекта и набрать команду
   
docker-compose up

Б. Резервный вариант

1)	Склонировать репозиторий проекта

2)	С помощью терминала перейти в каталог стартера

3)	Набрать команду ниже и подождать окончания сохранения стартера в локальный репозиторий:
   
mvn clean install

5)	Перейти в каталог внешнего проекта и собрать тестовый проект
   
mvn clean package

6)	Далее выполнить
   
java -jar target/ crudapp-0.0.1-SNAPSHOT.jar


### Быстрое тестирование приложения

Для тестирования можно использовать браузер, postman, swagger-ui. Вариант для Postman: 

1) Запустить коллекцию Postman через одноименное приложение, результат – статус 200 по всем запросам
