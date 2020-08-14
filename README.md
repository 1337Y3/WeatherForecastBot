# Умный сервис прогноза погоды _(средний уровень сложности)_

Для реализации использован язык Kotlin, Spring Boot и библиотека TelegramBots
Интерфейс реализован в виде бота в Telegram

Данные о погоде возвращаются ответом на сообщение, содержащее местоположение или команду "/weather _*широта*_ _*долгота*_"
в формате:
        "По вашим координатам (_широта_, _долгота_) текущее время _время_,
         температура _градусов_ ºC, ощущается как _градусов_ ºC, 
         влажность _процентов_ %, давление _ед_ мм. рт. с.,
         _состояние (напр. дождь, снег)_"
         
## Принцип работы

При получении сообщения в Telegram, сервис считывает его тип и структуру.
Если находится соответствие команды/типа сообщения с возможными - отправляется запрос в 
API https://openweathermap.org/api/one-call-api для получения текущей погоды по заданным координатам.
Далее собирается сообщение и отправляется пользователю в ответ.

## Запуск

Для запуска приложения необходимо собрать jar-файл посредством gradle (gradle clean package) 
и запустить его с параметрами окружения, указанными в resources/application.yml (BOT_TOKEN, BOT_NAME, BOT_API_KEY)


Для разработки и запуска использовалась IntelliJ IDEA с установленным плагином EnvFile, позволяющим подключить к сборке файл, хранящий переменные окружения. 
Данный файл добавлен в .gitignore во избежание использования токенов злоумышленниками.
