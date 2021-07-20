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
@Table(name = "CRSFPCD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsFpcd.findAll", query = "SELECT g FROM CrsFpcd g")
})

public class CrsFpcd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PCDPLAN")
    private String pcdplan;
    
    @Column(name = "PCDFAOK")
    private String pcdfaok;
    
    @Column(name = "PCDPLOB")
    private String pcdplob;    
    
    @Column(name = "PCDNAME")
    private String pcdname;
    
    @Column(name = "PCDHSC")
    private String pcdhsc;        
    
    @Column(name = "PCDIDX")
    private String pcdidx;
    
    @Column(name = "PCDREIN")
    private String pcdrein;       
    
    @Column(name = "PCDIBNRSC")
    private String pcdibnrsc;
    
    @Column(name = "PCDIBNRCC")
    private String pcdibnrcc;      
    
    @Column(name = "PCDIBNRPC")
    private String pcdibnrpc;          

    @Column(name = "PCDIBNRAR")
    private String pcdibnrar;      
    
    @Column(name = "PCDRBAS")
    private String pcdrbas;     
    
    @Column(name = "PCDRDET")
    private String pcdrdet;      
    
    @Column(name = "PCDRSPE")
    private String pcdrspe;         
    
    @Column(name = "PCDINCRCR")
    private String pcdincrcr;      
    
    @Column(name = "PCDINCRDR")
    private String pcdincrdr;             
    
    @Column(name = "PCDDECRCR")
    private String pcddecrcr;      
    
    @Column(name = "PCDDECRDR")
    private String pcddecrdr;        
    
    
    @Column(name = "PCDBNFTCR")
    private String pcdbnftcr;      
    
    @Column(name = "PCDBNFTDR")
    private String pcdbnftdr;    
    
    public String toString() {
        return pcdplan.trim().toUpperCase();
    }

}
