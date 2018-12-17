# micronaut-serverless-example code from infoq article

https://www.infoq.com/articles/micronaut-tutorial-microservices-jvm
https://www.infoq.com/articles/micronaut-tracing-security-serverless

Notes for Windows:

To get oauth token:

```
curl -H "Content-Type: application/json" -d "{\"username\": \"sherlock\", \"password\": \"elementary\"}" http://localhost:8080/login
```

Test API with bearer token:

```
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGVybG9jayIsIm5iZiI6MTU0NDk4MzY2NCwicm9sZXMiOltdLCJpc3MiOiJnYXRld2F5IiwiZXhwIjoxNTQ0OTg3MjY0LCJpYXQiOjE1NDQ5ODM2NjR9.lROH2m-Rf-CcxN0QUS-aEGMFm8kkcBOd3VPlba5FTpw" http://localhost:8080/api/books
[{"isbn":"1491950358","name":"Building Microservices","stock":2},{"isbn":"1680502395","name":"Release It!","stock":3}]
```

