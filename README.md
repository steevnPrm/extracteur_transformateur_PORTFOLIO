# Data Morph — Extracteur & Transformateur de Flux

## Description

**Data Morph** est un service backend conçu avec **Spring Boot** et **Java 21**, permettant :

* L’ingestion de flux de données
* La validation selon des règles métier simples
* La séparation automatique des données valides et invalides
* La persistance sécurisée en base PostgreSQL
* L’audit des anomalies via une table dédiée

L’objectif est de simuler un **pipeline de traitement de données robuste**, proche de cas réels (logs, IoT, ETL léger).

---

## Stack Technique

| Domaine         | Technologie                 |
| --------------- | --------------------------- |
| Runtime         | Java 21 (LTS)               |
| Framework       | Spring Boot                 |
| API             | Spring Web (REST)           |
| Persistance     | Spring Data JPA / Hibernate |
| Base de données | PostgreSQL                  |
| Driver          | PostgreSQL JDBC             |
| Tests           | JUnit 5 / Mockito           |

---

## Workflow Métier

### 1. Ingestion

Réception ou génération d’objets contenant :

* `value` *(Integer)*
* `description` *(String)*

---

### 2. Validation & Transformation

Un item est considéré **valide** si :

* `value > 0`
* `description != null`
* l’objet lui-même n’est pas nul

Toute erreur technique ou violation entraîne un rejet contrôlé.

---

### 3. Persistance

| Type de donnée | Destination                              |
| -------------- | ---------------------------------------- |
| ✅ Valide       | Table principale (`item_entity`)         |
| ❌ Invalide     | Table d’audit (`item_validation_errors`) |

Les erreurs sont enrichies avec :

* Message : `"Log mal formatée"`
* Données brutes (traçabilité complète)

---

## Installation

### Prérequis

* JDK 21
* Maven 3.9+
* PostgreSQL (local ou Docker)

---

### Lancement

```bash
# Cloner le projet
git clone https://github.com/steevnPrm/extracteur_transformateur_PORTFOLIO

# Accéder au backend
cd extracteur_transformateur_PORTFOLIO/fluxExtractor

# Build + tests
mvn clean install

# Démarrage
mvn spring-boot:run
```

Application disponible sur :
`http://localhost:8080`

---

## Configuration

Exemple `application.yml` :

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/datamorph
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## API

### 🔹 Génération de flux (Batch Test)

**POST** `/api/items/batch-test`

* Génère **1000 items**
* Injecte volontairement des erreurs (`value = -100`)
* Teste simultanément :

  * validation
  * persistance
  * gestion d’erreurs

---

### 🔹 Récupération des données validées

**GET** `/api/items`

Retourne :

* Données nettoyées
* Fiabilisées
* Stockées en base principale

---

## Accès Base de Données

### Docker

```bash
docker exec -it db psql -U postgres -d datamorph
```

### Local

```bash
psql -U postgres -d datamorph
```

---

## Requêtes utiles

### Vérifier les données valides

```sql
SELECT count(*) FROM item_entity;
```

👉 Résultat attendu après batch : **~900**

---

### Vérifier les erreurs

```sql
SELECT error_message, raw_data 
FROM item_validation_errors 
LIMIT 5;
```

---

### Reset des tables

```sql
TRUNCATE TABLE item_entity, item_validation_errors;
```

---

## Tests

### Tests unitaires

* Validation métier
* Logique de filtrage
* Gestion des erreurs

### Tests d’intégration

* Simulation de flux massif
* Robustesse du traitement
* Persistance concurrente

---

## Objectifs du projet

* Démontrer une architecture backend propre
* Implémenter un pipeline de validation simple mais réaliste
* Gérer proprement les erreurs (audit + traçabilité)
* Tester la scalabilité sur des flux simulés

---

## Documentation

* Spring Boot : [https://docs.spring.io/spring-boot/index.html](https://docs.spring.io/spring-boot/index.html)
* Java 21 : [https://openjdk.org/projects/jdk/21/](https://openjdk.org/projects/jdk/21/)