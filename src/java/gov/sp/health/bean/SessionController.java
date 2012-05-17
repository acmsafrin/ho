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
    
    
    /** Creates a new instance of SessionController */
    public SessionController() {
    }

    public static WebUser getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(WebUser loggedUser) {
        SessionController.loggedUser = loggedUser;
    }
    
    
}
