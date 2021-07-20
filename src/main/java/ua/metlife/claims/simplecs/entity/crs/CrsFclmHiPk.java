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
public class CrsFclmHiPk implements Serializable {

    private String clmno;
    private String seqno;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.clmno);
        hash = 23 * hash + Objects.hashCode(this.seqno);
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
        final CrsFclmHiPk other = (CrsFclmHiPk) obj;
        if (!Objects.equals(this.clmno, other.clmno)) {
            return false;
        }
        if (!Objects.equals(this.seqno, other.seqno)) {
            return false;
        }

        return true;
    }

}
