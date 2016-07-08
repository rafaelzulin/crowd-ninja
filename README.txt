Environment
- Maven 3.3.9
- JDK 1.8u74

Run
- mvn clean install jetty:run

Services
*List users
curl -X GET http://localhost:8080/rest/UserServices/users
*Describe User
curl -X GET http://localhost:8080/rest/UserServices/users/{id}
*Create User
curl -X PUT --data "id=2&name=Margaret Thatcher" http://localhost:8080/rest/UserServices/users
*Update User
curl -X POST --data "id=2&name=Winston Churchill" http://localhost:8080/rest/UserServices/users
*Delete User
curl -X DELETE http://localhost:8080/rest/UserServices/users/{id}
*Options
curl -X OPTIONS http://localhost:8080/rest/UserServices/users