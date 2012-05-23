/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.autobean.MOHAreaFacade;
import gov.sp.health.entity.MOHArea;
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
public class PprBean {
@EJB
    MOHAreaFacade myFacade;
    
    String firstName;
    String surname;
    
    
    /**
     * Creates a new instance of PprBean
     */
    public PprBean() {
    }
    
    public void savePerson(){
        JsfUtil.addSuccessMessage("Tyring to Save");
        try{
            MOHArea area = new MOHArea();
            area.setName(surname);
            area.setDescription(firstName);
            myFacade.create(area);
            JsfUtil.addSuccessMessage("Saved");
        }catch(Exception e){
            JsfUtil.addSuccessMessage(e.getMessage());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
    
    
}
