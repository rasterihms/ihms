package in.raster.ihms.authserver.ldap.repository;

import in.raster.ihms.authserver.ldap.domain.LdapRole;
import org.springframework.data.ldap.repository.LdapRepository;

/**
 * Ldap data repository to interact with ldap role.
 */
public interface LdapRoleRepository  extends LdapRepository<LdapRole> {

    /**
     * Find ldap role by name.
     *
     * @param roleName - role name
     * @return LdapRole object
     */
    LdapRole findByName(final String roleName);
}
