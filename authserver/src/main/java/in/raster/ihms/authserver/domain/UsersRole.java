package in.raster.ihms.authserver.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import in.raster.ihms.authserver.domain.enumeration.LinkTo;

/**
 * A UsersRole.
 */
@Entity
@Table(name = "users_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsersRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ldap_user_id")
    private String ldapUserId;

    @Column(name = "ldap_role_id")
    private String ldapRoleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "link_to")
    private LinkTo linkTo;

    @Column(name = "link_to_id")
    private Long linkToId;

    @Column(name = "effective_from")
    private Instant effectiveFrom;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_modified")
    private Instant lastModified;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Role role;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLdapUserId() {
        return ldapUserId;
    }

    public UsersRole ldapUserId(String ldapUserId) {
        this.ldapUserId = ldapUserId;
        return this;
    }

    public void setLdapUserId(String ldapUserId) {
        this.ldapUserId = ldapUserId;
    }

    public String getLdapRoleId() {
        return ldapRoleId;
    }

    public UsersRole ldapRoleId(String ldapRoleId) {
        this.ldapRoleId = ldapRoleId;
        return this;
    }

    public void setLdapRoleId(String ldapRoleId) {
        this.ldapRoleId = ldapRoleId;
    }

    public LinkTo getLinkTo() {
        return linkTo;
    }

    public UsersRole linkTo(LinkTo linkTo) {
        this.linkTo = linkTo;
        return this;
    }

    public void setLinkTo(LinkTo linkTo) {
        this.linkTo = linkTo;
    }

    public Long getLinkToId() {
        return linkToId;
    }

    public UsersRole linkToId(Long linkToId) {
        this.linkToId = linkToId;
        return this;
    }

    public void setLinkToId(Long linkToId) {
        this.linkToId = linkToId;
    }

    public Instant getEffectiveFrom() {
        return effectiveFrom;
    }

    public UsersRole effectiveFrom(Instant effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
        return this;
    }

    public void setEffectiveFrom(Instant effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Boolean isActive() {
        return active;
    }

    public UsersRole active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public UsersRole createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public UsersRole createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public UsersRole lastModified(Instant lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public UsersRole modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Users getUsers() {
        return users;
    }

    public UsersRole users(Users users) {
        this.users = users;
        return this;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Role getRole() {
        return role;
    }

    public UsersRole role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsersRole usersRole = (UsersRole) o;
        if (usersRole.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usersRole.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsersRole{" +
            "id=" + getId() +
            ", ldapUserId='" + getLdapUserId() + "'" +
            ", ldapRoleId='" + getLdapRoleId() + "'" +
            ", linkTo='" + getLinkTo() + "'" +
            ", linkToId=" + getLinkToId() +
            ", effectiveFrom='" + getEffectiveFrom() + "'" +
            ", active='" + isActive() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", lastModified='" + getLastModified() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            "}";
    }
}
