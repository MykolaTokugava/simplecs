/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

/**
 *
 * @author NPopov
 */
public enum ClaimActionEnum {

    select,
    DEFAULT,
    OPEN,
    FULLDECLINE,
    DECLINE,
    SETTLED_OPENED,
    SETTLED_APPROVED,
    PAYD,
    CLOSE

}
