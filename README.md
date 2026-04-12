# Data Morph — Extracteur & Transformateur de Flux

## Description
**Data Morph** est un service backend conçu avec **Spring Boot** et **Java 21**, permettant l’ingestion, la validation et la persistance de flux de données avec un audit complet des anomalies.

---

## Stack Technique
* **Runtime :** Java 21 (LTS)
* **Framework :** Spring Boot 3.x
* **Base de données :** PostgreSQL
* **Conteneurisation :** Docker / Docker Compose

---

## Installation et Lancement

### 1. Prérequis
* Docker & Docker Compose installés.
* (Optionnel) JDK 21 et Maven 3.9+ pour le développement local.

### 2. Démarrage avec Docker Compose
Le projet inclut une configuration prête à l'emploi pour lancer l'application et sa base de données PostgreSQL.

```bash
# Cloner le projet
git clone https://github.com/steevnPrm/extracteur_transformateur_PORTFOLIO
cd fluxExtractor

# Lancer l'infrastructure (App + DB)
docker-compose up -d --build
```
L'application sera accessible sur `http://localhost:8080`.

---

## Utilisation du Pipeline

### 1. Déclencher le flux ETL
Pour lancer le traitement (ingestion, validation et stockage), utilisez la commande suivante :

```bash
curl -X POST http://localhost:8080/api/items/run \
     -H "Content-Type: application/json"
```

### 2. Vérification des résultats
Vous pouvez vérifier le succès de l'opération de deux manières :

#### A. Via les logs de l'application
```bash
docker-compose logs -f app
```

#### B. Via l'accès direct à la base de données (Docker)
Connectez-vous au conteneur PostgreSQL pour interroger les tables :

```bash
# Accès au CLI PostgreSQL
docker exec -it db psql -U myuser -d fluxextractor_db

# Vérifier les items valides
SELECT count(*) FROM item_entity;

# Vérifier les erreurs d'audit
SELECT error_message, raw_data FROM item_validation_errors LIMIT 10;
```

---

## Validation Métier
Chaque item entrant passe par un filtre rigoureux :
* **Validation :** `value > 0` et `description` non nulle.
* **Succès :** Persistance dans `item_entity`.
* **Échec :** Capture de la donnée brute et du motif de l'erreur dans `item_validation_errors`.

---

## Ressources Utiles
* [Documentation Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [Docker Compose Specification](https://docs.docker.com/compose/compose-file/)
