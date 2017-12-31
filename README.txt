sdfsdsdfTechnology Stack:

Spring boot
Spring data repository
Mockito
Java 8
Maven


Notes:

- Created a restful Api that provides endpoints for returning list of cakes in json format and adding a new cake:

  1- For getting list of cakes in json please use following urls:

http://localhost:8080/cakes  
http://localhost:8080/

2- For find a cake of particular name

http://localhost:8080/cakes/<name>

 3 - For adding cake please use following url

url : http://localhost:8080/cakes
method : POST
json sample request format
{ 
  "name" : "cheese cake",
   "description" : "This is cheese cake",
   "image"  : "http:imageurl/image"
}  

The above endpoint create a new cake entry and on successful create return the status code 200 along with the list of all the cakes
including the newly created cake.

Things assumed not to be required for this test:
- logger implementation
- Sending a customized status code in response if user tries to add a cake with the title already present. currently it returns 500.

