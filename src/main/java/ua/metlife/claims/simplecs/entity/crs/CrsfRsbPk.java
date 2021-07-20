/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crs;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author NPopov
 */
@Data
public class CrsfRsbPk implements Serializable {

    private String rsbclmno;
    private String rsbpolno;
    private String rsbplcd;

    @Override 
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.rsbclmno);
        hash = 23 * hash + Objects.hashCode(this.rsbpolno);
        hash = 23 * hash + Objects.hashCode(this.rsbplcd);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CrsfRsbPk other = (CrsfRsbPk) obj;
        if (!Objects.equals(this.rsbclmno, other.rsbclmno)) {
            return false;
        }
        if (!Objects.equals(this.rsbpolno, other.rsbpolno)) {
            return false;
        }
        if (!Objects.equals(this.rsbplcd, other.rsbplcd)) {
            return false;
        }
        return true;
    }

}
