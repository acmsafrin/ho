/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.component.menuitem.MenuItem;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@SessionScoped
public class Menu {

    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    MenuModel model;
    String temIx = "";

    private String getLabel(String key) {
        return new MessageProvider().getValue(key);
    }

    public Menu() {
//        createMenu();
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public void createMenu() {
        model = new DefaultMenuModel();
//        model.addSubmenu(cadreSubmenu());
        if (sessionController.privilege.isBmeView()) model.addSubmenu(biomedSubmenu());
        if (sessionController.privilege.isVehicleView()) model.addSubmenu(transportSubmenu());
        if (sessionController.privilege.isDemographyView()) model.addSubmenu(demographySubmenu());
        if (sessionController.privilege.isCaderView()) model.addSubmenu(humanSubmenu());
        if (sessionController.privilege.isMsView()) model.addSubmenu(medicalSubmenu());
        if (sessionController.privilege.isInventoryView()) model.addSubmenu(inventorySubmenu());
        if (sessionController.privilege.isManageAccounts()) model.addSubmenu(adminSubmenu());

    }

    private Submenu cadreSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("HR");

        item = new MenuItem();
        item.setValue("Institution Types");
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institutions");
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations Category");
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institution Designations");
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Cadre Positions");
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Reports");
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu biomedSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("Biomedical");

        item = new MenuItem();
        item.setValue("Institution Types");
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institutions");
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations Category");
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institution Designations");
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Cadre Positions");
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Reports");
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu transportSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("Transport");

        item = new MenuItem();
        item.setValue("Institution Types");
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institutions");
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations Category");
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institution Designations");
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Cadre Positions");
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Reports");
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu demographySubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("Demography");

        
        item = new MenuItem();
        item.setValue("Edit");
        item.setUrl("demography_edit.xhtml");
        submenu.getChildren().add(item);

        
        
        item = new MenuItem();
        item.setValue("Import");
        item.setUrl("demography_import_excel.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Display");
        item.setUrl("demography_display_areas.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institution Designations");
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Cadre Positions");
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Reports");
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu humanSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("HR");

        item = new MenuItem();
        item.setValue("Institution Types");
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institutions");
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations Category");
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institution Designations");
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Cadre Positions");
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Reports");
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu medicalSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("MSD");

        item = new MenuItem();
        item.setValue("Institution Types");
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institutions");
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations Category");
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Designations");
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Institution Designations");
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Cadre Positions");
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Reports");
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu inventorySubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("Inventory");

        item = new MenuItem();
        item.setValue("Edit Data");
        item.setUrl("inventory_edit.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Good Receive");
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Purchase");
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);
        
item = new MenuItem();
        item.setValue("Distribution");
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        
        item = new MenuItem();
        item.setValue("Requests");
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Estimates");
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);


        return submenu;
    }

    private Submenu adminSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("Admin");

        item = new MenuItem();
        item.setValue("Activate Accounts");
        item.setUrl("activate_users.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    //    private Submenu editSubmenu() {
//        Submenu submenu;
//        Submenu childSubmenu;
//        MenuItem item;
//
//        submenu = new Submenu();
//        submenu.setLabel(getLabel("editMenu"));
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("investigationMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("investigationCategoriesMenu"));
//        item.setUrl("/faces/ix_category.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("institutionsMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("institutionMenu"));
//        item.setUrl("/faces/institution.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("financeMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("expenseMenu"));
//        item.setUrl("/faces/expense.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//
//        childSubmenu = new Submenu();
//        childSubmenu.setLabel(getLabel("metadataMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("contactTypeMenu"));
//        item.setUrl("/faces/contact_type.xhtml");
//        childSubmenu.getChildren().add(item);
//
//        submenu.getChildren().add(childSubmenu);
//
//        return submenu;
//    }
//
//    private Submenu fileSubmenu() {
//        Submenu submenu;
//        Submenu childSubmenu;
//        MenuItem item;
//
//        submenu = new Submenu();
//        submenu.setLabel(getLabel("fileMenu"));
//
//        item = new MenuItem();
//        item.setValue(getLabel("saveMenu"));
//        item.setUrl(getLabel("#"));
//
//        submenu.getChildren().add(item);
//
//        return submenu;
//
//    }
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
