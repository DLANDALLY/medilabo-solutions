# medilabo-solutions  
Solution microservices pour la gestion d’un écosystème médical (patients, dossiers, rapports)  

## Contexte & objectif  
Le projet medilabo-solutions propose une architecture en microservices pour un système de gestion et de reporting dans un contexte médical :  
- gestion des patients via le service *patient-service*  
- gestion des dossiers médicaux via le service *medical-record-service*  
- reporting et agrégation via *reporting-service*  
- découverte de services et résilience via *discovery-service*  
- passerelle API via *gateway-service*  
- configuration centralisée via *config-service* 
  
L’objectif : déployer une solution évolutive, modulaire et facilement maintenable pour un environnement multi-services
  
## Architecture  
- Chaque service est indépendant, déployable séparément
- Communication via HTTP/REST (ou éventuellement messaging) entre certains services 
- Utilisation de la découverte de services pour automatiser l’interconnexion  
- Passerelle (gateway) pour centraliser l’accès aux APIs et appliquer des règles transversales  
- Configuration externalisée  
  
## Technologies utilisées  
- Java 21 (Spring Boot 3.5.5)  
- Spring Cloud (Eureka/Discovery, Config, Gateway)  
- Spring Data JPA + Hibernate + H2 + MongoDB
- Spring Security
- Docker / Docker Compose  
- API REST avec JSON  
- Angular 20 / NGINX 


## Installation & démarrage  

### Prérequis  
- Java 21  
- Angular 20  
- Maven  
- Docker & Docker Compose  

### Étapes  
1. **Cloner le dépôt :**  
   ```bash
   git clone https://github.com/DLANDALLY/medilabo-solutions.git
   cd medilabo-solutions

2. **Construire les images Docker :**  
   ```bash
   docker-compose build
   
3. **Lancer les conteneurs :**  
   ```bash
   docker-compose up
   
1. **Installer les dépendances du front-end :**  
   ```bash
   cd frontend
   npm install
   ng serve

## Utilisation
Une fois les services démarrés, connectez-vous à l’application via :
http://localhost:4200

Identifiants par défaut :
- **Nom d'utilisateur :** `admin`
- **Mot de passe:** `admin123`

