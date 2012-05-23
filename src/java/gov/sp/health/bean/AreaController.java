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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@RequestScoped
public class AreaController {

    /**
     * EJBs
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
    /**
     * Lists
     */
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
    /**
     * IDs
     */
    Long dpdhsID;
    Long mohID;
    Long phiID;
    Long phmID;
    Long gnID;

    /**
     *
     * @return
     */
    public Long getMohID() {
        return mohID;
    }

    /**
     *
     * @param mohID
     */
    public void setMohID(Long mohID) {
//        mOHArea = mohFacade.find(mohID);
        this.mohID = mohID;
    }

    /**
     *
     * @return
     */
    public Long getDpdhsID() {
        return dpdhsID;
    }

    /**
     *
     * @param dpdhsID
     */
    public void setDpdhsID(Long dpdhsID) {
//        dPDHSArea = dPDHSFacade.find(dpdhsID);
        this.dpdhsID = dpdhsID;
    }

    /**
     *
     * @return
     */
    public Long getPhiID() {
        return phiID;
    }

    /**
     *
     * @param phiID
     */
    public void setPhiID(Long phiID) {
//        pHIArea = phiFacade.find(phiID);
        this.phiID = phiID;
    }

    /**
     *
     * @return
     */
    public Long getPhmID() {
        return phmID;
    }

    /**
     *
     * @param phmID
     */
    public void setPhmID(Long phmID) {
//        pHMArea = pHMAreas.get(phmID);
        this.phmID = phmID;
    }

    /**
     *
     * @return
     */
    public Long getGnID() {
        return gnID;
    }

    /**
     *
     * @param gnID
     */
    public void setGnID(Long gnID) {
//        gNArea = gnFacade.find(gnID);
        this.gnID = gnID;
    }

    /**
     *
     */
    public AreaController() {
        dPDHSArea = new DPDHSArea();
        mOHArea = new MOHArea();
        pHIArea = new PHIArea();
        pHMArea = new PHMArea();
        gNArea = new GNArea();

        dpdhsID = 0l;
        mohID = 0l;
        phiID = 0l;
        phmID = 0l;
        gnID = 0l;
    }

    /**
     *
     * @return
     */
    public DPDHSAreaFacade getdPDHSFacade() {
        return dPDHSFacade;
    }

    /**
     *
     * @return
     */
    public GNAreaFacade getGnFacade() {
        return gnFacade;
    }

    /**
     *
     * @return
     */
    public MOHAreaFacade getMohFacade() {
        return mohFacade;
    }

    /**
     *
     * @return
     */
    public PHIAreaFacade getPhiFacade() {
        return phiFacade;
    }

    /**
     *
     * @return
     */
    public PHMAreaFacade getPhmFacade() {
        return phmFacade;
    }

    /**
     *
     * @return
     */
    public List<DPDHSArea> getdPDHSAreas() {
        String temSQL;
        temSQL = "SELECT d FROM DPDHSArea d ORDER BY d.name";
        return dPDHSFacade.findBySQL(temSQL);
    }

    /**
     *
     * @return
     */
    public List<GNArea> getgNAreas() {
        String temSQL = "";
        temSQL = "SELECT a FROM GNArea a ORDER BY a.name";
        return getGnFacade().findBySQL(temSQL);
    }

    /**
     *
     * @return
     */
    public List<MOHArea> getmOHAreas() {
        String temSQL = "SELECT m FROM MOHArea m ORDER By m.name";
        return getMohFacade().findBySQL(temSQL);
    }

    /**
     *
     * @return
     */
    public List<PHIArea> getpHIAreas() {
        String temSQL = "SELECT m FROM PHIArea m ORDER By m.name";
        return getPhiFacade().findBySQL(temSQL);
    }

    /**
     *
     * @return
     */
    public List<PHMArea> getpHMAreas() {
        String temSQL = "SELECT p FROM PHMArea p ORDER By p.name";
        return getPhmFacade().findBySQL(temSQL);
    }

    /**
     * Add new DPDHS Area
     */
    public void addDPDHS() {
        dPDHSFacade.create(dPDHSArea);
        JsfUtil.addSuccessMessage("DPDHS Area Added");
        dPDHSArea = new DPDHSArea();
    }

    /**
     * Add New MOH Area
     */
    public void addMOH() {
        dPDHSArea = dPDHSFacade.find(dpdhsID);
        mOHArea.setdPDHSArea(dPDHSArea);
        mohFacade.create(mOHArea);
        JsfUtil.addSuccessMessage("MOH Area Added");
        mOHArea = new MOHArea();
    }

    /**
     * Add New PHI Area
     */
    public void addPHI() {
        mOHArea = mohFacade.find(mohID);
        pHIArea.setmOHArea(mOHArea);
        phiFacade.create(pHIArea);
        JsfUtil.addSuccessMessage("PHI Area Added");
        pHIArea = new PHIArea();
    }    
    



    /**
     * Add New PHM Area
     */
    public void addPHM() {
        pHMArea.setpHIArea(pHIArea);
        phmFacade.create(pHMArea);
        JsfUtil.addSuccessMessage("Grama Niladhari Area Added");
        pHMArea = new PHMArea();
    }
    
    
    /**
     * Add New Grama Niladhari Area
     */
    public void addGN() {
        gNArea.setpHMArea(pHMArea);
        gnFacade.create(gNArea);
        JsfUtil.addSuccessMessage("Grama Niladhari Area Added");
        gNArea = new GNArea();

    }



    /**
     *
     * @return
     */
    public DPDHSArea getdPDHSArea() {
        return dPDHSArea;
    }

    /**
     *
     * @param dPDHSArea
     */
    public void setdPDHSArea(DPDHSArea dPDHSArea) {
        this.dPDHSArea = dPDHSArea;
    }

    /**
     *
     * @return
     */
    public GNArea getgNArea() {
        return gNArea;
    }

    /**
     *
     * @param gNArea
     */
    public void setgNArea(GNArea gNArea) {
        this.gNArea = gNArea;
    }

    /**
     *
     * @return
     */
    public MOHArea getmOHArea() {
        return mOHArea;
    }

    /**
     *
     * @param mOHArea
     */
    public void setmOHArea(MOHArea mOHArea) {
        this.mOHArea = mOHArea;
    }

    /**
     *
     * @return
     */
    public PHIArea getpHIArea() {
        return pHIArea;
    }

    /**
     *
     * @param pHIArea
     */
    public void setpHIArea(PHIArea pHIArea) {
        this.pHIArea = pHIArea;
    }

    /**
     *
     * @return
     */
    public PHMArea getpHMArea() {
        return pHMArea;
    }

    /**
     *
     * @param pHMArea
     */
    public void setpHMArea(PHMArea pHMArea) {
        this.pHMArea = pHMArea;
    }
}
