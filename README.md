#sicredi-votacao 

#instalando as dependencias
```
./gradlew build
```
#buildando imagem do docker

```
sudo docker build -t voting-api:0.0.1 .
```

#iniciando aplicação

```
sudo docker-compose up 
```

#criando uma agenda
<h5>http://localhost:8080/agenda </h5>

```
{
    "title": "Voce ja usou Pix?"
}
```

#criando uma sessão
<h5>http://localhost:8080/agendas/{agendaId}/sessions </h5>
<p>com a agenda já criada, recupere o identificador e substitua no agendaId</p>

```
{
    "minutes": 1
}
```

#Votando
<h5>http://localhost:8080/sessions/{sessionId}/votes </h5>
<p>com a session criada, recupere o identificador e substitua no sessionId</p>

```
{
    "cpf": "1322",
    "answer": "NÃO"
}
```

#Avaliando Resultado
<h5>http://localhost:8080/agendas/{agendaId} </h5>
para recuperar o resultado, utilize o identificador da agenda



