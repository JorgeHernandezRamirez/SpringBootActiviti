curl -u admin:admin -H "Content-Type: application/json" -d "{\"userId\":\"1\",\"name\":\"John Doe\", \"email\": \"john.doe@alfresco.com\", \"phone\":\"123456789\"}" http://localhost:8080/start-interview
curl -u admin:admin http://localhost:8080/runtime/tasks
curl -u admin:admin -H "Content-Type: application/json" -d "{\"action\" : \"complete\", \"variables\": [ {\"name\":\"telephoneInterviewOutcome\", \"value\":true} ]}" http://localhost:8080/runtime/tasks/17
curl -u admin:admin http://localhost:8080/runtime/tasks
curl -u admin:admin -H "Content-Type: application/json" -d "{\"action\" : \"complete\", \"variables\": [ {\"name\":\"techOK\", \"value\":true} ]}" http://localhost:8080/runtime/tasks/28
curl -u admin:admin -H "Content-Type: application/json" -d "{\"action\" : \"complete\", \"variables\": [ {\"name\":\"finantialOK\", \"value\":true} ]}" http://localhost:8080/runtime/tasks/31
