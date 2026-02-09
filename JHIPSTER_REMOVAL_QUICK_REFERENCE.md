# JHipster Removal - Quick Reference Guide

This is a quick reference companion to the comprehensive [JHIPSTER_REMOVAL_MILESTONES.md](./JHIPSTER_REMOVAL_MILESTONES.md) document.

## Quick Stats

- **JHipster Version:** 4.14.3 (legacy)
- **Microservices:** 2 (authserver, abdm)
- **Spring Boot Version:** 1.5.x → 2.7.x/3.x
- **Estimated Timeline:** 16 weeks
- **Total Configuration Files:** 50+ tenant configs
- **Key Dependencies to Replace:** ~10 major components

## Critical Components to Replace

### 1. Service Discovery
```yaml
# FROM (JHipster Registry)
jhipster.registry.password: admin
config server: http://admin:${jhipster.registry.password}@localhost:8761/config

# TO (Standard Eureka)
eureka.client.serviceUrl.defaultZone: http://localhost:8761/eureka/
spring.cloud.config.uri: http://localhost:8888
```

### 2. Core Dependencies
```gradle
// FROM
compile "io.github.jhipster:jhipster:${jhipster_dependencies_version}"

// TO
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
```

### 3. Configuration Properties
```yaml
# REMOVE
jhipster:
  async: {...}
  cors: {...}
  mail: {...}
  swagger: {...}

# REPLACE WITH
spring:
  task:
    execution: {...}
  web:
    cors: {...}
  mail: {...}
springdoc:
  api-docs: {...}
```

## File Checklist by Priority

### 🔴 Critical (Must Change First)
- [ ] `authserver/build.gradle`
- [ ] `abdm/build.gradle`
- [ ] `build.gradle` (root)
- [ ] `authserver/.yo-rc.json` (DELETE)
- [ ] `authserver/src/main/resources/config/bootstrap.yml`
- [ ] `authserver/src/main/docker/jhipster-registry.yml` (REPLACE)

### 🟡 High Priority
- [ ] All `application*.yml` files (50+ files)
- [ ] Security configuration classes
- [ ] JWT TokenProvider
- [ ] Configuration classes in `authserver/src/main/java/.../config/`
- [ ] Docker compose files

### 🟢 Medium Priority
- [ ] Domain entities (11 entities)
- [ ] Service layer implementations
- [ ] REST controllers (10+ resources)
- [ ] Liquibase changelogs (review only)

### 🔵 Low Priority
- [ ] README files
- [ ] Test configurations
- [ ] Static resources
- [ ] Documentation

## Common Migration Patterns

### Pattern 1: Entity Caching
```java
// FROM (JHipster + Hazelcast)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class User implements Serializable { ... }

// TO (Spring Cache)
@Cacheable("users")
@Entity
public class User implements Serializable { ... }
```

### Pattern 2: Audit Entities
```java
// FROM (JHipster)
public abstract class AbstractAuditingEntity implements Serializable {
    // JHipster-specific audit fields
}

// TO (Spring Data)
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity {
    @CreatedBy
    private String createdBy;
    
    @CreatedDate
    private Instant createdDate;
    
    @LastModifiedBy
    private String lastModifiedBy;
    
    @LastModifiedDate
    private Instant lastModifiedDate;
}
```

### Pattern 3: Async Configuration
```java
// FROM (JHipster)
@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
    private final JHipsterProperties jHipsterProperties;
    // Uses jhipster.async properties
}

// TO (Spring Boot)
@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }
}
```

## Testing Strategy

### Pre-Migration Tests
```bash
# Run existing tests
./gradlew clean test

# Run integration tests
./gradlew integrationTest

# Check test coverage
./gradlew jacocoTestReport
```

### Post-Migration Validation
```bash
# Verify build
./gradlew clean build

# Run all tests
./gradlew test integrationTest

# Start services locally
./gradlew bootRun

# Check service registration
curl http://localhost:8761/eureka/apps

# Test authentication
curl -X POST http://localhost:8090/api/authenticate \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin"}'
```

## Configuration File Locations

### Bootstrap Configuration
```
authserver/src/main/resources/config/bootstrap.yml
authserver/src/main/resources/config/bootstrap-prod.yml
abdm/src/main/resources/config/bootstrap.yml
```

### Application Configuration
```
authserver/src/main/resources/config/application.yml
authserver/src/main/resources/config/application-dev.yml
authserver/src/main/resources/config/application-prod.yml
authserver/src/main/resources/config/application-{tenant}.yml (50+ files)
```

### Docker Configuration
```
authserver/src/main/docker/jhipster-registry.yml (REMOVE)
authserver/src/main/docker/app.yml
authserver/src/main/docker/postgresql.yml
docker-compose.yml
```

## Key Entities to Review

| Entity | Purpose | JHipster Features Used |
|--------|---------|----------------------|
| Users | User management | LDAP integration, JWT auth |
| Role | RBAC | Caching |
| Menu | UI menus | Caching, hierarchical |
| Resource | Permissions | RBAC |
| Application | Multi-tenant | Tenant isolation |
| PersistentAuditEvent | Audit trail | JHipster audit (consider removing) |

## Dependency Version Targets

| Component | Current | Target |
|-----------|---------|--------|
| Spring Boot | 1.5.12 | 2.7.18 or 3.2.x |
| Spring Cloud | Dalston.SR1 | 2021.0.x or 2022.0.x |
| Hibernate | 5.2.12.Final | 5.6.x or 6.x |
| PostgreSQL Driver | 42.x | 42.7.x |
| Liquibase | 3.6.x | 4.25.x |
| MapStruct | 1.2.0.Final | 1.5.x |

## Environment-Specific Notes

### Development
- Eureka on localhost:8761
- PostgreSQL on localhost:5432
- Config server on localhost:8888

### Production
- External Eureka cluster
- External PostgreSQL
- External config server
- Load balancer configuration

## Common Issues & Solutions

### Issue 1: Service Not Registering with Eureka
**Solution:** Check `eureka.client.serviceUrl.defaultZone` and ensure Eureka is running

### Issue 2: Configuration Not Loading
**Solution:** Verify `spring.application.name` matches config file naming

### Issue 3: JWT Token Not Valid
**Solution:** Ensure JWT secret key is consistent across services

### Issue 4: Cache Not Working
**Solution:** Enable Spring Cache with `@EnableCaching` and configure cache manager

### Issue 5: LDAP Authentication Fails
**Solution:** Check LDAP URL, credentials, and DN patterns

## Useful Commands

### Gradle
```bash
# Clean build
./gradlew clean build

# Run without tests
./gradlew build -x test

# Run specific service
./gradlew :authserver:bootRun

# Check dependencies
./gradlew dependencies
```

### Docker
```bash
# Build image
./gradlew bootRepackage -Pprod buildDocker

# Run with compose
docker-compose up -d

# View logs
docker-compose logs -f authserver

# Stop services
docker-compose down
```

### Git
```bash
# Create feature branch
git checkout -b feature/remove-jhipster-component-x

# View changes
git diff --stat

# Commit with meaningful message
git commit -m "refactor: replace JHipster X with Spring Boot Y"
```

## Rollback Procedure

If issues occur during migration:

1. **Stop deployment:**
   ```bash
   docker-compose down
   ```

2. **Checkout previous version:**
   ```bash
   git checkout tags/pre-migration-stable
   ```

3. **Restore configuration:**
   ```bash
   cp backup/application.yml src/main/resources/config/
   ```

4. **Restart services:**
   ```bash
   docker-compose up -d
   ```

5. **Verify health:**
   ```bash
   curl http://localhost:8090/management/health
   ```

## Resources

### Documentation
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Spring Security JWT](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)
- [Eureka](https://cloud.spring.io/spring-cloud-netflix/reference/html/)

### Migration Guides
- [JHipster to Spring Boot Migration](https://www.jhipster.tech/migrating/)
- [Spring Boot 1.5 to 2.x](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide)
- [Spring Boot 2.x to 3.x](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide)

### Tools
- [Spring Initializr](https://start.spring.io/) - Generate new Spring Boot projects
- [OpenRewrite](https://docs.openrewrite.org/) - Automated code refactoring
- [Spring Boot Migrator](https://github.com/spring-projects-experimental/spring-boot-migrator)

## Contact & Support

- **Migration Lead:** TBD
- **Technical Questions:** Development team channel
- **Blocker Escalation:** Team lead
- **Documentation:** Update this guide as you learn

---

**Last Updated:** 2026-02-09  
**Status:** Active Migration  
**Current Phase:** Milestone 1 - Preparation & Assessment
