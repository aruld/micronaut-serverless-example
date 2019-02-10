# micronaut-serverless-example code from infoq article

https://www.infoq.com/articles/micronaut-tutorial-microservices-jvm

https://www.infoq.com/articles/micronaut-tracing-security-serverless


Micronaut services:

- books (groovy)
- inventory (kotlin)
- gateway (java)
- isbn-validator (aws lambda function)

Servers:

- consul agent -dev
- java -jar zipkin.jar

- books> gradlew -parallel run
- inventory> gradlew -parallel run
- gateway> gradlew -parallel run

Notes for Windows:

To get oauth token:

```
curl -H "Content-Type: application/json" -d "{\"username\": \"sherlock\", \"password\": \"elementary\"}" http://localhost:8080/login
{"username":"sherlock","access_token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGVybG9jayIsIm5iZiI6MTU0OTc3NTM4MSwicm9sZXMiOltdLCJpc3MiOiJnYXRld2F5IiwiZXhwIjoxNTQ5Nzc4OTgxLCJpYXQiOjE1NDk3NzUzODF9.g89q7OI47S97ZzXDyy3HYZTN2bQScpO8vwy0Giwdrnw","refresh_token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGVybG9jayIsIm5iZiI6MTU0OTc3NTM4MSwicm9sZXMiOltdLCJpc3MiOiJnYXRld2F5IiwiaWF0IjoxNTQ5Nzc1MzgxfQ.ycF_6wgZO7eOwHAHsfb-WcL92TMo_wUajIcNSLsOZk4","expires_in":3600,"token_type":"Bearer"}
```

Test API with bearer token:

```
curl -H "Authorization: Bearer <access_token>" http://localhost:8080/api/books
[{"isbn":"1491950358","name":"Building Microservices","stock":2},{"isbn":"1680502395","name":"Release It!","stock":3}]
```

Test using Apache Bench:

```
ab -v 2 -H "Authorization: Bearer <access_token>" -c 10 -n 20 http://localhost:8080/api/books
```

Test isbn-validator lambda from AWS CLI:

```
aws lambda invoke --function-name isbn-validator --region us-east-1 --payload file://.\payload1.json outfile1.json
aws lambda invoke --function-name isbn-validator --region us-east-1 --payload file://.\payload2.json outfile2.json
```

JWT Payload:

{
  "sub": "sherlock",
  "nbf": 1545103990,
  "roles": [],
  "iss": "gateway",
  "exp": 1545107590,
  "iat": 1545103990
}