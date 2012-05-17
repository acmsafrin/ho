/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.autobean.DPDHSAreaFacade;
import gov.sp.health.entity.DPDHSArea;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@RequestScoped
public class DPDHSManager {

    List<DPDHSArea> dPDHSAreas;
    
    /**
     * Creates a new instance of DPDHSManager
     */
    public DPDHSManager() {
    }

    private void emptyList(){
        dPDHSAreas = null;
    }
    
    public List<DPDHSArea> getdPDHSAreas() {
        if (dPDHSAreas == null){
            dPDHSAreas = new DPDHSAreaFacade().findAll();
        }
        return dPDHSAreas;
    }
    
    
}
