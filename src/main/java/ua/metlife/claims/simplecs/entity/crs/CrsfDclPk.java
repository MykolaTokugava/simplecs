/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crs;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author NPopov
 */
@Data
public class CrsfDclPk implements Serializable {

    private String dclobj;
    private BigDecimal dclpw2;
    private String dcldesc;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.dclobj);
        hash = 23 * hash + Objects.hashCode(this.dclpw2);
        hash = 23 * hash + Objects.hashCode(this.dcldesc);
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
        final CrsfDclPk other = (CrsfDclPk) obj;
        if (!Objects.equals(this.dclobj, other.dclobj)) {
            return false;
        }
        if (!Objects.equals(this.dclpw2, other.dclpw2)) {
            return false;
        }
        if (!Objects.equals(this.dcldesc, other.dcldesc)) {
            return false;
        }
        return true;
    }

}
