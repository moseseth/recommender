## Product recommender

This tool recommends list of products to perspective customers.
The rules which govern what products a customer may choose are 
based upon answers to question that the customer has given.

There is two separate services inside: 
- frontend service (`recommender-ui`)
- backend service (`recommender-api`)

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for 
development and testing purposes

### Prerequisites

- Docker Desktop
- Login as username: `sandbox`  password: `seb2021`


### Installing

```
git clone https://github.com/moseseth/recommender.git
cd recommender/
docker-compose up -d
```

- [x] Backend runs as a spring boot app: http://localhost:8080
- [x] Frontend runs as an angular app: http://localhost:4200
- [x] Available endpoints:

```
* api/auth (POST) {username, password}
* api/products?age=18&income=1000&student=0 (GET)
```

Note:
```
+ access to /api/products is restricted, use Authorization: Bearer XXXX in header
```

### Running the tests
