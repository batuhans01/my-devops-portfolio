# Headless E-Commerce Infrastructure

This project demonstrates a production-ready deployment of a MedusaJS ecosystem using Docker and Nginx.

## Features
- **Containerization:** Decoupled Backend, Frontend, and Database services.
- **Security:** Admin panel access is restricted to VPN (Wireguard) IP ranges via Nginx.
- **Reverse Proxy:** Optimized Nginx configuration for SSL termination and API routing.
- **Scalability:** Ready for CI/CD integration with Infisical for secret management.

## Tech Stack
- **Engine:** MedusaJS
- **Database:** PostgreSQL 16
- **Cache:** Redis 7
- **Proxy:** Nginx
- **Infrastructure:** Docker & Docker Compose
