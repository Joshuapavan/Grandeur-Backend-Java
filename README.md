# Grandeur-Backend

Repo for Grandeur Backend, which is implemented using Spring Boot and provides Data to the client over api calls.

Prerequisites to Run The Backend.

1. Have no servers running on port 8080
2. set the username and password for the Database in application.properities(path : src->main->resources->application.properities.)
3. Create a database for the CRUD operations to happen.

API End points : 
Registration/ Signup -> (POST) http://localhost:8080/api/v1/registration

Client APIs -> (GET/POST) http://localhost:8080/api/v1/clients
Client APIs -> (GET/PATCH) http://localhost:8080/api/v1/clients/{id}

Cars API -> (GET/POST) http://localhost:8080/api/v1/cars or http://localhost:8080/api/v1/cars/{id}
Cars API -> (GET) http://localhost:8080/api/v1/cars/{name} (name = brandName)
         
Car Images API -> (POST) http://localhost:8080/api/v1/cars/{id}/images 

Features to implemented.

1. Apply Relationships between car and client entities.
2. Unit Tests.
