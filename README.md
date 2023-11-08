# lab1.1-dev

docker network create lab1_net

docker run --name mysql -p 3307:3306 -e MYSQL_USER=taquang -e MYSQL_PASSWORD=123456 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=labtest --net lab1_net mysql:latest

docker build -t dream/lab .

docker run -p 8000:8080 --net lab1_net dream/lab

docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.5 start-dev
