/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@Entity
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    boolean demographyAdd;
    boolean demographyEdit;
    boolean demographyDelete;
    boolean demographyView;
    boolean inventaryAdd;
    boolean inventaryEdit;
    boolean inventaryDelete;
    boolean inventaryView;
    boolean MSAdd;
    boolean MSEdit;
    boolean MSDelete;
    boolean MSView;
    boolean caderAdd;
    boolean caderEdit;
    boolean caderDelete;
    boolean caderView;
    boolean libraryAdd;
    boolean libraryEdit;
    boolean libraryDelete;
    boolean libraryView;
    boolean vehicleAdd;
    boolean vehicleEdit;
    boolean vehicleDelete;
    boolean vehicleView;
    boolean activateAccounts;
    boolean dectivateAccounts;
    boolean deleteAccounts;
    boolean manageAccounts;
    
    @ManyToOne
    Province restrictedProvince;
    
    @ManyToOne
    DPDHSArea restrictedDPDHSArea;
    
    @ManyToOne
    MOHArea restrictedMOHArea;
    
    @ManyToOne
    PHIArea restrictedPHIArea;
    
    @ManyToOne
    PHMArea restrictedPHMArea;
    
    //Main Properties
    @Column(unique = true, nullable = false)
    String name;
    String description;
    //Created Properties
    @ManyToOne
    WebUser creater;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date createdAt;
    //Retairing properties
    boolean retired;
    @ManyToOne
    WebUser retirer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    String retireComments;
    //Activation properties
    boolean activated;
    @ManyToOne
    WebUser activator;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date activatedAt;
    String activateComments;

    
    @OneToOne
    WebUser webUser;
    
    @OneToOne
    WebUserRole webUserRole;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isMSAdd() {
        return MSAdd;
    }

    public void setMSAdd(boolean MSAdd) {
        this.MSAdd = MSAdd;
    }

    public boolean isMSDelete() {
        return MSDelete;
    }

    public void setMSDelete(boolean MSDelete) {
        this.MSDelete = MSDelete;
    }

    public boolean isMSEdit() {
        return MSEdit;
    }

    public void setMSEdit(boolean MSEdit) {
        this.MSEdit = MSEdit;
    }

    public boolean isMSView() {
        return MSView;
    }

    public void setMSView(boolean MSView) {
        this.MSView = MSView;
    }

    public boolean isActivateAccounts() {
        return activateAccounts;
    }

    public void setActivateAccounts(boolean activateAccounts) {
        this.activateAccounts = activateAccounts;
    }

    public boolean isCaderAdd() {
        return caderAdd;
    }

    public void setCaderAdd(boolean caderAdd) {
        this.caderAdd = caderAdd;
    }

    public boolean isCaderDelete() {
        return caderDelete;
    }

    public void setCaderDelete(boolean caderDelete) {
        this.caderDelete = caderDelete;
    }

    public boolean isCaderEdit() {
        return caderEdit;
    }

    public void setCaderEdit(boolean caderEdit) {
        this.caderEdit = caderEdit;
    }

    public boolean isCaderView() {
        return caderView;
    }

    public void setCaderView(boolean caderView) {
        this.caderView = caderView;
    }

    public boolean isDectivateAccounts() {
        return dectivateAccounts;
    }

    public void setDectivateAccounts(boolean dectivateAccounts) {
        this.dectivateAccounts = dectivateAccounts;
    }

    public boolean isDeleteAccounts() {
        return deleteAccounts;
    }

    public void setDeleteAccounts(boolean deleteAccounts) {
        this.deleteAccounts = deleteAccounts;
    }

    public boolean isDemographyAdd() {
        return demographyAdd;
    }

    public void setDemographyAdd(boolean demographyAdd) {
        this.demographyAdd = demographyAdd;
    }

    public boolean isDemographyDelete() {
        return demographyDelete;
    }

    public void setDemographyDelete(boolean demographyDelete) {
        this.demographyDelete = demographyDelete;
    }

    public boolean isDemographyEdit() {
        return demographyEdit;
    }

    public void setDemographyEdit(boolean demographyEdit) {
        this.demographyEdit = demographyEdit;
    }

    public boolean isDemographyView() {
        return demographyView;
    }

    public void setDemographyView(boolean demographyView) {
        this.demographyView = demographyView;
    }

    public boolean isInventaryAdd() {
        return inventaryAdd;
    }

    public void setInventaryAdd(boolean inventaryAdd) {
        this.inventaryAdd = inventaryAdd;
    }

    public boolean isInventaryDelete() {
        return inventaryDelete;
    }

    public void setInventaryDelete(boolean inventaryDelete) {
        this.inventaryDelete = inventaryDelete;
    }

    public boolean isInventaryEdit() {
        return inventaryEdit;
    }

    public void setInventaryEdit(boolean inventaryEdit) {
        this.inventaryEdit = inventaryEdit;
    }

    public boolean isInventaryView() {
        return inventaryView;
    }

    public void setInventaryView(boolean inventaryView) {
        this.inventaryView = inventaryView;
    }

    public boolean isLibraryAdd() {
        return libraryAdd;
    }

    public void setLibraryAdd(boolean libraryAdd) {
        this.libraryAdd = libraryAdd;
    }

    public boolean isLibraryDelete() {
        return libraryDelete;
    }

    public void setLibraryDelete(boolean libraryDelete) {
        this.libraryDelete = libraryDelete;
    }

    public boolean isLibraryEdit() {
        return libraryEdit;
    }

    public void setLibraryEdit(boolean libraryEdit) {
        this.libraryEdit = libraryEdit;
    }

    public boolean isLibraryView() {
        return libraryView;
    }

    public void setLibraryView(boolean libraryView) {
        this.libraryView = libraryView;
    }

    public boolean isManageAccounts() {
        return manageAccounts;
    }

    public void setManageAccounts(boolean manageAccounts) {
        this.manageAccounts = manageAccounts;
    }

    public DPDHSArea getRestrictedDPDHSArea() {
        return restrictedDPDHSArea;
    }

    public void setRestrictedDPDHSArea(DPDHSArea restrictedDPDHSArea) {
        this.restrictedDPDHSArea = restrictedDPDHSArea;
    }

    public MOHArea getRestrictedMOHArea() {
        return restrictedMOHArea;
    }

    public void setRestrictedMOHArea(MOHArea restrictedMOHArea) {
        this.restrictedMOHArea = restrictedMOHArea;
    }

    public PHIArea getRestrictedPHIArea() {
        return restrictedPHIArea;
    }

    public void setRestrictedPHIArea(PHIArea restrictedPHIArea) {
        this.restrictedPHIArea = restrictedPHIArea;
    }

    public PHMArea getRestrictedPHMArea() {
        return restrictedPHMArea;
    }

    public void setRestrictedPHMArea(PHMArea restrictedPHMArea) {
        this.restrictedPHMArea = restrictedPHMArea;
    }

    public Province getRestrictedProvince() {
        return restrictedProvince;
    }

    public void setRestrictedProvince(Province restrictedProvince) {
        this.restrictedProvince = restrictedProvince;
    }

    public boolean isVehicleAdd() {
        return vehicleAdd;
    }

    public void setVehicleAdd(boolean vehicleAdd) {
        this.vehicleAdd = vehicleAdd;
    }

    public boolean isVehicleDelete() {
        return vehicleDelete;
    }

    public void setVehicleDelete(boolean vehicleDelete) {
        this.vehicleDelete = vehicleDelete;
    }

    public boolean isVehicleEdit() {
        return vehicleEdit;
    }

    public void setVehicleEdit(boolean vehicleEdit) {
        this.vehicleEdit = vehicleEdit;
    }

    public boolean isVehicleView() {
        return vehicleView;
    }

    public void setVehicleView(boolean vehicleView) {
        this.vehicleView = vehicleView;
    }

    public String getActivateComments() {
        return activateComments;
    }

    public void setActivateComments(String activateComments) {
        this.activateComments = activateComments;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Date getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Date activatedAt) {
        this.activatedAt = activatedAt;
    }

    public WebUser getActivator() {
        return activator;
    }

    public void setActivator(WebUser activator) {
        this.activator = activator;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public WebUser getCreater() {
        return creater;
    }

    public void setCreater(WebUser creater) {
        this.creater = creater;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRetireComments() {
        return retireComments;
    }

    public void setRetireComments(String retireComments) {
        this.retireComments = retireComments;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public WebUser getRetirer() {
        return retirer;
    }

    public void setRetirer(WebUser retirer) {
        this.retirer = retirer;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public WebUserRole getWebUserRole() {
        return webUserRole;
    }

    public void setWebUserRole(WebUserRole webUserRole) {
        this.webUserRole = webUserRole;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.sp.health.entity.Privilage[ id=" + id + " ]";
    }
}
