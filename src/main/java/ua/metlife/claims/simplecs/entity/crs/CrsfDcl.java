/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crs;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author NPopov
 */
@Entity
@IdClass(CrsfDclPk.class)
@Data
@Table(name = "CRSFDCL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsfDcl.findAll", query = "SELECT g FROM CrsfDcl g")
})

public class CrsfDcl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Generated(GenerationTime.NEVER)

    @Column(name = "DCLOBJ")
    private String dclobj;
    @Id
    @Column(name = "DCLPW2")
    private BigDecimal dclpw2;
    @Id
    @Column(name = "DCLDESC")
    private String dcldesc;

    public String toString() {
        return dcldesc.trim() + " [" + dclobj.toString() + "]";
    }

    public String toComapre() {
        return dclobj.toString().trim()+"_"+dclpw2.toString();
    }    
    
}
