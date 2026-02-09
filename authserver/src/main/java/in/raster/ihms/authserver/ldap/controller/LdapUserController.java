/*******************************************************************************
 *  CONFIDENTIAL AND PROPRIETARY
 *
 *  The source code and other information contained herein is the confidential and the exclusive property of
 *  Raster Images Pvt. Ltd. and is subject to the terms and conditions in your end user license agreement.
 *  This source code, and any other information contained herein, shall not be copied, reproduced, published,
 *  displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
 *  expressly permitted under such license agreement.
 *
 *  Copyright Raster Images Pvt. Ltd.
 *
 *  ALL RIGHTS RESERVED
 *******************************************************************************/
package in.raster.ihms.authserver.ldap.controller;


import in.raster.ihms.authserver.ldap.domain.LdapGroup;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.service.impl.LdapClientService;
import in.raster.ihms.authserver.ldap.service.impl.LdapUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.CommunicationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo - unused file kept only for reference and will be removed in future.
 */
@RestController
@RequestMapping("/api")
public class LdapUserController {
    @Autowired
    LdapUserService ldapUserService;
    @Autowired
    LdapClientService userClientService;

    @PostMapping("addOrgUnit/{partitionName}")
    public void addOrgunit(@PathVariable("partitionName") String partitionName) {
        userClientService.createLdapOrgUnit(partitionName);
    }

    @PostMapping("authenticate")
    public Boolean authenticateUser(@RequestBody LdapUser user) {
//        userClientService.authenticate(user.getUsername(), user.getPassword());
        return ldapUserService.authenticate(user.getUsername(), user.getPassword());
    }

    @PostMapping("LdapUser")
    public void addUser(@RequestBody LdapUser user) {
        userClientService.createLdapUser(user.getUsername(), user.getPassword(), null);
    }

    @PutMapping("LdapUser")
    public void updateLdapUser(@RequestBody LdapUser user) {
        ldapUserService.modify(user.getUsername(), user.getPassword());
    }

    @GetMapping("rolesByUser/{user}")
    public List<LdapGroup> getUsersRoles(@PathVariable("user") String userAttribute) { //UID=admin,ou=users,dc=dcm4che,dc=org
        List<LdapGroup> lst = userClientService.getLdapGroupsByMemberDn(userAttribute);
        System.out.println("List--->" + lst.size());
        return lst;
    }

    @GetMapping("ldapUsers")
    public List<LdapUser> getUsersRoles() throws CommunicationException { //UID=admin,ou=users,dc=dcm4che,dc=org
        List<LdapUser> lst = userClientService.searchAllPersons();
        System.out.println("List--->" + lst.size());
        return lst;
    }

    @GetMapping("ldapUsers/{name}")
    public List<LdapUser> getUsersByName(@PathVariable("name") String name) { //UID=admin,ou=users,dc=dcm4che,dc=org
        List<LdapUser> lst = userClientService.search(name);
        System.out.println("List--->" + lst.size());
        return lst;
    }

    @PostMapping("addLdapUserRoles/{roleName}/{MEMBER}")
    public void doTest(@PathVariable("roleName") String roleName, @PathVariable("MEMBER") String member) { //UID=dinesh,ou=users,dc=dcm4che,dc=org
        userClientService.addUserToRole(member, roleName);
    }
}
