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
public class CrsfRsbHiPk implements Serializable {

    private String hrsbclmno;
    private String hrsbpolno;
    private String hrsbsrplan;
    private String hrsbseqno;

    @Override 
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.hrsbclmno);
        hash = 23 * hash + Objects.hashCode(this.hrsbpolno);
        hash = 23 * hash + Objects.hashCode(this.hrsbsrplan);
        hash = 23 * hash + Objects.hashCode(this.hrsbseqno);
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
        final CrsfRsbHiPk other = (CrsfRsbHiPk) obj;
        if (!Objects.equals(this.hrsbclmno, other.hrsbclmno)) {
            return false;
        }
        if (!Objects.equals(this.hrsbpolno, other.hrsbpolno)) {
            return false;
        }
        if (!Objects.equals(this.hrsbsrplan, other.hrsbsrplan)) {
            return false;
        }
        if (!Objects.equals(this.hrsbseqno, other.hrsbseqno)) {
            return false;
        }        
        return true;
    }

}
