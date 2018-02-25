Technology Stack:

Java 8
Spring boot
Spring data repository
Spring REST API
Angular JS
Mockito
Maven


Notes:

- Created a restful Api that provide endpoints for returning list of cakes in json format and adding a new cake:

- Created a basic UI for getting list of cakes and adding a new cake (please use the following url to access)

http://localhost:8080/cakes  
http://localhost:8080/

- The above will direct to the web page that list the cakes in the system and the user can also add a new cake.

- When the user add a cake, the list is updated to reflect the new entry and the message is displayed 'Cake successfully added'

- There are basic validations in place . e.g. The Add button will be only enable when the user inputs all the three 
required fields. 

- In addition the lengh of the text fields are restricted to the maximum length of the 
attributes(the field maps to) in the database.

- In addition the UI displays error message if the cake with the name already exist in the database. 

- The previous version of the code is refactored to decouple the CakeDto that represents the json from the model
(Cake entity) and hence Converter is added to map dto to enitity and vice versa.
- In addition the api returns an error code if the user tried to add cake with the name already exist in the database.

Things assumed not to be required for this test:
- logger implementation

