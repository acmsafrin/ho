/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@SessionScoped
public class MOHController {

    String temStr;
    
    /**
     * Creates a new instance of MOHController
     */
    public MOHController() {
    }

    public String getTemStr() {
        if ("".equals(temStr)){
            temStr="My Dear God,Please help me";
        }
        return temStr;
    }

    public void setTemStr(String temStr) {
        this.temStr = temStr;
    }
    
    
   
    
}
