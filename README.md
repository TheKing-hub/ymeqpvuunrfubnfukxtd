Описание проекта:
Настройки проекта для windows x64:
1) Java - версия 17
2) PostgreSQL - версия 15.4.1.
   Логин:  postgres
   Пароль: 1234qp
   Имя базы данных внутри PostgreSQL - postgres
   Схема (Schema) - public
3) MongoDB Community Server - версия 7.0.3.
   Без логина и пароля
4) Gradle - версия 8.4.
5) SDK - openjdk-20 Oracle OpenJDK version 20.0.1


В моем проекте в папке main в com.example.ymeqpvuunrfubnfukxtd 
лежат следующие папки:
1) controller:
Тут 2 контроллера, 
ControllerPostgreSQL привязан к PostgreSQL, 
ControllerMongoDB к MongoDB.
a) В первом контроллере ControllerPostgreSQL:
Через адрес http://localhost:8080/call_one мы подключаемся к данному контроллеру. В нем описаны следующие методы:

http://localhost:8080/call_one/add
Метод addCallOne, который на вход принимает DTO объекта CallOne. Конвертирует CallOneDTO в CallOne и затем после добавления возвращает строку что добавление прошло успешно, если добавление прошло успешно.

http://localhost:8080/call_one/get/{id}
Метод getCallOne, который на вход принимает pathvariable id. Возвращает объект по айдишке

http://localhost:8080/call_one/all
Метод getAllCallObjects просто возвращает все объекты из БД

http://localhost:8080/call_one/update/{{id}}
Метод updateCallObjectById по айдишке находит объект и по ДТО обновляет объект

http://localhost:8080/call_one/delete/{{id}}
Метод deleteCallObjectById удаляет по айдишке объект в БД

http://localhost:8080/call_one
Метод getCallObjects  при помощи объекта Filter получает необходимые записи в БД

b) Во втором контроллере ControllerMongoDB аналогично, но вместо айдишки находим объекты по первому номеру телефона.


2) exception:
Здесь хранятся необходимые exception для нестандартных вводов.
EntityNotFoundException - запускается когда пользователь пытаеся ввести данные, через которые нельзя получить 
нужную информацию в БД.
IncorrectCallObject - запускается когда пользователь вводит неправильные данные (номер телефона с цифрой, дата левая и др.)
ObjectCannotBeAdded - запускается, если не получилось добавить объект в БД

3) model:
Здесь лежат 2 папки:
а) entity:

BaseEntity:
Базовая сущность с готовой айдишкой для других сущностей. Чтобы не добавлять айди постоянно, просто наследуемся от 
BaseEntity (для PostgreSQL)

CallOne:
Сущность для работы с PostgreSQL с полями 
name (String) - имя пользователя
year (Integer) - год рождения
phoneOne (String) - первый номер телефона
phoneTwo (String) - второй номер телефона
creationDate(String) - дата создания

pgAdmin автоматически создает в схеме public таблицу postgres

CallOneMongo:
Сущность для работы с MongoDB с теми же полями, что и CallOne за исключение id, тут String id.
MongoDB создает таблицу call_mongo

b) service_dto

CallOneDTO:
DTO для сущности CallOne, те же поля что и у CallOne, но без айдишки

CallOneMongoDTO:
DTO для сущности CallOneMongo, те же поля что и у CallOneMongo, но без айдишки

Filter:
Класс с полями limit (количество записей на показ) и offset (начинается с 0 и показывает через сколько записей показать limit записей)


4) repository:
a) CallOneMongoRepository - репозиторий для хранения и манипулирования данными из MongoDB
b) CallOneRespository - репозиторий для хранения и манипулирования данными из PostgreSQL


5) service:
implementation.model_service_impl хранит в себе методы для использования в контроллере. Наследуются от интефейсов в пакете model_service.
CallOneMongoServiceImpl в implementation.model_service_impl наследуется от CallOneMongoService в model_service
CallOneServiceImpl в implementation.model_service_impl наследуется от CallOneService в model_service

Тут идет проверка от неправильных данных на имя, год, телефон и дату создания. Подробное описание внутри проекта в комментариях о методах.



В папке src -> test в .java файле ymeqpvuunrfubnfukxtdApplicationTests лежат 3 теста на проверку номера телефона, имени и даты



Как запустить проект:

В cmd прописать git clone ***url***. Устанавливаем все настройки, которые я описал выше. Запускаем проект в main, разворачиваем postman файл


















