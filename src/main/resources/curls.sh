curl http://localhost:8080/repeat/123/5 -X GET

curl http://localhost:8080/student/XXX_rogue_samurai_XXX -X GET

curl http://localhost:8080/student -X POST -H 'Content-Type: application/json' -d '{ "login":"bbb",  "name":"Sergey",   "age":51 }'

curl http://localhost:8080/student/abc -X GET

curl http://localhost:8080/student/bbb -X GET