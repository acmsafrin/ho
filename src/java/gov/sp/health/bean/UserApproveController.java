/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.bean;

import gov.sp.health.autobean.PrivilegeFacade;
import gov.sp.health.autobean.WebUserFacade;
import gov.sp.health.entity.Privilege;
import gov.sp.health.entity.WebUser;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Buddhika
 */
@ManagedBean
@RequestScoped
public class UserApproveController {
    DataModel<WebUser> toApproveUsers;
    WebUser selectedUser;
    //
    @EJB
    WebUserFacade userFacade;
    @EJB
    PrivilegeFacade priFacade;
    //
    String activateComments;
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    Privilege privilege;
    /**
     * Creates a new instance of UserApproveController
     */
    public UserApproveController() {
        
        
    }

    public String getActivateComments() {
        return activateComments;
    }

    public void setActivateComments(String activateComments) {
        this.activateComments = activateComments;
    }

    public Privilege getPrivilege() {
        if (privilege ==null) privilege = new Privilege();
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    
    
    
    public WebUser getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(WebUser selectedUser) {
        this.selectedUser = selectedUser;
    }

    public DataModel<WebUser> getToApproveUsers() {
        String temSQL;
        temSQL = "SELECT u FROM WebUser u WHERE u.retired=false AND u.activated=false";
        List<WebUser> lst ;
        lst=getUserFacade().findBySQL(temSQL);
        return new ListDataModel<WebUser>(lst);
    }

    public void setToApproveUsers(DataModel<WebUser> toApproveUsers) {
        this.toApproveUsers = toApproveUsers;
    }

    public WebUserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(WebUserFacade userFacade) {
        this.userFacade = userFacade;
    }
    
    
    public void approveUser(){
        if (selectedUser==null){
            JsfUtil.addErrorMessage("Please select a user to approve");
            return;
        }
        selectedUser.setActivated(true);
        selectedUser.setActivatedAt(Calendar.getInstance().getTime());
        selectedUser.setActivator(sessionController.loggedUser);
        selectedUser.setActivateComments(activateComments);
        userFacade.edit(selectedUser);
        
        privilege.setWebUser(selectedUser);
        priFacade.create(privilege);
        
        selectedUser=null;
        privilege = null;
        
        JsfUtil.addSuccessMessage("Successfully activated");
    }

    public PrivilegeFacade getPriFacade() {
        return priFacade;
    }

    public void setPriFacade(PrivilegeFacade priFacade) {
        this.priFacade = priFacade;
    }
    
    
}
