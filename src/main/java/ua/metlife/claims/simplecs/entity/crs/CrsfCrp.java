/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crs;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author NPopov
 */
@Entity
@Data
@Table(name = "CRSFCRP")
@XmlRootElement

public class CrsfCrp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "POLNO")
    private String polno;

    @Column(name = "PLOB")
    private String plob;

    @Column(name = "EFFDTE")
    private String effdte;

    @Column(name = "MATDTE")
    private String matdte;

    @Column(name = "ONAME")
    private String oname;

    @Column(name = "ADRS1")
    private String adrs1;

    @Column(name = "ADRS2")
    private String adrs2;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "CITY")
    private String city;

    @Column(name = "RECUSR")
    private String recusr;

    @Column(name = "RECDTE")
    private String recdte;



    
    

}
