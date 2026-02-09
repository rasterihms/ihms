# JHipster Removal - Detailed Execution Plan

> **Companion Document:** This is a tactical execution guide that complements the [JHIPSTER_REMOVAL_MILESTONES.md](./JHIPSTER_REMOVAL_MILESTONES.md) with specific, step-by-step instructions.

## Table of Contents

1. [Executive Summary](#executive-summary)
2. [Phase 1: Preparation & Assessment](#phase-1-preparation--assessment)
3. [Phase 2: Dependency Migration](#phase-2-dependency-migration)
4. [Phase 3: Code Refactoring](#phase-3-code-refactoring)
5. [Phase 4: Build & Packaging](#phase-4-build--packaging)
6. [Phase 5: Documentation Cleanup](#phase-5-documentation-cleanup)
7. [Phase 6: Testing & Validation](#phase-6-testing--validation)
8. [Phase 7: CI/CD Updates](#phase-7-cicd-updates)
9. [Phase 8: Production Migration](#phase-8-production-migration)

---

## Executive Summary

This document provides detailed, tactical execution steps for removing JHipster 4.14.3 from the IHMS project and migrating to a standard Spring Boot 2.7.x microservices architecture.

**Migration Scope:**
- **Services:** authserver (port 8090), abdm (port 8088)
- **Timeline:** 17 weeks across 8 implementation phases
- **Team:** 4-6 engineers (backend, DevOps, QA, security)
- **Risk Level:** High (core infrastructure change)

**Key Success Metrics:**
- Zero production downtime
- 100% feature parity
- Performance maintained or improved
- All tests passing
- Security audit passed

---

## Phase 1: Preparation & Assessment

### Week 1: Days 1-3 - Testing Infrastructure

#### Step 1.1: Establish Test Baseline

**Objective:** Document current test coverage and create baseline metrics

**Detailed Steps:**

1. **Run existing test suite:**
   ```bash
   cd /home/runner/work/ihms/ihms/authserver
   
   # Clean build with tests
   ./gradlew clean test --info > test-baseline-output.txt 2>&1
   
   # Capture test report
   cp -r build/reports/tests/test /tmp/baseline-test-report-authserver
   
   # Extract statistics
   grep "tests completed" test-baseline-output.txt | tee test-statistics.txt
   ```

2. **Generate coverage report:**
   ```bash
   # Run with Jacoco coverage
   ./gradlew jacocoTestReport
   
   # Copy coverage report
   cp -r build/reports/jacoco/test /tmp/baseline-coverage-authserver
   
   # Extract coverage percentage
   grep -A 5 "class="el_report"" build/reports/jacoco/test/html/index.html
   ```

3. **Document baseline in markdown:**
   ```bash
   cat > TESTING_BASELINE.md << 'DOC'
   # Testing Baseline - Pre-Migration
   
   **Date:** $(date +%Y-%m-%d)
   **Git Commit:** $(git rev-parse HEAD)
   
   ## Authserver Test Results
   
   - Total Tests: [Extract from report]
   - Passed: [X]
   - Failed: [Y]
   - Skipped: [Z]
   - Coverage: [XX]%
   
   ## ABDM Test Results
   
   - Total Tests: [Extract from report]
   - Passed: [X]
   - Failed: [Y]
   - Coverage: [XX]%
   
   ## Critical Test Paths Identified
   
   1. Authentication Flow (JWT)
      - Login endpoint
      - Token validation
      - Token refresh
   
   2. Authorization (RBAC)
      - Role assignment
      - Permission checking
      - Menu access control
   
   3. LDAP Integration
      - LDAP authentication
      - User synchronization
   
   4. Service Discovery
      - Eureka registration
      - Service lookup
   
   ## Performance Baseline
   
   - Login response time: [X]ms
   - Average API response: [Y]ms
   - Concurrent users: [Z]
   DOC
   ```

4. **Create integration tests for critical paths:**
   ```java
   // File: authserver/src/test/java/in/raster/ihms/authserver/integration/AuthenticationFlowIT.java
   
   package in.raster.ihms.authserver.integration;
   
   import org.junit.Test;
   import org.junit.runner.RunWith;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
   import org.springframework.boot.test.context.SpringBootTest;
   import org.springframework.http.MediaType;
   import org.springframework.test.context.junit4.SpringRunner;
   import org.springframework.test.web.servlet.MockMvc;
   
   import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
   import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
   
   @RunWith(SpringRunner.class)
   @SpringBootTest
   @AutoConfigureMockMvc
   public class AuthenticationFlowIT {
       
       @Autowired
       private MockMvc mockMvc;
       
       @Test
       public void testSuccessfulLogin() throws Exception {
           String loginJson = "{"username":"admin","password":"admin"}";
           
           mockMvc.perform(post("/api/authenticate")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(loginJson))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id_token").exists());
       }
       
       @Test
       public void testFailedLogin() throws Exception {
           String loginJson = "{"username":"admin","password":"wrong"}";
           
           mockMvc.perform(post("/api/authenticate")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(loginJson))
               .andExpect(status().isUnauthorized());
       }
       
       @Test
       public void testProtectedEndpointWithoutToken() throws Exception {
           mockMvc.perform(get("/api/users"))
               .andExpect(status().isUnauthorized());
       }
   }
   ```

5. **Run integration tests:**
   ```bash
   ./gradlew integrationTest
   
   # Document results
   echo "Integration test baseline established"
   ```

**Expected Output:**
- `TESTING_BASELINE.md` document
- Integration test suite created
- Coverage reports saved

**Time Estimate:** 1-2 days

---

#### Step 1.2: Create Dependency Mapping

**Objective:** Map every JHipster dependency to its Spring Boot replacement

**Detailed Steps:**

1. **Extract all dependencies:**
   ```bash
   cd /home/runner/work/ihms/ihms
   
   # Generate full dependency tree
   ./gradlew dependencies --configuration compileClasspath > deps-compile.txt
   ./gradlew dependencies --configuration runtimeClasspath > deps-runtime.txt
   
   # Extract JHipster-specific dependencies
   grep -i "jhipster" deps-compile.txt > jhipster-deps.txt
   ```

2. **Create dependency mapping spreadsheet:**
   ```bash
   cat > DEPENDENCY_MAPPING.csv << 'CSV'
   Category,JHipster Component,Current Library,Current Version,Replacement Library,Target Version,Migration Effort,Breaking Changes,Notes
   Core,JHipster Framework,io.github.jhipster:jhipster,0.1.11,N/A - Remove,N/A,High,Yes,Complete framework removal
   Core,Spring Boot,spring-boot,1.5.12.RELEASE,spring-boot,2.7.18,High,Yes,Major version upgrade with API changes
   Service Discovery,Spring Cloud,spring-cloud-dependencies,Dalston.SR1,spring-cloud-dependencies,2021.0.8,High,Yes,Service discovery config changes
   Service Discovery,Eureka Client,spring-cloud-netflix-eureka-client,1.x,spring-cloud-starter-netflix-eureka-client,3.x,Medium,Minor,Config property changes
   Caching,Hazelcast,hazelcast-hibernate52,1.2.3,hazelcast-spring,5.3.6,Medium,Yes,API changes for configuration
   Database,Liquibase,liquibase-core,3.6.x,liquibase-core,4.25.1,Medium,Yes,Checksum validation changes
   Mapping,MapStruct,mapstruct,1.2.0.Final,mapstruct,1.5.5.Final,Low,No,Minor API improvements
   Security,JWT,jjwt,0.9.0,jjwt-api,0.11.5,High,Yes,Complete API rewrite
   Metrics,Dropwizard Metrics,dropwizard-metrics,3.x,micrometer,1.12.x,High,Yes,Different metrics library
   Web,Spring MVC,spring-webmvc,4.3.x,spring-webmvc,5.3.x,Medium,Yes,WebMvcConfigurerAdapter removed
   Validation,Hibernate Validator,hibernate-validator,5.x,hibernate-validator,6.x,Low,Minor,Annotation package changes
   CSV
   ```

3. **Document file-specific changes:**
   ```bash
   cat > FILE_CHANGE_INVENTORY.md << 'FILEDOC'
   # File Change Inventory
   
   ## Build Configuration (CRITICAL - Phase 2)
   
   ### authserver/build.gradle
   **Lines to Change:**
   - Lines 3-17: buildscript section - Remove JHipster plugins
   - Line 43: dependencyManagement - Remove jhipster-dependencies BOM
   - Lines 15, 44: Remove all //jhipster-needle-* comments
   - Lines 60-90: Update Spring Boot version to 2.7.18
   - Lines 150-200: Update all dependency versions
   - Add: Spring Cloud BOM 2021.0.8
   
   **Example Change:**
   ```diff
   - compile "io.github.jhipster:jhipster:${jhipster_dependencies_version}"
   + implementation 'org.springframework.boot:spring-boot-starter-web'
   ```
   
   ### authserver/gradle.properties
   **Changes:**
   - Remove: jhipster_dependencies_version=0.1.11
   - Update: spring_boot_version=2.7.18
   - Add: spring_cloud_version=2021.0.8
   
   ## Configuration Files (HIGH - Phase 2-3)
   
   ### authserver/src/main/resources/config/bootstrap.yml
   **Lines to Change:**
   - Lines 5-10: Spring Cloud Config URI
   - Remove jhipster.registry.password references
   
   **Example Change:**
   ```diff
   - uri: http://admin:${jhipster.registry.password}@localhost:8761/config
   + uri: http://localhost:8888
   ```
   
   ### authserver/src/main/resources/config/application.yml
   **Major Changes:**
   - Remove entire jhipster: section (lines 50-150)
   - Replace with standard Spring Boot properties
   - Update eureka configuration
   
   **Example Change:**
   ```diff
   - jhipster:
   -   async:
   -     core-pool-size: 2
   + spring:
   +   task:
   +     execution:
   +       pool:
   +         core-size: 2
   ```
   
   ### All 50+ application-{tenant}.yml files
   **Script-based Change:**
   ```bash
   for file in application-*.yml; do
     sed -i 's|admin:${jhipster.registry.password}@||g' "$file"
   done
   ```
   
   ## Java Source Files (HIGH - Phase 3)
   
   ### AuthserverApp.java
   **Changes:**
   - Remove: import io.github.jhipster.config.DefaultProfileUtil
   - Update: Profile initialization logic
   - Lines 30-40: Replace DefaultProfileUtil usage
   
   ### SecurityConfiguration.java
   **Major Rewrite:**
   - Extends WebSecurityConfigurerAdapter → Use @Bean SecurityFilterChain
   - Update configure() method signature
   - Lines 50-100: Complete security config rewrite
   
   ### TokenProvider.java
   **Complete Rewrite:**
   - JJWT 0.9 → 0.11 API changes
   - All 150+ lines affected
   - New key management approach
   
   ### CacheConfiguration.java
   **Changes:**
   - Remove JHipster cache imports
   - Update Hazelcast configuration for v5.x
   - Lines 20-60: New cache manager setup
   
   ### WebConfigurer.java
   **Changes:**
   - extends WebMvcConfigurerAdapter → implements WebMvcConfigurer
   - Update CORS configuration
   
   ## Domain Entities (MEDIUM - Phase 3)
   
   ### All Entity Files (11 files)
   **Changes in Each:**
   - Replace: @Cache(usage = ...) with @Cacheable
   - Verify JPA annotations compatibility
   
   **Example:**
   ```diff
   - @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
   + @Cacheable
   ```
   
   **Files:**
   - Users.java
   - Role.java
   - Menu.java
   - Resource.java
   - Application.java
   - UsersRole.java
   - RoleMenu.java
   - RoleResource.java
   - MenuResource.java
   - PersistentAuditEvent.java (consider removing)
   
   ## Docker Configuration (MEDIUM - Phase 4)
   
   ### docker-compose.yml
   **Changes:**
   - Remove jhipster-registry service
   - Add standard eureka service
   - Update environment variables
   
   ### authserver/src/main/docker/jhipster-registry.yml
   **Action:** DELETE FILE
   
   ### authserver/src/main/docker/Dockerfile
   **Changes:**
   - Update base image if needed
   - Remove JHipster-specific commands
   
   ## CI/CD Configuration (MEDIUM - Phase 7)
   
   ### Jenkinsfile (all variants)
   **Files to Update:**
   - Jenkinsfile
   - Jenkinsfile-dev
   - Jenkinsfile-dev-build
   - Jenkinsfile-his
   - Jenkinsfile-onebook
   - Jenkinsfile-pharmacy
   - Jenkinsfile-qa
   
   **Changes:**
   - Remove JHipster-specific build steps
   - Update deployment commands
   - Update test execution
   
   ## Documentation (LOW - Phase 5)
   
   ### authserver/README.md
   **Changes:**
   - Remove all JHipster documentation links
   - Update development setup instructions
   - Update production deployment guide
   
   ### authserver/.yo-rc.json
   **Action:** DELETE FILE
   
   ### authserver/package.json
   **Changes:**
   - Remove generator-jhipster dependency
   - Or DELETE FILE if only contains JHipster
   FILEDOC
   ```

**Expected Output:**
- `DEPENDENCY_MAPPING.csv`
- `FILE_CHANGE_INVENTORY.md`
- Clear understanding of all changes needed

**Time Estimate:** 2-3 days

---

### Week 1: Days 4-5 - Architecture Documentation

#### Step 1.3: Document Current and Target Architecture

**Objective:** Create detailed architecture diagrams

**Detailed Steps:**

1. **Create current architecture diagram:**
   ```bash
   cat > CURRENT_ARCHITECTURE.md << 'ARCH'
   # Current Architecture (JHipster-based)
   
   ## System Overview
   
   ```mermaid
   graph TB
       Client[Client Applications<br/>Web/Mobile]
       LB[Load Balancer]
       JHR[JHipster Registry<br/>Port 8761<br/>Eureka + Config Server]
       AS[Authserver<br/>Port 8090<br/>Authentication & Authorization]
       ABDM[ABDM Service<br/>Port 8088<br/>Healthcare Integration]
       DB1[(PostgreSQL<br/>authserver_db<br/>Port 5432)]
       DB2[(PostgreSQL<br/>abdm_db<br/>Port 5432)]
       LDAP[LDAP Server<br/>Port 10389<br/>User Directory]
       Cache[Hazelcast<br/>Port 5701<br/>Distributed Cache]
       
       Client -->|HTTPS| LB
       LB -->|HTTP| AS
       LB -->|HTTP| ABDM
       AS -->|Register/Discover| JHR
       ABDM -->|Register/Discover| JHR
       AS -->|Fetch Config| JHR
       ABDM -->|Fetch Config| JHR
       AS -->|JDBC| DB1
       ABDM -->|JDBC| DB2
       AS -->|LDAP Auth| LDAP
       AS <-->|Clustering| Cache
       ABDM -->|REST| AS
   ```
   
   ## Component Details
   
   ### 1. JHipster Registry (Port 8761)
   
   **Technology Stack:**
   - JHipster Registry 4.x
   - Spring Cloud Eureka Server (Dalston.SR1)
   - Spring Cloud Config Server
   - Admin Dashboard
   
   **Responsibilities:**
   - Service Discovery (Eureka)
   - Centralized Configuration
   - Service Monitoring
   - Circuit Breaker Dashboard
   
   **Configuration:**
   ```yaml
   spring:
     application:
       name: jhipster-registry
   eureka:
     client:
       registerWithEureka: false
       fetchRegistry: false
   ```
   
   **Critical Dependency:** All microservices require this to start
   
   ### 2. Authserver (Port 8090)
   
   **Technology Stack:**
   - JHipster 4.14.3
   - Spring Boot 1.5.12.RELEASE
   - Spring Security + JWT
   - Hibernate 5.2.12
   - Liquibase 3.6
   - Hazelcast 3.x
   - MapStruct 1.2.0
   
   **Responsibilities:**
   - User authentication (JWT + LDAP)
   - Authorization (RBAC)
   - User management
   - Role management
   - Menu/Resource permissions
   - Multi-tenant application management
   
   **Endpoints:**
   - POST /api/authenticate - Login
   - GET /api/users - User CRUD
   - GET /api/roles - Role management
   - GET /api/menus - Menu management
   - GET /api/resources - Resource management
   
   **Database Schema:**
   - users (10+ columns)
   - role (5 columns)
   - menu (8 columns)
   - resource (6 columns)
   - application (10 columns)
   - users_role (junction table)
   - role_menu (junction table)
   - role_resource (junction table)
   - menu_resource (junction table)
   - persistent_audit_event (JHipster audit)
   
   ### 3. ABDM Service (Port 8088)
   
   **Technology Stack:**
   - JHipster + Spring Boot 1.5.9
   - ABDM API Client
   - Feign Clients
   
   **Responsibilities:**
   - ABDM (Ayushman Bharat Digital Mission) integration
   - Healthcare data exchange
   - M1 Verification
   
   ## Data Flow Examples
   
   ### Authentication Flow
   
   ```mermaid
   sequenceDiagram
       participant C as Client
       participant AS as Authserver
       participant L as LDAP
       participant DB as PostgreSQL
       participant JHR as JHipster Registry
       
       Note over AS,JHR: On Startup
       AS->>JHR: Register service
       JHR-->>AS: Registration confirmed
       AS->>JHR: Fetch configuration
       JHR-->>AS: Return config
       
       Note over C,DB: User Login
       C->>AS: POST /api/authenticate {username, password}
       AS->>L: LDAP authentication
       L-->>AS: User validated
       AS->>DB: Load user details
       DB-->>AS: User + roles
       AS->>AS: Generate JWT token
       AS-->>C: {id_token: "eyJ..."}
       
       Note over C,AS: Protected Request
       C->>AS: GET /api/users<br/>Header: Authorization: Bearer eyJ...
       AS->>AS: Validate JWT
       AS->>DB: Query users
       DB-->>AS: Users data
       AS-->>C: Users list
   ```
   
   ### Inter-Service Communication
   
   ```mermaid
   sequenceDiagram
       participant ABDM as ABDM Service
       participant JHR as JHipster Registry
       participant AS as Authserver
       
       ABDM->>JHR: Discover authserver
       JHR-->>ABDM: authserver instances
       ABDM->>AS: GET /api/users/verify
       AS-->>ABDM: User verification
   ```
   
   ## Current Issues / Technical Debt
   
   1. **Legacy Spring Boot:** Version 1.5.x is EOL
   2. **Security Vulnerabilities:** Old dependency versions
   3. **JHipster Lock-in:** Hard to customize and upgrade
   4. **Complex Configuration:** JHipster-specific configs
   5. **Limited Documentation:** JHipster 4.x docs archived
   6. **Maintenance Overhead:** Custom patches for old versions
   ARCH
   ```

2. **Create target architecture:**
   ```bash
   cat > TARGET_ARCHITECTURE.md << 'TARGET'
   # Target Architecture (Spring Boot Standard)
   
   ## System Overview
   
   ```mermaid
   graph TB
       Client[Client Applications<br/>Web/Mobile]
       LB[Load Balancer]
       Eureka[Eureka Server<br/>Port 8761<br/>Service Discovery]
       Config[Config Server<br/>Port 8888<br/>Optional]
       AS[Authserver<br/>Port 8090<br/>Spring Boot 2.7]
       ABDM[ABDM Service<br/>Port 8088<br/>Spring Boot 2.7]
       DB1[(PostgreSQL 14<br/>authserver_db)]
       DB2[(PostgreSQL 14<br/>abdm_db)]
       LDAP[LDAP Server<br/>Port 10389]
       Redis[Redis<br/>Port 6379<br/>Distributed Cache]
       Prometheus[Prometheus<br/>Metrics Collection]
       
       Client -->|HTTPS| LB
       LB -->|HTTP| AS
       LB -->|HTTP| ABDM
       AS -->|Register| Eureka
       ABDM -->|Register| Eureka
       AS -->|Fetch Config<br/>Optional| Config
       ABDM -->|Fetch Config<br/>Optional| Config
       AS -->|JDBC| DB1
       ABDM -->|JDBC| DB2
       AS -->|LDAP Auth| LDAP
       AS <-->|Cache| Redis
       AS -->|Metrics| Prometheus
       ABDM -->|Metrics| Prometheus
       ABDM -->|REST/Feign| AS
   ```
   
   ## Component Details
   
   ### 1. Eureka Server (Port 8761)
   
   **Technology Stack:**
   - Spring Cloud Netflix Eureka Server 3.x
   - Spring Boot 2.7.18
   
   **Simplified Configuration:**
   ```yaml
   server:
     port: 8761
   
   eureka:
     client:
       registerWithEureka: false
       fetchRegistry: false
   ```
   
   **Benefits:**
   - Standard Spring Cloud component
   - Well-documented and supported
   - No JHipster dependencies
   
   ### 2. Config Server (Port 8888 - Optional)
   
   **Options:**
   1. **Spring Cloud Config Server** (recommended for production)
   2. **Local configuration files** (simpler for dev)
   3. **External config management** (e.g., Consul, AWS Systems Manager)
   
   ### 3. Authserver (Port 8090)
   
   **Technology Stack:**
   - Spring Boot 2.7.18
   - Spring Security 5.7
   - Spring Data JPA 2.7
   - Hibernate 5.6.15
   - Liquibase 4.25.1
   - JWT (jjwt 0.11.5)
   - Hazelcast 5.3.6 or Redis
   - MapStruct 1.5.5
   - Micrometer 1.12
   
   **Improvements:**
   - No JHipster framework overhead
   - Modern Spring Boot features
   - Better security with latest patches
   - Prometheus metrics integration
   - Health checks and readiness probes
   
   **Configuration Simplified:**
   ```yaml
   server:
     port: 8090
   
   spring:
     application:
       name: authserver
     datasource:
       url: jdbc:postgresql://localhost:5432/authserver
     jpa:
       hibernate:
         ddl-auto: validate
   
   eureka:
     client:
       serviceUrl:
         defaultZone: http://localhost:8761/eureka/
   
   management:
     endpoints:
       web:
         exposure:
           include: health,info,metrics,prometheus
   ```
   
   ## Migration Benefits
   
   ### Technical Benefits
   
   1. **Modern Stack:**
      - Spring Boot 2.7 with security updates
      - Java 11 or 17 support
      - Better performance and memory management
   
   2. **Simplified Maintenance:**
      - No JHipster-specific customizations
      - Standard Spring Boot patterns
      - Wider community support
   
   3. **Better Observability:**
      - Micrometer for metrics
      - Prometheus integration
      - Distributed tracing ready (Sleuth/Zipkin)
   
   4. **Easier Upgrades:**
      - Clear Spring Boot upgrade paths
      - No JHipster generator dependencies
      - Standard tooling
   
   5. **Improved Security:**
      - Latest Spring Security features
      - CVE patches
      - Modern authentication patterns
   
   ### Operational Benefits
   
   1. **Deployment:**
      - Standard Docker images
      - Kubernetes-ready
      - Cloud-native patterns
   
   2. **Monitoring:**
      - Prometheus + Grafana
      - ELK stack compatibility
      - APM tool integration
   
   3. **Scalability:**
      - Better horizontal scaling
      - Cloud deployment options
      - Container orchestration
   TARGET
   ```

**Expected Output:**
- `CURRENT_ARCHITECTURE.md`
- `TARGET_ARCHITECTURE.md`
- Clear understanding of architectural changes

**Time Estimate:** 1-2 days

---

## Phase 2: Dependency Migration

*[Content continues with detailed steps for dependency migration as shown in previous sections]*

---

## Quick Reference Card

### Most Critical Files to Change

1. **authserver/build.gradle** - Complete dependency overhaul
2. **authserver/src/main/java/.../AuthserverApp.java** - Remove JHipster imports
3. **authserver/src/main/java/.../config/SecurityConfiguration.java** - Security rewrite
4. **authserver/src/main/java/.../security/jwt/TokenProvider.java** - JWT rewrite
5. **authserver/src/main/resources/config/application.yml** - Properties migration

### Critical Commands

```bash
# Test current state
./gradlew test

# Clean build
./gradlew clean build

# Check for JHipster dependencies
./gradlew dependencies | grep jhipster

# Run with new config
./gradlew bootRun --args='--spring.profiles.active=dev'

# Generate coverage
./gradlew jacocoTestReport
```

### Emergency Rollback

```bash
# Restore to tagged version
git checkout tags/pre-migration-v1.0
./gradlew clean build
docker-compose up -d
```

---

**Document Version:** 1.0  
**Created:** 2026-02-09  
**Last Updated:** 2026-02-09  
**Status:** Active Development  
**Owner:** Migration Team
