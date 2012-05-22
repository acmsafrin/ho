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

    public String loginAction(){
        if (login()){
            return "registered";
        }else{
            JsfUtil.addErrorMessage("Login Failure. Please try again");
            return "";
        }
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
            JsfUtil.addSuccessMessage("First Visit");
            return true;
        } else {
            JsfUtil.addSuccessMessage("First Visit");Not
            return false;
        }
        
    }

    private boolean checkUsers() {
        List<WebUser> allUsers = getFacede().findAll();
        for (WebUser u : allUsers) {
            if (HOSecurity.encrypt(u.getName()).equals(userName)) {
                if (HOSecurity.matchPassword(passord, u.getWebUserPassword())) {
                    SessionController.setLoggedUser(u);
                    JsfUtil.addSuccessMessage("Logged successfully");
                    return true;
                }
            }
        }
        return false;
    }

    public WebUser getCurrent() {
        return current;
    }

    public void setCurrent(WebUser current) {
        this.current = current;
    }

    public WebUserFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(WebUserFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
}
