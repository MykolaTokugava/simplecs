package ua.metlife.claims.simplecs.ClaimsEntity;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Data;

import java.util.Date;

/**
 *
 * @author NPopov
 */
@Data
public class ClientData implements ClientInterface {

    private String nameFull;
    private String shortName;
    private String name;
    private String lastName;
    private String nameIni;
    private String patronimic;
    private Date dob;
    private String passport;
    private String taxcode;
    private String fullAddress;
    private String city;
    private String zip;
    private String region;

    @Override
    public String getNameFull() {
        return nameFull;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPatronimic() {
        return patronimic;
    }

    @Override
    public Date getDob() {
        return dob;
    }

    @Override
    public String getPassport() {
        return passport;
    }

    @Override
    public String getTaxCode() {
        return taxcode;
    }

}
