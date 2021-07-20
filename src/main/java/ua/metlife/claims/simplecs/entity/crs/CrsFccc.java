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
@IdClass(CrsFcccPk.class)
@Data
@Table(name = "CRSFCCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsFccc.findAll", query = "SELECT g FROM CrsFccc g"),
    @NamedQuery(name = "CrsFccc.findByDiseaseOD", query = "SELECT g FROM CrsFccc g where g.agt = 'CD'"),
    @NamedQuery(name = "CrsFccc.findByAccidentItem", query = "SELECT g FROM CrsFccc g where g.agt = 'EA'"),    
    @NamedQuery(name = "CrsFccc.findByAccidentGroup", query = "SELECT g FROM CrsFccc g where g.agt = 'CA'")

})

public class CrsFccc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CCCCATG")
    private String agt;
    @Id
    @Column(name = "CCCCODE")
    private String code;
    @Id
    @Column(name = "CCCDESC")
    private String desc;

    public String toString() {
        return code + " > " + desc.trim();
    }

}
