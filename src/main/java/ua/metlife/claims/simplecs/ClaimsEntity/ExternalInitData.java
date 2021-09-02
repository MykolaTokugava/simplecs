/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ua.metlife.claims.simplecs.processing.Config;

/**
 *
 * @author NPopov
 */
@Slf4j
@Data
public class ExternalInitData {
    
    private String polNumber;
    private String claimNumber;
    private SystemsEnum system;
    private Boolean insuredOrPayer;
    
    private String ldapLogin;
    private String crsLogin;

    
    
    public String getCrsLogin() {
        if (ldapLogin==null) {
            return "Set Ldap Login";
        }
        return Config.getCrsLogin(ldapLogin);
    }

    public String toString() {
        return ldapLogin == null ? "Set Ldap Login" : ldapLogin;
    }    
    
}
