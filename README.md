# Data Morph : Extracteur & Transformateur de Flux

## Description

**Data Morph** est un service backend développé avec **Spring Boot** et **Java 21**. Il permet l'ingestion d'éléments, le filtrage selon des critères techniques minimaux et la persistance en base de données de manière automatisée. Les données non conformes sont traitées et isolées à des fins d'audit via une base d'erreurs dédiée.

## Stack Technique

* **Runtime :** Java 21 (LTS)
* **Framework :** Spring Boot  
* **Web :** Spring Web (REST API)  
* **Validation :** Logique de validation métier interne
* **Persistance :** Spring Data JPA / Hibernate  
* **Base de données :** PostgreSQL  
* **Driver DB :** PostgreSQL JDBC Driver  
* **Tests :** JUnit 5 & Mockito

## Architecture des processus (Workflow métier)

1. **Ingestion :** Génération ou réception d'entités contenant une `value` (Integer) et une `description` (String).
2. **Filtrage et Transformation :** 
   - L'item est **valide** si `value` > 0 et si sa `description` n'est pas nulle (ni si l'objet lui-même est nul).
   - Validation technique : Un item passe ce contrôle de conformité sans lever d'exception de type système.
3. **Persistance Sécurisée :** 
   - Les objets valides sont enregistrés classiquement en table principale.
   - Les objets invalides sont journalisés avec un type d'erreur prédéfini (`"Log mal formatée"`) et insérés dans une table spécifique (historisation des erreurs) pour traitement ultérieur.

## Installation

### Prérequis

* JDK 21
* Maven 3.9+
* PostgreSQL local ou Docker

### Clonage et Lancement

```bash
# Cloner le dépôt
git clone https://github.com/steevnPrm/extracteur_transformateur_PORTFOLIO

# Accéder au dossier backend
cd extracteur_transformateur_PORTFOLIO/fluxExtractor

# Compiler et lancer les tests
mvn clean install

# Démarrer l'application
mvn spring-boot:run
```

L'application est disponible par défaut sur `http://localhost:8080`.

## Configuration

Exemple de configuration PostgreSQL (`application.yml`) attendue :

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

### 1. Lancer un test de flux important
Méthode pour générer et valider massivement et simultanément 1000 items :
**Endpoint :** `POST /api/items/batch-test`
**Description :** Teste le filtre d'intégration. Génère 1000 items, dont certains contiennent des exceptions forcées (`value = -100`) afin d'alimenter la base des rapports d'erreurs en parallèle que la base opérationnelle régulière.

### 2. Récupérer l'intégralité des éléments validés
**Endpoint :** `GET /api/items`
**Description :** Retourne en JSON toutes les données fiabilisées, nettoyées et conservées au sein de la base de données principale.

## Plan de Tests

L'application est testée via différentes strates :
* **Tests Unitaires isolés :** Vérifications granulaires avec JUnit 5 et Mockito sur les services fondamentaux (création d'éléments, règles de filtrage stricts, mécanismes de gestion d'erreur).
* **Robustesse d'ingestion :** Scalabilité éprouvée au travers du test d'intégration qui ingère rapidement sans interruption les anomalies sur des flux de données consécutifs (batchs natifs simulés).

---

## Documentations de référence

* [Spring Boot](https://docs.spring.io/spring-boot/index.html)
* [Java JDK 21](https://openjdk.org/projects/jdk/21/)
