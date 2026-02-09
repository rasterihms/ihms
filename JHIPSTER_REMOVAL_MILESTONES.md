# JHipster Removal Milestones

This document outlines the comprehensive plan to remove JHipster framework dependencies from the IHMS (Integrated Hospital Management System) project and migrate to a standard Spring Boot microservices architecture.

## Project Overview

**Current State:**
- JHipster Version: 4.14.3
- Microservices: authserver (port 8090), abdm (port 8088)
- Spring Boot: 1.5.x (legacy version)
- Service Discovery: JHipster Registry (Eureka-based)
- Authentication: JWT-based
- Database: PostgreSQL with Liquibase migrations
- Build Tool: Gradle

**Target State:**
- Standard Spring Boot 2.x/3.x microservices
- Native Spring Cloud components
- Maintained JWT authentication
- PostgreSQL with Liquibase (retained)
- Gradle build system (modernized)

---

## Milestone 1: Preparation & Assessment (Week 1-2)

### Objectives
- Complete inventory of JHipster dependencies
- Establish baseline testing
- Create rollback strategy

### Tasks

#### 1.1 Documentation & Inventory
- [x] Document all JHipster components and dependencies
- [ ] Create dependency mapping (JHipster → Spring Boot equivalents)
- [ ] Document current microservices architecture
- [ ] Identify custom code vs. generated code
- [ ] Map configuration files and their dependencies

#### 1.2 Testing Infrastructure
- [ ] Set up comprehensive test suite
- [ ] Document existing test coverage
- [ ] Create integration test suite for critical paths:
  - Authentication flow (JWT)
  - User management
  - Role-based access control
  - LDAP integration
  - Service discovery and inter-service communication
- [ ] Establish performance benchmarks

#### 1.3 Environment Setup
- [ ] Create development branch: `remove-jhipster`
- [ ] Set up local testing environment
- [ ] Document deployment procedures
- [ ] Create rollback plan

---

## Milestone 2: Dependency Migration (Week 3-5)

### Objectives
- Replace JHipster dependencies with standard Spring Boot equivalents
- Maintain functional parity

### Tasks

#### 2.1 Core Framework Migration
- [ ] Upgrade Spring Boot version:
  - From: 1.5.12.RELEASE (authserver) / 1.5.9.RELEASE (abdm)
  - To: 2.7.x or 3.x (assess compatibility)
- [ ] Remove `io.github.jhipster:jhipster-dependencies` BOM
- [ ] Remove `io.github.jhipster:jhipster` core library
- [ ] Update Spring Cloud version (Dalston.SR1 → 2021.x or 2022.x)

**Affected Files:**
- `authserver/build.gradle`
- `abdm/build.gradle`
- `build.gradle` (root)
- `gradle.properties`

#### 2.2 Service Discovery Migration
- [ ] Migrate from JHipster Registry to standard Eureka Server
- [ ] Update Eureka client configuration
- [ ] Remove JHipster Registry Docker configuration
- [ ] Update service URLs and discovery settings
- [ ] Test service registration and discovery

**Affected Files:**
- `authserver/src/main/docker/jhipster-registry.yml` → Remove
- `authserver/src/main/resources/config/bootstrap.yml` → Update
- `abdm/src/main/resources/config/bootstrap.yml` → Update
- `docker-compose.yml` → Update

#### 2.3 Configuration Management
- [ ] Migrate Spring Cloud Config integration:
  - Remove JHipster Registry config server dependency
  - Implement standard Spring Cloud Config Server or alternative
- [ ] Update configuration properties structure
- [ ] Remove JHipster-specific properties:
  - `jhipster.async`
  - `jhipster.cors`
  - `jhipster.mail`
  - `jhipster.swagger`
  - `jhipster.ribbon`
- [ ] Migrate to standard Spring Boot properties

**Affected Files:**
- All `application*.yml` files (50+ tenant configurations)
- `bootstrap*.yml` files

#### 2.4 Database & Liquibase
- [ ] Review Liquibase changelogs (retain as-is or migrate)
- [ ] Verify JHipster audit table dependencies
- [ ] Update Liquibase configuration to remove JHipster conventions
- [ ] Test database migrations

**Affected Files:**
- `authserver/src/main/resources/config/liquibase/**`
- `liquibase.gradle`

---

## Milestone 3: Code Refactoring (Week 6-9)

### Objectives
- Remove JHipster-generated code patterns
- Replace with standard Spring Boot implementations

### Tasks

#### 3.1 Domain Model Updates
- [ ] Review and update entity classes:
  - Remove JHipster-specific annotations
  - Update `AbstractAuditingEntity` (if needed)
  - Remove JHipster audit dependencies
- [ ] Update caching strategy (Hazelcast → Spring Cache or Redis):
  - `@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)` → `@Cacheable`
- [ ] Verify serialization requirements

**Affected Entities:**
- Users, Role, Menu, Resource, Application
- UsersRole, RoleMenu, RoleResource, MenuResource
- PersistentAuditEvent (consider removal or migration)

**Files:**
- `authserver/src/main/java/in/raster/ihms/authserver/domain/**`

#### 3.2 Repository Layer
- [ ] Verify Spring Data JPA repositories are standard
- [ ] Review custom query methods
- [ ] Update LDAP repository implementations (if JHipster-specific)

**Files:**
- `authserver/src/main/java/in/raster/ihms/authserver/repository/**`

#### 3.3 Service Layer
- [ ] Update service implementations
- [ ] Remove JHipster utility dependencies
- [ ] Verify LDAP integration (LdapUserService, etc.)
- [ ] Update DTOs and MapStruct mappings

**Files:**
- `authserver/src/main/java/in/raster/ihms/authserver/service/**`

#### 3.4 REST Controllers
- [ ] Update REST resource classes
- [ ] Remove JHipster response util dependencies
- [ ] Update error handling (ErrorConstants)
- [ ] Verify Swagger/OpenAPI configuration

**Files:**
- `authserver/src/main/java/in/raster/ihms/authserver/web/rest/**`

#### 3.5 Security & Authentication
- [ ] Update JWT TokenProvider:
  - Remove JHipster security dependencies
  - Implement standard Spring Security JWT
- [ ] Update security configuration
- [ ] Verify LDAP authentication integration
- [ ] Test authentication flows

**Files:**
- `authserver/src/main/java/in/raster/ihms/authserver/security/**`
- `authserver/src/main/java/in/raster/ihms/authserver/config/SecurityConfiguration.java`

#### 3.6 Configuration Classes
- [ ] Update `AsyncConfiguration` (remove JHipster async properties)
- [ ] Update `CacheConfiguration` (Hazelcast → Spring Cache)
- [ ] Update `DatabaseConfiguration` (remove JHipster DB config)
- [ ] Update `MetricsConfiguration` (Dropwizard → Micrometer)
- [ ] Update `WebConfigurer` (CORS, compression)
- [ ] Review and update `LoggingAspectConfiguration`
- [ ] Update `LocaleConfiguration` if needed
- [ ] Remove `CloudDatabaseConfiguration` if JHipster-specific

**Files:**
- `authserver/src/main/java/in/raster/ihms/authserver/config/**`

#### 3.7 AOP & Logging
- [ ] Update `LoggingAspect` (remove JHipster dependencies)
- [ ] Update `LoggingConfiguration`

**Files:**
- `authserver/src/main/java/in/raster/ihms/authserver/aop/**`

---

## Milestone 4: Build & Packaging Updates (Week 10)

### Objectives
- Modernize build configuration
- Remove JHipster Gradle plugins and tasks

### Tasks

#### 4.1 Gradle Build Files
- [ ] Remove JHipster Gradle buildscript dependencies
- [ ] Remove JHipster needle comments (e.g., `//jhipster-needle-*`)
- [ ] Update Spring Boot Gradle plugin version
- [ ] Remove obsolete plugins (propdeps, swagger-codegen)
- [ ] Update dependency management
- [ ] Clean up root `build.gradle`

**Files:**
- `build.gradle` (root)
- `authserver/build.gradle`
- `abdm/build.gradle`
- `settings.gradle`

#### 4.2 Node/NPM Dependencies
- [ ] Remove `generator-jhipster` from `package.json`
- [ ] Clean up `node_modules` and lockfiles
- [ ] Update or remove frontend build tasks

**Files:**
- `authserver/package.json`
- `authserver/yarn.lock`
- `package-lock.json` (root)

#### 4.3 Docker Configuration
- [ ] Update Dockerfile (remove JHipster references)
- [ ] Update `docker-compose.yml` (remove JHipster Registry)
- [ ] Update application Docker configs
- [ ] Test containerized deployments

**Files:**
- `authserver/src/main/docker/**`
- `docker-compose.yml`

---

## Milestone 5: Documentation & Metadata Cleanup (Week 11)

### Objectives
- Remove JHipster references from documentation
- Update development guides

### Tasks

#### 5.1 Documentation Updates
- [ ] Update `README.md` (root)
- [ ] Update `authserver/README.md`:
  - Remove JHipster documentation links
  - Update development instructions
  - Update production deployment guide
  - Remove JHipster-specific commands
- [ ] Update ABDM module documentation
- [ ] Create migration guide for developers

**Files:**
- `README.md`
- `ReadMe.md`
- `authserver/README.md`
- `authserver/src/main/docker/central-server-config/README.md`

#### 5.2 Configuration File Cleanup
- [ ] Remove `.yo-rc.json` (JHipster generator config)
- [ ] Review and clean `gradle.properties`
- [ ] Update `.gitignore` if needed
- [ ] Remove or update `ihms.iml` (IntelliJ)

**Files:**
- `authserver/.yo-rc.json` → Remove
- `gradle.properties`

#### 5.3 Static Resources
- [ ] Update or remove `authserver/src/main/resources/static/index.html`
- [ ] Review error pages and templates
- [ ] Update Swagger UI resources

---

## Milestone 6: Testing & Validation (Week 12-13)

### Objectives
- Comprehensive testing of migrated system
- Performance validation
- Security audit

### Tasks

#### 6.1 Unit Testing
- [ ] Update test configurations (remove JHipster test dependencies)
- [ ] Run all unit tests and fix failures
- [ ] Update test resources configuration

**Files:**
- `authserver/src/test/**`
- `authserver/src/test/resources/config/application.yml`
- `authserver/src/test/resources/logback.xml`

#### 6.2 Integration Testing
- [ ] Test authentication flows (JWT, LDAP)
- [ ] Test authorization (roles, resources, menus)
- [ ] Test service discovery and inter-service communication
- [ ] Test database migrations
- [ ] Test multi-tenant configurations
- [ ] Test ABDM integration

#### 6.3 Performance Testing
- [ ] Compare performance metrics with baseline
- [ ] Test caching implementation
- [ ] Load testing
- [ ] Memory usage analysis

#### 6.4 Security Testing
- [ ] Security vulnerability scan
- [ ] JWT token validation testing
- [ ] LDAP authentication testing
- [ ] CORS configuration validation
- [ ] Dependency security audit

---

## Milestone 7: CI/CD Pipeline Updates (Week 14)

### Objectives
- Update build and deployment pipelines
- Remove JHipster-specific CI tasks

### Tasks

#### 7.1 Jenkins Pipeline Updates
- [ ] Update `Jenkinsfile` (main)
- [ ] Update `Jenkinsfile-dev`
- [ ] Update `Jenkinsfile-dev-build`
- [ ] Update `Jenkinsfile-his`
- [ ] Update `Jenkinsfile-onebook`
- [ ] Update `Jenkinsfile-pharmacy`
- [ ] Update `Jenkinsfile-qa`
- [ ] Remove JHipster-specific build steps
- [ ] Update deployment scripts

**Files:**
- All `Jenkinsfile*` files

#### 7.2 Deployment Scripts
- [ ] Update `start.sh`
- [ ] Update `stop.sh`
- [ ] Update `buildwithtestcase`
- [ ] Test deployment procedures

---

## Milestone 8: Production Migration (Week 15-16)

### Objectives
- Deploy to staging environment
- Validate production readiness
- Plan production cutover

### Tasks

#### 8.1 Staging Deployment
- [ ] Deploy to staging environment
- [ ] Run smoke tests
- [ ] Performance validation
- [ ] Security validation
- [ ] User acceptance testing (UAT)

#### 8.2 Production Planning
- [ ] Create detailed cutover plan
- [ ] Document rollback procedures
- [ ] Schedule maintenance window
- [ ] Prepare communication plan
- [ ] Train operations team

#### 8.3 Production Deployment
- [ ] Execute cutover plan
- [ ] Monitor system health
- [ ] Validate all services
- [ ] Performance monitoring
- [ ] Issue tracking and resolution

#### 8.4 Post-Deployment
- [ ] Monitor for 48-72 hours
- [ ] Document lessons learned
- [ ] Update runbooks
- [ ] Close migration tickets

---

## Milestone 9: Optimization & Cleanup (Week 17+)

### Objectives
- Optimize the new implementation
- Clean up deprecated code
- Knowledge transfer

### Tasks

#### 9.1 Code Optimization
- [ ] Code review and cleanup
- [ ] Performance tuning
- [ ] Dependency cleanup
- [ ] Dead code removal

#### 9.2 Documentation Finalization
- [ ] Complete API documentation
- [ ] Update architecture diagrams
- [ ] Create developer onboarding guide
- [ ] Document new workflows

#### 9.3 Knowledge Transfer
- [ ] Conduct training sessions
- [ ] Create video tutorials
- [ ] Update wiki/knowledge base
- [ ] Q&A sessions with team

---

## Risk Assessment & Mitigation

### High-Risk Areas

1. **Authentication System**
   - **Risk:** JWT token generation/validation changes could break authentication
   - **Mitigation:** Extensive testing, staged rollout, maintain backward compatibility

2. **Service Discovery**
   - **Risk:** Services may fail to discover each other after migration
   - **Mitigation:** Test in isolated environment, implement health checks, gradual migration

3. **Database Migrations**
   - **Risk:** Liquibase changelogs may have JHipster dependencies
   - **Mitigation:** Backup databases, test migrations thoroughly, keep Liquibase if possible

4. **Multi-Tenant Configuration**
   - **Risk:** 50+ tenant configurations need updates
   - **Mitigation:** Script configuration updates, validate each tenant, phased rollout

5. **LDAP Integration**
   - **Risk:** Custom LDAP implementation may have hidden JHipster dependencies
   - **Mitigation:** Thorough integration testing, maintain test LDAP server

### Medium-Risk Areas

1. **Caching Strategy** (Hazelcast migration)
2. **Metrics & Monitoring** (Dropwizard → Micrometer)
3. **ABDM Integration** (external dependency)
4. **Build System** (Gradle updates)

### Low-Risk Areas

1. **Documentation updates**
2. **Static resources**
3. **Configuration file cleanup**

---

## Success Criteria

### Technical Criteria
- [ ] Zero JHipster dependencies in build files
- [ ] All tests passing (unit, integration, e2e)
- [ ] No JHipster framework code references
- [ ] Performance metrics match or exceed baseline
- [ ] Security audit passed
- [ ] All microservices operational

### Business Criteria
- [ ] Zero downtime during cutover
- [ ] All features functional
- [ ] No regression issues
- [ ] Positive user feedback
- [ ] Team comfortable with new stack

### Quality Criteria
- [ ] Code coverage maintained or improved
- [ ] Documentation complete and accurate
- [ ] Build time improved or maintained
- [ ] Technical debt reduced

---

## Timeline Summary

| Milestone | Duration | Dependencies |
|-----------|----------|--------------|
| M1: Preparation & Assessment | 2 weeks | None |
| M2: Dependency Migration | 3 weeks | M1 |
| M3: Code Refactoring | 4 weeks | M2 |
| M4: Build & Packaging Updates | 1 week | M3 |
| M5: Documentation & Cleanup | 1 week | M4 |
| M6: Testing & Validation | 2 weeks | M5 |
| M7: CI/CD Pipeline Updates | 1 week | M6 |
| M8: Production Migration | 2 weeks | M7 |
| M9: Optimization & Cleanup | Ongoing | M8 |

**Total Estimated Duration:** 16 weeks (4 months) + ongoing optimization

---

## Team & Resources

### Required Team Members
- **Lead Developer:** Overall migration coordination
- **Backend Developers (2-3):** Code refactoring and testing
- **DevOps Engineer:** CI/CD and infrastructure updates
- **QA Engineer:** Testing and validation
- **Security Specialist:** Security audit and validation
- **Technical Writer:** Documentation updates

### External Resources
- Spring Boot/Cloud documentation
- Migration guides and best practices
- Community support forums
- Third-party libraries documentation

---

## Communication Plan

### Stakeholders
- Development team
- QA team
- Operations team
- Product management
- End users (for UAT)

### Communication Schedule
- **Weekly:** Team sync meetings
- **Bi-weekly:** Stakeholder updates
- **Monthly:** Executive summary
- **Ad-hoc:** Critical issues and blockers

### Status Reporting
- Progress tracking via project management tool
- Risk register maintained and updated
- Blockers escalated immediately
- Success metrics dashboarded

---

## Appendix

### A. Dependency Mapping

| JHipster Component | Spring Boot Equivalent |
|-------------------|------------------------|
| JHipster Registry | Standard Eureka Server |
| JHipster Config Server | Spring Cloud Config Server |
| JHipster Async Properties | Spring Task Execution |
| JHipster Metrics | Spring Boot Actuator + Micrometer |
| JHipster Security | Spring Security + JWT |
| JHipster Cache | Spring Cache Abstraction |
| JHipster Swagger | Springdoc OpenAPI |

### B. Key Files Requiring Changes

**High Priority:**
- All `build.gradle` files
- `bootstrap.yml` files
- `application.yml` files
- Security configuration classes
- Docker compose files

**Medium Priority:**
- Domain entities
- Service implementations
- REST controllers
- Configuration classes

**Low Priority:**
- Documentation files
- Static resources
- Test configurations

### C. Rollback Plan

1. **Pre-Migration:** Tag current stable version
2. **During Migration:** Feature flags for new implementations
3. **Post-Deployment:** Database backups, config backups
4. **Rollback Triggers:** >5% error rate, critical features broken, >50% performance degradation
5. **Rollback Steps:** Restore previous Docker images, revert configuration, restore databases if needed

---

## Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-02-09 | GitHub Copilot | Initial milestone document created |

---

## Next Steps

1. Review and approve this milestone document
2. Assign team members to milestones
3. Set up project tracking (JIRA, GitHub Projects, etc.)
4. Begin Milestone 1: Preparation & Assessment
5. Schedule kickoff meeting with all stakeholders

---

**Document Status:** Draft  
**Last Updated:** 2026-02-09  
**Owner:** Development Team  
**Approvers:** TBD
