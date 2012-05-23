/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.autobean.DPDHSAreaFacade;
import gov.sp.health.autobean.GNAreaFacade;
import gov.sp.health.autobean.MOHAreaFacade;
import gov.sp.health.autobean.PHIAreaFacade;
import gov.sp.health.autobean.PHMAreaFacade;
import gov.sp.health.entity.DPDHSArea;
import gov.sp.health.entity.GNArea;
import gov.sp.health.entity.MOHArea;
import gov.sp.health.entity.PHIArea;
import gov.sp.health.entity.PHMArea;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@RequestScoped
public class AreaController {

    /**
     * Creates a new instance of AreaController
     */
    @EJB
    DPDHSAreaFacade dPDHSFacade;
    @EJB
    MOHAreaFacade mohFacade;
    @EJB
    PHIAreaFacade phiFacade;
    @EJB
    PHMAreaFacade phmFacade;
    @EJB
    GNAreaFacade gnFacade;
    List<DPDHSArea> dPDHSAreas;
    List<MOHArea> mOHAreas;
    List<PHIArea> pHIAreas;
    List<PHMArea> pHMAreas;
    List<GNArea> gNAreas;
    DPDHSArea dPDHSArea;
    MOHArea mOHArea;
    PHIArea pHIArea;
    PHMArea pHMArea;
    GNArea gNArea;

    public AreaController() {
        dPDHSArea = new DPDHSArea();
        mOHArea = new MOHArea();
        pHIArea = new PHIArea();
        pHMArea = new PHMArea();
        gNArea = new GNArea();
    }

    public DPDHSAreaFacade getdPDHSFacade() {
        return dPDHSFacade;
    }

    public GNAreaFacade getGnFacade() {
        return gnFacade;
    }

    public MOHAreaFacade getMohFacade() {
        return mohFacade;
    }

    public PHIAreaFacade getPhiFacade() {
        return phiFacade;
    }

    public PHMAreaFacade getPhmFacade() {
        return phmFacade;
    }

    public List<DPDHSArea> getdPDHSAreas() {
        return dPDHSAreas;
    }

    public List<GNArea> getgNAreas() {
        String temSQL = "";
        if (pHMArea.getId() == 0) {
            temSQL = "SELECT a FROM GNArea a ORDER BY a.name";
        } else {
            
            temSQL = "SELECT gn FROM GNArea gn INNER JOIN gn.pHMArea phm WHERE phm.id = " + pHMArea.getId();
                    
        }
        return getGnFacade().findBySQL(temSQL);
    }

    public List<MOHArea> getmOHAreas() {
        String temSQL = "SELECT m FROM MOHArea m ORDER By m.name";
        return getMohFacade().findBySQL(temSQL);
    }

    public List<PHIArea> getpHIAreas() {
        return pHIAreas;
    }

    public List<PHMArea> getpHMAreas() {
        String temSQL = "SELECT p FROM PHMArea p ORDER By p.name";
        return getPhmFacade().findBySQL(temSQL);
    }

    public void addGN() {
//        gNArea.setpHMArea(pHMArea);
        gnFacade.create(gNArea);
        JsfUtil.addSuccessMessage("Grama Niladhari Area Added");
        gNArea = new GNArea();

    }

    public void addPHM(){
        pHMArea.setpHIArea(pHIArea);
        phmFacade.create(pHMArea);
        JsfUtil.addSuccessMessage("Grama Niladhari Area Added");
        pHMArea = new PHMArea();
    }
  
    public DPDHSArea getdPDHSArea() {
        return dPDHSArea;
    }

    public void setdPDHSArea(DPDHSArea dPDHSArea) {
        this.dPDHSArea = dPDHSArea;
    }

    public GNArea getgNArea() {
        return gNArea;
    }

    public void setgNArea(GNArea gNArea) {
        this.gNArea = gNArea;
    }

    public MOHArea getmOHArea() {
        return mOHArea;
    }

    public void setmOHArea(MOHArea mOHArea) {
        this.mOHArea = mOHArea;
    }

    public PHIArea getpHIArea() {
        return pHIArea;
    }

    public void setpHIArea(PHIArea pHIArea) {
        this.pHIArea = pHIArea;
    }

    public PHMArea getpHMArea() {
        return pHMArea;
    }

    public void setpHMArea(PHMArea pHMArea) {
        this.pHMArea = pHMArea;
    }
}
