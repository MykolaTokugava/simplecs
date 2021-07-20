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
@Data
@Table(name = "CRSFSRB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsfSrb.findAll", query = "SELECT g FROM CrsfSrb g")
})

public class CrsfSrb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SRBCLMNO")
    private String srbclmno;
    @Id
    @Column(name = "SRBSETNO")
    private String srbsetno;
    @Id
    @Column(name = "SRBPOLNO")
    private String srbpolno;
    @Id
    @Column(name = "SRBBFTPLC")
    private String srbbftplc;

    @Column(name = "SRBRSBTP")
    private String srbrsbtp;

    @Column(name = "SRBRSBID")
    private String srbrsbid;

    @Column(name = "SRBRSBDP")
    private String srbrsbdp;

    @Column(name = "SRBBFTDSC")
    private String srbbftdsc;

    @Column(name = "SRBBFTPER")
    private BigDecimal srbbftper;

    @Column(name = "SRBBFTSET")
    private BigDecimal srbbftset;
    
    @Column(name = "SRBBFTPAY")
    private BigDecimal srbbftpay;  
    
    @Column(name = "SRBBFTSGN")
    private String srbbftsgn;

    @Column(name = "SRBBFTPGM")
    private String srbbftpgm;

    @Column(name = "SRBRECUSR")
    private String srbrecusr;

    @Column(name = "SRBRECDTE")
    private String srbrecdte;    

    
    public String toString() {
        return "";
    }

}
 