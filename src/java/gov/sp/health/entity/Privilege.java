/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@Entity
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    boolean demographyAdd;
    boolean demographyEdit;
    boolean demographyDelete;
    boolean demographyView;
    boolean inventaryAdd;
    boolean inventaryEdit;
    boolean inventaryDelete;
    boolean inventaryView;
    boolean MSAdd;
    boolean MSEdit;
    boolean MSDelete;
    boolean MSView;
    boolean caderAdd;
    boolean caderEdit;
    boolean caderDelete;
    boolean caderView;
    boolean libraryAdd;
    boolean libraryEdit;
    boolean libraryDelete;
    boolean libraryView;
    boolean vehicleAdd;
    boolean vehicleEdit;
    boolean vehicleDelete;
    boolean vehicleView;
    
    @ManyToOne
    Province restrictedProvince;
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.sp.health.entity.Privilage[ id=" + id + " ]";
    }
}
