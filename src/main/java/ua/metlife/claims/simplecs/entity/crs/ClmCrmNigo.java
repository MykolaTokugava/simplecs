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

/**
 *
 * @author NPopov
 */
@Entity
@Data
@Table(name = "CRM_CLAIM_NIGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClmCrmNigo.findAll", query = "SELECT g FROM ClmCrmNigo g"),
    @NamedQuery(name = "ClmCrmNigo.findById", query = "SELECT g FROM ClmCrmNigo g where g.claimNo=:clmNumber")
})

public class ClmCrmNigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CLAIMNO")
    private String claimNo;

    @Column(name = "NIGO")
    private Integer nigo;

    public String toString() {
        return claimNo.trim() + " [" + nigo.toString() + "]";
    }

}
