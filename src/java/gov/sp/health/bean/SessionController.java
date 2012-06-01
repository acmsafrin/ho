/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.entity.Privilege;
import gov.sp.health.entity.WebUser;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical Informatics)
 */
@ManagedBean
@SessionScoped
@Stateless
public class SessionController {

     WebUser loggedUser = null;
    
     boolean logged = false;
    
     boolean activated = false;
    
    
     Privilege privilege = null;
    
    /** Creates a new instance of SessionController */
    public SessionController() {
    }

    /**
     * 
     * @return
     */
    public  WebUser getLoggedUser() {
        return loggedUser;
    }

    /**
     * 
     * @param loggedUser
     */
    public  void setLoggedUser(WebUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    /**
     * 
     * @return
     */
    public  boolean isLogged() {
        return logged;
    }

    /**
     * Set whether user 
     * @param logged
     */
    public  void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * Get whether user is activated
     * @return
     */
    public  boolean isActivated() {
        return activated;
    }

    /**
     * Mark logged user as activated
     * @param activated
     */
    public  void setActivated(boolean activated) {
        this.activated = activated;
    }

    public  Privilege getPrivilege() {
        return privilege;
    }

    public  void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
    
    
    
}
