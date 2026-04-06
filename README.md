# Data Morph : Extracteur & Transformateur de Flux

## Description
**Data Morph** est un service backend développé avec **Spring Boot** et **Java 21**. Il permet l'ingestion de données brutes au format JSON, leur nettoyage (validation de types, mise en conformité) et leur persistance automatisée dans une base de données relationnelle.

## Stack Technique
* **Runtime :** Java 21 (LTS)
* **Framework :** Spring Boot **
* **Persistance :** Spring Data JPA / Hibernate
* **Base de données :** H2 (In-memory pour le dev) / MySQL (Cible démonstration)
* **Tests :** JUnit 5, AssertJ

## Fonctionnalités (MVP)
1.  **Endpoint d'Ingestion :** API REST acceptant des payloads JSON complexes.
2.  **Moteur de Transformation :** Service de validation stricte des types et calcul d'agrégats (moyennes, sommes).
3.  **Persistance Automatisée :** Nettoyage et injection sécurisée en base de données.
4.  **Reporting technique :** Journalisation des erreurs et lignes corrompues pour audit.

## Installation

### Prérequis
* JDK 21
* Maven 3.9+

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
```
L'application sera disponible sur `http://localhost:8080`.

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
Le projet suit une stratégie de test rigoureuse pour garantir la qualité des données :
* **Tests Unitaires :** Validation de la logique de transformation des agrégats.
* **Tests d'Intégration :** Utilisation de `@DataJpaTest` pour valider les schémas de base de données.
* **Rapport de succès :** Le système est validé pour un traitement minimal de **1000 lignes** sans échec de typage.

---

### Documentations de référence
* [Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)
* [Java 21 Features](https://openjdk.org/projects/jdk/21/)
* [Hibernate Validator (Bean Validation)](https://hibernate.org/validator/)