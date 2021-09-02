/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

import java.util.Date;

/**
 *
 * @author NPopov
 */
public interface ClientInterface {

    public String getNameFull();

    public String getShortName();

    public String getName();

    public String getLastName();

    public String getPatronimic();

    public Date getDob();

    public String getPassport();

    public String getTaxCode();

    public String getFullAddress();

    public String getCity();

    public String getZip();

    public String getRegion();

}
