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
@Table(name = "CLMAGT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClmAgt.findAll", query = "SELECT g FROM ClmAgt g"),
    @NamedQuery(name = "ClmAgt.findByClmNum", query = "SELECT g FROM ClmAgt g where g.clmnum=:clmNumber"),
})

public class ClmAgt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CLMNUM")
    private String clmnum;

    @Column(name = "POLNUM")
    private String polnum;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "AGTNAME1")
    private String agtname1;

    @Column(name = "AGTNAME2")
    private String agtname2;

    @Column(name = "AGTPHONE1")
    private String agtphone1;

    @Column(name = "AGTPHONE2")
    private String agtphone2;

    @Column(name = "AGTMAIL1")
    private String agtemail1;

    @Column(name = "AGTMAIL2")
    private String agtemail2;
    
//    @JoinColumn(name = "CLMNUM" , referencedColumnName="CLMNO")
//    @OneToOne
//    private CrsfPol pol;     
    
    public String toString() {
        return clmnum.trim() + " [" + polnum + "]";
    }

}
