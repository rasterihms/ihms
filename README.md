# IHMS - Integrated Hospital Management System

## Project Status: JHipster Removal in Progress

This repository is currently undergoing a migration to remove JHipster framework dependencies and modernize to a standard Spring Boot microservices architecture.

### 📋 Migration Documentation

For detailed information about the JHipster removal project, please refer to:

1. **[JHIPSTER_REMOVAL_MILESTONES.md](./JHIPSTER_REMOVAL_MILESTONES.md)** - Comprehensive 9-phase migration plan
   - Detailed milestones from preparation to production deployment
   - 17-week timeline with tasks and acceptance criteria
   - Risk assessment and mitigation strategies
   - Success criteria and rollback procedures

2. **[JHIPSTER_REMOVAL_QUICK_REFERENCE.md](./JHIPSTER_REMOVAL_QUICK_REFERENCE.md)** - Developer quick reference
   - Quick stats and critical components to replace
   - Common migration patterns and examples
   - File checklists by priority
   - Testing strategies and useful commands

3. **[JHIPSTER_REMOVAL_GITHUB_MILESTONES.md](./JHIPSTER_REMOVAL_GITHUB_MILESTONES.md)** - GitHub project tracking
   - 24 issues across 9 milestones
   - Issue templates for GitHub Projects
   - Labels and priority assignments
   - Progress tracking guidelines

### 🎯 Current Phase

**Milestone 1: Preparation & Assessment** (Week 1-2)
- ✅ Complete JHipster component inventory
- ⏳ Establish testing baseline
- ⏳ Create development environment and rollback plan

### 🏗️ Architecture

**Current State:**
- JHipster 4.14.3 framework
- Spring Boot 1.5.x
- Microservices: authserver (8090), abdm (8088)
- JHipster Registry for service discovery

**Target State:**
- Standard Spring Boot 2.7.x/3.x
- Native Spring Cloud components
- Standard Eureka service discovery
- Maintained JWT authentication and PostgreSQL

### 📚 Additional Resources

- [Authserver Documentation](./authserver/README.md)
- [Docker Deployment Guide](./docker-compose.yml)

---

For development setup and other information, see the migration documentation above.
