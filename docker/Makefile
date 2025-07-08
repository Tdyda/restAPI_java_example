up:
	docker compose up -d

build:
	docker compose build

down:
	docker compose down

bash:
	docker exec -it $(shell docker compose ps -q php) bash

db:
	docker exec -it mysql mysql -u tdyda -p

composer:
	docker exec -it $(shell docker compose ps -q php) composer $(filter-out $@,$(MAKECMDGOALS))

.PHONY: up down bash composer
