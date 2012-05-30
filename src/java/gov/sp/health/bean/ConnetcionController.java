/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.autobean.PersonFacade;
import gov.sp.health.autobean.PrivilegeFacade;
import gov.sp.health.autobean.WebUserFacade;
import gov.sp.health.autobean.WebUserRoleFacade;
import gov.sp.health.entity.Person;
import gov.sp.health.entity.Privilege;
import gov.sp.health.entity.WebUser;
import gov.sp.health.entity.WebUserRole;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.omg.PortableInterceptor.ACTIVE;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@RequestScoped
public class ConnetcionController {

    @EJB
    WebUserFacade uFacade;
    @EJB
    PersonFacade pFacade;
    @EJB
    WebUserRoleFacade rFacade;
    @EJB
    PrivilegeFacade vFacade;
    //
    WebUser current;
    String userName;
    String passord;
    String newPassword;
    String newPasswordConfirm;
    String newPersonName;
    String newUserName;
    String newDesignation;
    String newInstitution;
    String newPasswordHint;
    //
    boolean logged;
    boolean activated;
    Privilege privilege;

    /**
     * Creates a new instance of ConnetcionController
     */
    public ConnetcionController() {
    }

    private WebUserFacade getFacede() {
        return uFacade;
    }

    public String loginAction() {
        if (login()) {
            return "registered";
        } else {
            JsfUtil.addErrorMessage("Login Failure. Please try again");
            return "";
        }
    }

    private boolean login() {
        if (isFirstVisit()) {
            prepareFirstVisit();
            return true;
        } else {
            JsfUtil.addSuccessMessage("Checking Old Users");
            return checkUsers();
        }
    }

    private void prepareFirstVisit() {
        WebUser user = new WebUser();
        Person person = new Person();
        person.setName(userName);
        pFacade.create(person);

        WebUserRole role = new WebUserRole();
        role.setName("Administrator");
        rFacade.create(role);

        user.setName(HOSecurity.encrypt(userName));
        user.setWebUserPassword(HOSecurity.hash(passord));
        user.setWebUserPerson(person);
        user.setActivated(true);
        user.setRole(role);
        uFacade.create(user);

        Privilege p = new Privilege();
        //Cadre
        p.setCaderAdd(true);
        p.setCaderEdit(true);
        p.setCaderDelete(true);
        p.setCaderView(true);
        //Demography
        p.setDemographyAdd(true);
        p.setDemographyEdit(true);
        p.setDemographyDelete(true);
        p.setDemographyView(true);
        //
        p.setInventaryAdd(true);
        p.setInventaryEdit(true);
        p.setInventaryDelete(true);
        p.setInventaryView(true);
        //
        p.setLibraryAdd(true);
        p.setLibraryEdit(true);
        p.setLibraryDelete(true);
        p.setLibraryView(true);
        //
        p.setMSAdd(true);
        p.setMSEdit(true);
        p.setMSDelete(true);
        p.setMSView(true);
        //
        p.setVehicleAdd(true);
        p.setVehicleEdit(true);
        p.setVehicleDelete(true);
        p.setVehicleView(true);
        //
        p.setActivateAccounts(true);
        p.setDectivateAccounts(true);
        p.setManageAccounts(true);
        //
        p.setWebUser(user);
        //
        getvFacade().create(p);

        //
        //Privilege for Administrator Role
        p = new Privilege();
        //Cadre
        p.setCaderAdd(true);
        p.setCaderEdit(true);
        p.setCaderDelete(true);
        p.setCaderView(true);
        //Demography
        p.setDemographyAdd(true);
        p.setDemographyEdit(true);
        p.setDemographyDelete(true);
        p.setDemographyView(true);
        //
        p.setInventaryAdd(true);
        p.setInventaryEdit(true);
        p.setInventaryDelete(true);
        p.setInventaryView(true);
        //
        p.setLibraryAdd(true);
        p.setLibraryEdit(true);
        p.setLibraryDelete(true);
        p.setLibraryView(true);
        //
        p.setMSAdd(true);
        p.setMSEdit(true);
        p.setMSDelete(true);
        p.setMSView(true);
        //
        p.setVehicleAdd(true);
        p.setVehicleEdit(true);
        p.setVehicleDelete(true);
        p.setVehicleView(true);
        //
        p.setActivateAccounts(true);
        p.setDectivateAccounts(true);
        p.setManageAccounts(true);
        //
        p.setWebUserRole(role);
        //
        getvFacade().create(p);

//        JsfUtil.addSuccessMessage("New User Added");



    }

    public void registeUser() {
        WebUser user = new WebUser();
        Person person = new Person();
        person.setName(newPersonName);
        pFacade.create(person);
        user.setName(HOSecurity.encrypt(newUserName));
        user.setWebUserPassword(HOSecurity.hash(newPassword));
        user.setWebUserPerson(person);
        user.setActivated(false);
        uFacade.create(user);
        JsfUtil.addSuccessMessage("New User Registered. You will be able to access the system when the administrater activate your account.");
        SessionController.setLoggedUser(user);
        SessionController.setLogged(Boolean.TRUE);
        SessionController.setActivated(false);

    }

    private boolean isFirstVisit() {
        if (getFacede().count() <= 0) {
//            JsfUtil.addSuccessMessage("First Visit");
            return true;
        } else {
//            JsfUtil.addSuccessMessage("Not, Not First Visit");
            return false;
        }

    }

    private boolean checkUsers() {
        JsfUtil.addSuccessMessage("Going to check users");
        String temSQL;
        temSQL = "SELECT u FROM WebUser u WHERE u.retired = false";
        List<WebUser> allUsers = getFacede().findBySQL(temSQL);
        for (WebUser u : allUsers) {
            if (HOSecurity.decrypt(u.getName()).equalsIgnoreCase(userName)) {
//                JsfUtil.addSuccessMessage("A user found");

                if (HOSecurity.matchPassword(passord, u.getWebUserPassword())) {
                    SessionController.setLoggedUser(u);
                    SessionController.setLogged(Boolean.TRUE);
                    SessionController.setActivated(u.isActivated());
                    SessionController.setPrivilege(allUserPrivilege(u));
                    JsfUtil.addSuccessMessage("Logged successfully");
                    return true;
                }
            }
        }
        return false;
    }

    private Privilege allUserPrivilege(WebUser user) {
        Privilege p = new Privilege();

        String temSQL = "SELECT p From Privilege p WHERE p.webUser.id = " + user.getId();
        List<Privilege> allP = getvFacade().findBySQL(temSQL);

        for (Privilege pv : allP) {
            //Cadre
            if (pv.isCaderAdd() == true) {
                p.setCaderAdd(true);
            }
            if (pv.isCaderEdit() == true) {
                p.setCaderEdit(true);
            }
            if (pv.isCaderDelete() == true) {
                p.setCaderDelete(true);
            }
            if (pv.isCaderView() == true) {
                p.setCaderView(true);
            }
            //Demography
            if (pv.isDemographyAdd() == true) {
                p.setDemographyAdd(true);
            }
            if (pv.isDemographyEdit() == true) {
                p.setDemographyEdit(true);
            }
            if (pv.isDemographyDelete() == true) {
                p.setDemographyDelete(true);
            }
            if (pv.isDemographyView() == true) {
                p.setDemographyView(true);
            }
            //
            if (pv.isInventaryAdd() == true) {
                p.setInventaryAdd(true);
            }
            if (pv.isInventaryEdit() == true) {
                p.setInventaryEdit(true);
            }
            if (pv.isInventaryDelete() == true) {
                p.setInventaryDelete(true);
            }
            if (pv.isInventaryView() == true) {
                p.setInventaryView(true);
            }
            //
            if (pv.isLibraryAdd() == true) {
                p.setLibraryAdd(true);
            }
            if (pv.isLibraryEdit() == true) {
                p.setLibraryEdit(true);
            }
            if (pv.isLibraryDelete() == true) {
                p.setLibraryDelete(true);
            }
            if (pv.isLibraryView() == true) {
                p.setLibraryView(true);
            }
            //
            if (pv.isMSAdd() == true) {
                p.setMSAdd(true);
            }
            if (pv.isMSEdit() == true) {
                p.setMSEdit(true);
            }
            if (pv.isMSDelete() == true) {
                p.setMSDelete(true);
            }
            if (pv.isMSView() == true) {
                p.setMSView(true);
            }
            //
            if (pv.isVehicleAdd() == true) {
                p.setVehicleAdd(true);
            }
            if (pv.isVehicleEdit() == true) {
                p.setVehicleEdit(true);
            }
            if (pv.isVehicleDelete() == true) {
                p.setVehicleDelete(true);
            }
            if (pv.isVehicleView() == true) {
                p.setVehicleView(true);
            }
            //
            if (pv.isActivateAccounts() == true) {
                p.setActivateAccounts(true);
            }
            if (pv.isDectivateAccounts() == true) {
                p.setDectivateAccounts(true);
            }
            if (pv.isManageAccounts() == true) {
                p.setManageAccounts(true);
            }
            //

        }

        return p;
    }

    public void logout() {
        SessionController.setLoggedUser(null);
        SessionController.setLogged(Boolean.FALSE);
        SessionController.setActivated(false);

    }

    public WebUser getCurrent() {
        return current;
    }

    public void setCurrent(WebUser current) {
        this.current = current;
    }

    public WebUserFacade getEjbFacade() {
        return uFacade;
    }

    public void setEjbFacade(WebUserFacade ejbFacade) {
        this.uFacade = ejbFacade;
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

    public String getNewDesignation() {
        return newDesignation;
    }

    public void setNewDesignation(String newDesignation) {
        this.newDesignation = newDesignation;
    }

    public String getNewInstitution() {
        return newInstitution;
    }

    public void setNewInstitution(String newInstitution) {
        this.newInstitution = newInstitution;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getNewPasswordHint() {
        return newPasswordHint;
    }

    public void setNewPasswordHint(String newPasswordHint) {
        this.newPasswordHint = newPasswordHint;
    }

    public String getNewPersonName() {
        return newPersonName;
    }

    public void setNewPersonName(String newPersonName) {
        this.newPersonName = newPersonName;
    }

    public PersonFacade getpFacade() {
        return pFacade;
    }

    public void setpFacade(PersonFacade pFacade) {
        this.pFacade = pFacade;
    }

    public WebUserFacade getuFacade() {
        return uFacade;
    }

    public void setuFacade(WebUserFacade uFacade) {
        this.uFacade = uFacade;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public boolean isActivated() {
        return SessionController.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
        SessionController.setLogged(activated);
    }

    public boolean isLogged() {
        return SessionController.logged;
    }

    public void setLogged(boolean logged) {
        SessionController.setLogged(logged);
        this.logged = logged;
    }

    public WebUserRoleFacade getrFacade() {
        return rFacade;
    }

    public void setrFacade(WebUserRoleFacade rFacade) {
        this.rFacade = rFacade;
    }

    public PrivilegeFacade getvFacade() {
        return vFacade;
    }

    public void setvFacade(PrivilegeFacade vFacade) {
        this.vFacade = vFacade;
    }

    public Privilege getPrivilege() {
        return SessionController.getPrivilege();
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
        SessionController.setPrivilege(privilege);
    }
}
