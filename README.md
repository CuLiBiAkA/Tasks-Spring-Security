# Tasks-Spring-Security

В задаче с рест контролерами, проведя исследования, я выяснил, что ебал я врот все это и хочу к маме. Но все таки
получилось, используя библиотеку Mapstruct, замутить методы преобразования Entity в DTO и обратно. Теперь мое Полу REST
Legacy приложение может общаться с миром живых и мертвых. Для того что бы получить список юзеров нужно использовать
метод GET в постмане стучась по URL: http://localhost:8080/admin1/users/
Для того что бы получить одного юзера из базы нужно использовать ГЕТ и стучаться по
УРЛ: http://localhost:8080/admin1/users/1 (1-это id первого юзера)
Для того что бы создать нового юзера, нужно отправить POST запрос по УРЛ http://localhost:8080/admin1/users/new и
отравить туже форму что пришла ответом из GET запроса, только со своими данными. Так же можно изменить юзера методом PUT
по адресу: http://localhost:8080/admin1/users/1 (1-это id первого юзера), так же нужно отправить форму с изменениями.
Еще можно удалить по запросу дел. Но этот запрос вообще не менялся.

Пример ответа на GEN запрос по адресу: http://localhost:8080/admin1/users/1 (1-это id первого юзера)

```
{
"id": 1,
"name": "PIDOR",
"password": "$2a$12$WD5mWochtngLH/Sw5ORj3u5zs8EtHf4NwD3wLnPHg8vSQkE7nZTpa",
"penisSize": 9,
"drove": "CMOSHA",
"roles": [
{
"id": 1,
"name": "ROLE_ADMIN",
"authority": "ROLE_ADMIN"
}
]
}
```
![](spring_security-master/src/main/resources/static/228.gif)