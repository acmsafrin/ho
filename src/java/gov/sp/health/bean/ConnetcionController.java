/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.autobean.WebUserFacade;
import gov.sp.health.entity.Person;
import gov.sp.health.entity.WebUser;
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
public class ConnetcionController {

    @EJB
    WebUserFacade ejbFacade;
    WebUser current;
    String userName;
    String passord;

    /**
     * Creates a new instance of ConnetcionController
     */
    public ConnetcionController() {
    }

    private WebUserFacade getFacede() {
        return ejbFacade;
    }

    private boolean login() {
        if (isFirstVisit()) {
            prepareFirstVisit();
            return true;
        } else {
            return checkUsers();
        }
    }

    private void prepareFirstVisit() {
        WebUser user = new WebUser();
        Person person = new Person();
        person.setName(userName);
        user.setName(HOSecurity.encrypt(userName));
        user.setWebUserPassword(HOSecurity.hash(passord));
        user.setWebUserPerson(person);
        
    }

    private boolean isFirstVisit() {
        if (getFacede().count() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUsers() {
        List<WebUser> allUsers = getFacede().findAll();
        for (WebUser u : allUsers) {
            if (HOSecurity.encrypt(u.getName()).equals(userName)) {
                if (HOSecurity.matchPassword(passord, u.getWebUserPassword())) {
                    SessionController.setLoggedUser(u);
                    return true;
                }
            }
        }
        return false;
    }
}
