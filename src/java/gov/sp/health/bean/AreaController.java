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
     * Get MOH Area
     * @return
     */
    public Long getMohID() {
        return mohID;
    }

    /**
     * Set MOH Area
     * @param mohID
     */
    public void setMohID(Long mohID) {
//        mOHArea = mohFacade.find(mohID);
        this.mohID = mohID;
    }

    /**
     * Get DPDHS area
     * @return
     */
    public Long getDpdhsID() {
        return dpdhsID;
    }

    /**
     * Set DPDHS Area
     * @param dpdhsID
     */
    public void setDpdhsID(Long dpdhsID) {
//        dPDHSArea = dPDHSFacade.find(dpdhsID);
        this.dpdhsID = dpdhsID;
    }

    /**
     * Get PHI Area
     * @return
     */
    public Long getPhiID() {
        return phiID;
    }

    /**
     * Set PHI Area
     * @param phiID
     */
    public void setPhiID(Long phiID) {
//        pHIArea = phiFacade.find(phiID);
        this.phiID = phiID;
    }

    /**
     * Get PHM Area
     * @return
     */
    public Long getPhmID() {
        return phmID;
    }

    /**
     * Set PHM Area
     * @param phmID
     */
    public void setPhmID(Long phmID) {
//        pHMArea = pHMAreas.get(phmID);
        this.phmID = phmID;
    }

    /**
     * Get Grama Niladhari Area
     * @return
     */
    public Long getGnID() {
        return gnID;
    }

    /**
     * Set Grama Niladhari Area
     * @param gnID
     */
    public void setGnID(Long gnID) {
//        gNArea = gnFacade.find(gnID);
        this.gnID = gnID;
    }

    /**
     * Initiate Area Controller
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
     * Get DPDHS Area Facade
     * @return
     */
    public DPDHSAreaFacade getdPDHSFacade() {
        return dPDHSFacade;
    }

    /**
     * Get Grama Niladhari Area Facade
     * @return
     */
    public GNAreaFacade getGnFacade() {
        return gnFacade;
    }

    /**
     * Get MOH Area Facade
     * @return
     */
    public MOHAreaFacade getMohFacade() {
        return mohFacade;
    }

    /**
     * Get PHI Area Facade
     * @return
     */
    public PHIAreaFacade getPhiFacade() {
        return phiFacade;
    }

    /**
     * Get PHM Area Facade
     * @return
     */
    public PHMAreaFacade getPhmFacade() {
        return phmFacade;
    }

    /**
     * Get All DPDHS Areas
     * @return
     */
    public List<DPDHSArea> getdPDHSAreas() {
        String temSQL;
        temSQL = "SELECT d FROM DPDHSArea d ORDER BY d.name";
        return dPDHSFacade.findBySQL(temSQL);
    }

    /**
     * Get All Grama Niladhari Areas
     * @return
     */
    public List<GNArea> getgNAreas() {
        String temSQL = "";
        temSQL = "SELECT a FROM GNArea a ORDER BY a.name";
        return getGnFacade().findBySQL(temSQL);
    }

    /**
     * Get All MOH Areas
     * @return
     */
    public List<MOHArea> getmOHAreas() {
        String temSQL = "SELECT m FROM MOHArea m ORDER By m.name";
        return getMohFacade().findBySQL(temSQL);
    }

    /**
     * Get all PHI Areas
     * @return
     */
    public List<PHIArea> getpHIAreas() {
        String temSQL = "SELECT m FROM PHIArea m ORDER By m.name";
        return getPhiFacade().findBySQL(temSQL);
    }

    /**
     * Get All PHM Areas
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
     * Get current DPDHS Area
     * @return
     */
    public DPDHSArea getdPDHSArea() {
        return dPDHSArea;
    }

    /**
     * Set current DPDHS Area
     * @param dPDHSArea
     */
    public void setdPDHSArea(DPDHSArea dPDHSArea) {
        this.dPDHSArea = dPDHSArea;
    }

    /**
     * Get current Grama Niladhari Area
     * @return
     */
    public GNArea getgNArea() {
        return gNArea;
    }

    /**
     * Set current Grama Niladhari Area
     * @param gNArea
     */
    public void setgNArea(GNArea gNArea) {
        this.gNArea = gNArea;
    }

    /**
     * Get current MOH Area
     * @return
     */
    public MOHArea getmOHArea() {
        return mOHArea;
    }

    /**
     * Set current MOH area
     * @param mOHArea
     */
    public void setmOHArea(MOHArea mOHArea) {
        this.mOHArea = mOHArea;
    }

    /**
     * Get Current PHI Area
     * @return
     */
    public PHIArea getpHIArea() {
        return pHIArea;
    }

    /**
     * Set current PHI Area
     * @param pHIArea
     */
    public void setpHIArea(PHIArea pHIArea) {
        this.pHIArea = pHIArea;
    }

    /**
     * Get current PHM area
     * @return
     */
    public PHMArea getpHMArea() {
        return pHMArea;
    }

    /**
     * Set current PHM Area
     * @param pHMArea
     */
    public void setpHMArea(PHMArea pHMArea) {
        this.pHMArea = pHMArea;
    }
}
