## Product Recommender

This tool recommends list of products to perspective customers.
The rules which govern what products a customer may choose are 
based upon answers to question that the customer has given.

There is two separate services inside: 
- frontend service (`recommender-ui`)
- backend service (`recommender-api`)

```
Backend runs as a spring boot app: http://localhost:8080
Frontend runs as an angular app: http://localhost:4200
```
```
Available endpoints:

* api/auth (POST) {username, password}
* api/products?age=18&income=1000&student=0 (GET) 

N.B. > uses for query string: student - (yes - 1) and (no - 0)
     > access to /api/products is restricted, use Authorization: Bearer XXXX 
```

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for 
development and testing purposes

### Prerequisites

``` 
Docker Desktop
Use login credentials -> username: sandbox  password: seb2021
```
    

### Installing

```
cd recommender/
docker-compose up -d
```

### Running the tests
