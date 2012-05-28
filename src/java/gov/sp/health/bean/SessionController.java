/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.entity.WebUser;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical Informatics)
 */
@ManagedBean
@SessionScoped
public class SessionController {

    static WebUser loggedUser = null;
    
    static boolean logged = false;
    
    static boolean activated = false;
    
    /** Creates a new instance of SessionController */
    public SessionController() {
    }

    /**
     * 
     * @return
     */
    public static WebUser getLoggedUser() {
        return loggedUser;
    }

    /**
     * 
     * @param loggedUser
     */
    public static void setLoggedUser(WebUser loggedUser) {
        SessionController.loggedUser = loggedUser;
    }

    /**
     * 
     * @return
     */
    public static boolean isLogged() {
        return logged;
    }

    /**
     * Set whether user 
     * @param logged
     */
    public static void setLogged(boolean logged) {
        SessionController.logged = logged;
    }

    /**
     * Get whether user is activated
     * @return
     */
    public static boolean isActivated() {
        return activated;
    }

    /**
     * Mark logged user as activated
     * @param activated
     */
    public static void setActivated(boolean activated) {
        SessionController.activated = activated;
    }
    
    
    
}
