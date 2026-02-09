# JHipster Removal - GitHub Milestones

This file defines GitHub milestones and issues for tracking the JHipster removal project.

## How to Use This File

If using GitHub Issues/Projects:
1. Create milestones in GitHub for each phase
2. Create issues based on the tasks below
3. Link issues to appropriate milestones
4. Track progress using GitHub Projects board

---

## Milestone 1: Preparation & Assessment
**Due Date:** Week 2  
**Description:** Complete inventory, establish testing baseline, and create rollback strategy

### Issues for Milestone 1

#### Issue #1: Complete JHipster Component Inventory
**Labels:** `documentation`, `milestone-1`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Document all JHipster components and dependencies in the codebase.

**Tasks:**
- [x] Document all JHipster components and dependencies (completed via exploration)
- [ ] Create dependency mapping (JHipster → Spring Boot equivalents)
- [ ] Document current microservices architecture diagram
- [ ] Identify custom code vs. generated code
- [ ] Map configuration files and their dependencies

**Acceptance Criteria:**
- Comprehensive documentation created
- All dependencies mapped to Spring Boot equivalents
- Architecture diagram updated

---

#### Issue #2: Establish Testing Baseline
**Labels:** `testing`, `milestone-1`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Set up comprehensive test suite and document current test coverage.

**Tasks:**
- [ ] Run existing test suite and document results
- [ ] Document existing test coverage percentage
- [ ] Create integration test suite for:
  - [ ] Authentication flow (JWT)
  - [ ] User management operations
  - [ ] Role-based access control
  - [ ] LDAP integration
  - [ ] Service discovery communication
- [ ] Establish performance benchmarks
- [ ] Document baseline metrics

**Acceptance Criteria:**
- All tests run successfully
- Test coverage documented (>70% target)
- Integration tests created for critical paths
- Performance baseline established

---

#### Issue #3: Create Development Environment & Rollback Plan
**Labels:** `infrastructure`, `milestone-1`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Set up development environment and create comprehensive rollback plan.

**Tasks:**
- [x] Create development branch: `copilot/remove-jhipster-integration`
- [ ] Set up local testing environment
- [ ] Document deployment procedures
- [ ] Create rollback plan with triggers
- [ ] Tag current stable version for rollback
- [ ] Set up feature flags for gradual migration

**Acceptance Criteria:**
- Development environment fully functional
- Rollback plan documented and tested
- Stable version tagged

---

## Milestone 2: Dependency Migration
**Due Date:** Week 5  
**Description:** Replace JHipster dependencies with standard Spring Boot equivalents

### Issues for Milestone 2

#### Issue #4: Upgrade Spring Boot Framework
**Labels:** `dependencies`, `milestone-2`, `breaking-change`  
**Priority:** Critical  
**Assignee:** TBD

**Description:**
Upgrade Spring Boot from 1.5.x to 2.7.x (or 3.x after compatibility assessment).

**Tasks:**
- [ ] Assess compatibility with Spring Boot 3.x vs 2.7.x
- [ ] Update `build.gradle` in authserver module
- [ ] Update `build.gradle` in abdm module
- [ ] Update root `build.gradle`
- [ ] Remove `io.github.jhipster:jhipster-dependencies` BOM
- [ ] Remove `io.github.jhipster:jhipster` core library
- [ ] Update Spring Cloud version (Dalston.SR1 → 2021.x or 2022.x)
- [ ] Update `gradle.properties`
- [ ] Fix compilation errors
- [ ] Run tests and fix failures

**Acceptance Criteria:**
- Project builds successfully with new Spring Boot version
- All existing tests pass
- No JHipster dependencies in build files

---

#### Issue #5: Migrate Service Discovery to Standard Eureka
**Labels:** `service-discovery`, `milestone-2`, `infrastructure`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Replace JHipster Registry with standard Eureka Server for service discovery.

**Tasks:**
- [ ] Set up standalone Eureka Server
- [ ] Update Eureka client configuration in authserver
- [ ] Update Eureka client configuration in abdm
- [ ] Remove JHipster Registry Docker configuration
- [ ] Update `bootstrap.yml` files
- [ ] Update service URLs and discovery settings
- [ ] Remove `jhipster-registry.yml`
- [ ] Update `docker-compose.yml`
- [ ] Test service registration
- [ ] Test service discovery between microservices

**Acceptance Criteria:**
- Both microservices register with Eureka successfully
- Services can discover each other
- No JHipster Registry dependencies remain
- Docker compose works with standard Eureka

---

#### Issue #6: Migrate Configuration Management
**Labels:** `configuration`, `milestone-2`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Migrate Spring Cloud Config integration from JHipster Registry to standard implementation.

**Tasks:**
- [ ] Set up Spring Cloud Config Server (or choose alternative)
- [ ] Update configuration properties structure
- [ ] Remove JHipster-specific properties:
  - [ ] `jhipster.async` → `spring.task.execution`
  - [ ] `jhipster.cors` → `spring.web.cors`
  - [ ] `jhipster.mail` → `spring.mail`
  - [ ] `jhipster.swagger` → `springdoc`
  - [ ] `jhipster.ribbon` (remove if not needed)
- [ ] Update all 50+ tenant configuration files
- [ ] Update `bootstrap.yml` and `bootstrap-prod.yml`
- [ ] Test configuration loading from config server

**Acceptance Criteria:**
- Configuration server operational
- All services load configuration successfully
- No JHipster properties remain
- All tenant configurations migrated

---

#### Issue #7: Review and Validate Database Migrations
**Labels:** `database`, `milestone-2`  
**Priority:** Medium  
**Assignee:** TBD

**Description:**
Review Liquibase changelogs and ensure they work without JHipster dependencies.

**Tasks:**
- [ ] Review all Liquibase changelogs for JHipster dependencies
- [ ] Test database migrations on clean database
- [ ] Verify JHipster audit table compatibility
- [ ] Update Liquibase configuration if needed
- [ ] Update `liquibase.gradle` (remove JHipster references)
- [ ] Document any manual migration steps needed

**Acceptance Criteria:**
- All Liquibase changelogs execute successfully
- No JHipster-specific Liquibase dependencies
- Database schema matches expected state

---

## Milestone 3: Code Refactoring
**Due Date:** Week 9  
**Description:** Remove JHipster-generated code patterns and replace with standard Spring Boot implementations

### Issues for Milestone 3

#### Issue #8: Refactor Domain Models
**Labels:** `refactoring`, `milestone-3`, `domain`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Update entity classes to remove JHipster-specific patterns.

**Tasks:**
- [ ] Review `AbstractAuditingEntity` and update if needed
- [ ] Update caching strategy (Hazelcast → Spring Cache):
  - [ ] Configure Spring Cache abstraction
  - [ ] Replace `@Cache` annotations with `@Cacheable`
  - [ ] Test caching behavior
- [ ] Update entity classes (11 entities):
  - [ ] Users
  - [ ] Role
  - [ ] Menu
  - [ ] Resource
  - [ ] Application
  - [ ] UsersRole
  - [ ] RoleMenu
  - [ ] RoleResource
  - [ ] MenuResource
  - [ ] PersistentAuditEvent (consider removal)
- [ ] Verify serialization requirements

**Acceptance Criteria:**
- All entities compile without JHipster dependencies
- Caching works with Spring Cache
- Tests pass for all entity operations

---

#### Issue #9: Update Security and JWT Implementation
**Labels:** `security`, `milestone-3`, `critical`  
**Priority:** Critical  
**Assignee:** TBD

**Description:**
Refactor JWT TokenProvider and security configuration to use standard Spring Security.

**Tasks:**
- [ ] Update `TokenProvider` class:
  - [ ] Remove JHipster security dependencies
  - [ ] Implement using standard Spring Security JWT
  - [ ] Test token generation
  - [ ] Test token validation
- [ ] Update `SecurityConfiguration`
- [ ] Verify LDAP authentication integration
- [ ] Test authentication flows:
  - [ ] JWT authentication
  - [ ] LDAP authentication
  - [ ] Token refresh
  - [ ] Logout
- [ ] Update security tests

**Acceptance Criteria:**
- JWT authentication works correctly
- LDAP authentication works correctly
- All security tests pass
- No JHipster security dependencies

---

#### Issue #10: Refactor Configuration Classes
**Labels:** `refactoring`, `milestone-3`, `configuration`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Update all configuration classes to remove JHipster dependencies.

**Tasks:**
- [ ] `AsyncConfiguration` - Remove JHipster async properties
- [ ] `CacheConfiguration` - Migrate to Spring Cache
- [ ] `DatabaseConfiguration` - Remove JHipster DB config
- [ ] `MetricsConfiguration` - Migrate Dropwizard → Micrometer
- [ ] `WebConfigurer` - Update CORS and compression
- [ ] `LoggingAspectConfiguration` - Remove JHipster logging
- [ ] `LocaleConfiguration` - Review and update
- [ ] Remove `CloudDatabaseConfiguration` if JHipster-specific
- [ ] Test each configuration

**Acceptance Criteria:**
- All configurations work without JHipster
- Application starts successfully
- All features functional

---

#### Issue #11: Update REST Controllers and Services
**Labels:** `refactoring`, `milestone-3`, `api`  
**Priority:** Medium  
**Assignee:** TBD

**Description:**
Update REST resource classes and service implementations.

**Tasks:**
- [ ] Update REST controllers (10+ resources):
  - [ ] UsersResource
  - [ ] RoleResource
  - [ ] MenuResource
  - [ ] ResourceResource
  - [ ] ApplicationResource
  - [ ] Others...
- [ ] Remove JHipster response util dependencies
- [ ] Update error handling (`ErrorConstants`)
- [ ] Update Swagger/OpenAPI configuration
- [ ] Update service layer implementations
- [ ] Update DTOs and MapStruct mappings
- [ ] Test all API endpoints

**Acceptance Criteria:**
- All REST endpoints functional
- API documentation updated
- Error handling works correctly
- All API tests pass

---

## Milestone 4: Build & Packaging Updates
**Due Date:** Week 10  
**Description:** Modernize build configuration and remove JHipster build tools

### Issues for Milestone 4

#### Issue #12: Update Gradle Build Configuration
**Labels:** `build`, `milestone-4`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Clean up Gradle build files and remove JHipster-specific plugins.

**Tasks:**
- [ ] Remove JHipster Gradle buildscript dependencies
- [ ] Remove JHipster needle comments (`//jhipster-needle-*`)
- [ ] Update Spring Boot Gradle plugin version
- [ ] Remove obsolete plugins (propdeps, swagger-codegen)
- [ ] Update dependency management
- [ ] Clean up root `build.gradle`
- [ ] Update `settings.gradle`
- [ ] Test build process

**Acceptance Criteria:**
- Clean build succeeds
- No JHipster build dependencies
- Build time maintained or improved

---

#### Issue #13: Update Docker Configuration
**Labels:** `docker`, `milestone-4`, `infrastructure`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Update Docker configurations to remove JHipster references.

**Tasks:**
- [ ] Update `Dockerfile` (remove JHipster references)
- [ ] Update `docker-compose.yml` (remove JHipster Registry)
- [ ] Update application Docker configs
- [ ] Test Docker build
- [ ] Test containerized deployment
- [ ] Verify multi-service orchestration

**Acceptance Criteria:**
- Docker images build successfully
- Services run correctly in containers
- Service discovery works in Docker environment

---

#### Issue #14: Clean Up Node/NPM Dependencies
**Labels:** `frontend`, `milestone-4`  
**Priority:** Low  
**Assignee:** TBD

**Description:**
Remove JHipster from Node package dependencies.

**Tasks:**
- [ ] Remove `generator-jhipster` from `authserver/package.json`
- [ ] Clean up `node_modules`
- [ ] Clean up lockfiles
- [ ] Update or remove frontend build tasks
- [ ] Test any remaining Node scripts

**Acceptance Criteria:**
- No JHipster in package.json
- Frontend build works (if applicable)

---

## Milestone 5: Documentation & Metadata Cleanup
**Due Date:** Week 11  
**Description:** Remove JHipster references from documentation

### Issues for Milestone 5

#### Issue #15: Update Documentation Files
**Labels:** `documentation`, `milestone-5`  
**Priority:** Medium  
**Assignee:** TBD

**Description:**
Update all documentation to reflect Spring Boot stack.

**Tasks:**
- [ ] Update root `README.md`
- [ ] Update `authserver/README.md`:
  - [ ] Remove JHipster documentation links
  - [ ] Update development instructions
  - [ ] Update production deployment guide
  - [ ] Remove JHipster-specific commands
- [ ] Update ABDM module documentation
- [ ] Create migration guide for developers
- [ ] Update `ReadMe.md`
- [ ] Update Docker README files

**Acceptance Criteria:**
- All documentation accurate
- No JHipster references
- Developer onboarding guide complete

---

#### Issue #16: Clean Up Configuration Metadata
**Labels:** `cleanup`, `milestone-5`  
**Priority:** Low  
**Assignee:** TBD

**Description:**
Remove JHipster configuration metadata files.

**Tasks:**
- [ ] Remove `authserver/.yo-rc.json`
- [ ] Review and clean `gradle.properties`
- [ ] Update `.gitignore` if needed
- [ ] Remove or update `ihms.iml`
- [ ] Remove any JHipster temp files

**Acceptance Criteria:**
- No JHipster metadata files remain
- Clean repository state

---

## Milestone 6: Testing & Validation
**Due Date:** Week 13  
**Description:** Comprehensive testing and performance validation

### Issues for Milestone 6

#### Issue #17: Execute Comprehensive Test Suite
**Labels:** `testing`, `milestone-6`, `critical`  
**Priority:** Critical  
**Assignee:** TBD

**Description:**
Run all tests and validate migration success.

**Tasks:**
- [ ] Update test configurations
- [ ] Run all unit tests
- [ ] Run all integration tests
- [ ] Execute authentication flow tests
- [ ] Execute authorization tests (roles, resources, menus)
- [ ] Test service discovery
- [ ] Test database migrations
- [ ] Test all 50+ tenant configurations
- [ ] Test ABDM integration
- [ ] Fix any test failures

**Acceptance Criteria:**
- 100% of tests pass
- Test coverage maintained or improved
- All critical paths validated

---

#### Issue #18: Performance and Security Validation
**Labels:** `testing`, `milestone-6`, `performance`, `security`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Validate performance and security of migrated system.

**Tasks:**
- [ ] Performance testing:
  - [ ] Compare metrics with baseline
  - [ ] Test caching implementation
  - [ ] Load testing
  - [ ] Memory usage analysis
- [ ] Security testing:
  - [ ] Security vulnerability scan
  - [ ] JWT token validation testing
  - [ ] LDAP authentication testing
  - [ ] CORS configuration validation
  - [ ] Dependency security audit

**Acceptance Criteria:**
- Performance meets or exceeds baseline
- No security vulnerabilities introduced
- Security audit passed

---

## Milestone 7: CI/CD Pipeline Updates
**Due Date:** Week 14  
**Description:** Update build and deployment pipelines

### Issues for Milestone 7

#### Issue #19: Update Jenkins Pipelines
**Labels:** `ci-cd`, `milestone-7`, `infrastructure`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Update all Jenkins pipeline configurations.

**Tasks:**
- [ ] Update `Jenkinsfile` (main)
- [ ] Update `Jenkinsfile-dev`
- [ ] Update `Jenkinsfile-dev-build`
- [ ] Update `Jenkinsfile-his`
- [ ] Update `Jenkinsfile-onebook`
- [ ] Update `Jenkinsfile-pharmacy`
- [ ] Update `Jenkinsfile-qa`
- [ ] Remove JHipster-specific build steps
- [ ] Update deployment scripts
- [ ] Test each pipeline

**Acceptance Criteria:**
- All pipelines execute successfully
- No JHipster build steps remain
- Deployment process validated

---

#### Issue #20: Update Deployment Scripts
**Labels:** `deployment`, `milestone-7`  
**Priority:** Medium  
**Assignee:** TBD

**Description:**
Update deployment automation scripts.

**Tasks:**
- [ ] Update `start.sh`
- [ ] Update `stop.sh`
- [ ] Update `buildwithtestcase`
- [ ] Test deployment procedures
- [ ] Document any manual steps

**Acceptance Criteria:**
- Deployment scripts work correctly
- Deployment process documented

---

## Milestone 8: Production Migration
**Due Date:** Week 16  
**Description:** Deploy to staging and production environments

### Issues for Milestone 8

#### Issue #21: Staging Environment Deployment
**Labels:** `deployment`, `milestone-8`, `staging`  
**Priority:** Critical  
**Assignee:** TBD

**Description:**
Deploy migrated system to staging environment.

**Tasks:**
- [ ] Deploy to staging environment
- [ ] Run smoke tests
- [ ] Performance validation
- [ ] Security validation
- [ ] User acceptance testing (UAT)
- [ ] Document any issues found
- [ ] Fix critical issues

**Acceptance Criteria:**
- Successful deployment to staging
- All smoke tests pass
- UAT completed successfully

---

#### Issue #22: Production Deployment
**Labels:** `deployment`, `milestone-8`, `production`, `critical`  
**Priority:** Critical  
**Assignee:** TBD

**Description:**
Execute production cutover.

**Tasks:**
- [ ] Create detailed cutover plan
- [ ] Document rollback procedures
- [ ] Schedule maintenance window
- [ ] Prepare communication plan
- [ ] Train operations team
- [ ] Execute cutover
- [ ] Monitor system health (48-72 hours)
- [ ] Validate all services
- [ ] Performance monitoring
- [ ] Issue tracking and resolution

**Acceptance Criteria:**
- Successful production deployment
- Zero critical issues
- All services operational
- Stakeholders informed

---

## Milestone 9: Optimization & Cleanup
**Due Date:** Week 17+  
**Description:** Final optimization and knowledge transfer

### Issues for Milestone 9

#### Issue #23: Code Optimization and Cleanup
**Labels:** `optimization`, `milestone-9`  
**Priority:** Medium  
**Assignee:** TBD

**Description:**
Optimize implementation and remove dead code.

**Tasks:**
- [ ] Code review and cleanup
- [ ] Performance tuning based on monitoring
- [ ] Dependency cleanup
- [ ] Dead code removal
- [ ] Final documentation updates

**Acceptance Criteria:**
- Code optimized
- No dead code remains
- Documentation complete

---

#### Issue #24: Knowledge Transfer
**Labels:** `documentation`, `milestone-9`, `training`  
**Priority:** High  
**Assignee:** TBD

**Description:**
Conduct knowledge transfer to team.

**Tasks:**
- [ ] Conduct training sessions
- [ ] Create video tutorials
- [ ] Update wiki/knowledge base
- [ ] Q&A sessions with team
- [ ] Document lessons learned
- [ ] Close all migration tickets

**Acceptance Criteria:**
- Team trained on new stack
- All documentation complete
- Migration project officially closed

---

## Summary

**Total Milestones:** 9  
**Total Issues:** 24  
**Estimated Duration:** 17 weeks  

**Progress Tracking:**
- Use GitHub Projects board to track issue progress
- Update issue status regularly
- Link PRs to issues
- Close issues only when acceptance criteria met

**Labels Used:**
- `milestone-1` through `milestone-9` - Phase tracking
- `critical`, `high`, `medium`, `low` - Priority
- `documentation`, `testing`, `refactoring`, `infrastructure`, etc. - Type
- `breaking-change` - Requires careful review

---

**Last Updated:** 2026-02-09  
**Project Status:** In Progress  
**Current Milestone:** Milestone 1 - Preparation & Assessment
