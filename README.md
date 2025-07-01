# BookLab
The project is a web application for managing a library, which allows users to rent books, as well as add, delete, and edit books depending on the type and privileges of the logged-in user. The backend is implemented using Java Spring, the frontend is built with React, and PostgreSQL is used as the database.


## DevOps Overview
My project includes the following DevOps components for full CI/CD and deployment automation:

### Docker
* Dockerfile for frontend 

* Dockerfile for backend 

* docker-compose.yml for local orchestration of: Frontend, Backend and PostgreSQL database

### CI: Continuous Integration
GitHub Actions workflow for:

* Building Docker images for frontend and backend

* Pushing images to DockerHub

### CD: Continuous Deployment
ArgoCD application manifest to enable GitOps-based deployment to Kubernetes

### Kubernetes Manifests
* Deployments for: Frontend & Backend

* Service objects to expose backend and frontend internally

* Ingress configuration (using Ingress Controller) to route external traffic to services

* StatefulSet for PostgreSQL database with persistent volume

* ConfigMaps for application/environment configuration

* Secrets for sensitive environment variables

* Namespace configuration to isolate application resources in a dedicated K8s namespace
