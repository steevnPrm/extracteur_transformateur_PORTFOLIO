# Data Morph : Extracteur & Transformateur de Flux

## Description

**Data Morph** est un service backend développé avec **Spring Boot** et **Java 21**. Il permet l'ingestion de données brutes au format JSON, leur nettoyage (validation de types, mise en conformité) et leur persistance automatisée dans une base de données relationnelle.

## Stack Technique

* **Runtime :** Java 21 (LTS)
* **Framework :** Spring Boot  
* **Web :** Spring Web (REST API)  
* **Validation :** Spring Boot Starter Validation (Bean Validation)  
* **Persistance :** Spring Data JPA / Hibernate  
* **Base de données :** PostgreSQL  
* **Driver DB :** PostgreSQL JDBC Driver  
* **Tests :** JUnit 5  

## Fonctionnalités (MVP)

1. **Endpoint d'Ingestion :** API REST acceptant des payloads JSON complexes.
2. **Moteur de Transformation :** Validation stricte des types et calcul d'agrégats (moyennes, sommes).
3. **Persistance Automatisée :** Nettoyage et injection sécurisée en base de données.
4. **Reporting technique :** Journalisation des erreurs et des lignes corrompues pour audit.

## Installation

### Prérequis

* JDK 21
* Maven 3.9+
* PostgreSQL

### Clonage et Lancement

```bash
# Cloner le dépôt
git clone https://github.com/steevnPrm/extracteur_transformateur_PORTFOLIO

# Accéder au dossier
cd extractor

# Compiler et lancer les tests
mvn clean install

# Démarrer l'application
mvn spring-boot:run
````

L'application sera disponible sur `http://localhost:8080`.

## Configuration

Exemple de configuration PostgreSQL (`application.yml`) :

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

## Utilisation de l'API

### Soumission de données

**Endpoint :** `POST /api/submission`

**Payload :**

```json
[
  { "id": "A1", "vente": 150.50, "date": "2026-04-01" },
  { "id": "A2", "vente": "erreur", "date": "2026-04-01" }
]
```

### Consultation des résultats

**Endpoint :** `GET /api/results`
Retourne les données nettoyées et persistées en base.

## Plan de Tests

Le projet suit une stratégie de test pour garantir la qualité des données :

* **Tests Unitaires :** Validation de la logique de transformation
* **Tests d'Intégration :** Validation de la persistance avec JPA
* **Capacité validée :** Traitement d’un volume minimal de **1000 lignes** sans erreur de typage

---

## Documentations de référence

* [https://docs.spring.io/spring-boot/index.html](https://docs.spring.io/spring-boot/index.html)
* [https://openjdk.org/projects/jdk/21/](https://openjdk.org/projects/jdk/21/)
* [https://hibernate.org/validator/](https://hibernate.org/validator/)
