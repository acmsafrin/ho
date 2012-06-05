/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.bean;

import gov.sp.health.autobean.*;
import gov.sp.health.entity.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Buddhika
 */
@ManagedBean
@RequestScoped
public class DemographyExcelManager {

    /**
     *
     * EJBs
     *
     */
    @EJB
    DPDHSAreaFacade dPDHSFacade;
    @EJB
    MOHAreaFacade mohFacade;
    @EJB
    PHIAreaFacade phiFacade;
    @EJB
    PHMAreaFacade phmFacade;
    @EJB
    GNAreaFacade gnFacade;
    @EJB
    PopulationFacade poFacade;
    /**
     *
     * Values of Excel Columns
     *
     */
    int gnCol;
    int phmCol;
    int phiCol;
    int mohCol;
    int gnAreaCol;
    int gnPopCol;
    int startRow;
    /**
     * Selected
     *
     */
    DPDHSArea selectedArea;
    MOHArea selectedMOH;
    PHIArea selectedPHI;
    long selectedMOHId;
    long selectedPHIId;
    /**
     *
     * Areas
     *
     */
    DataModel<DPDHSArea> dmDPDHS;
    DataModel<MOHArea> dmMOH;
    DataModel<PHIArea> dmPHI;
    /**
     *
     * Uploading File
     *
     */
    private UploadedFile file;
    /**
     *
     * Tables Columns
     *
     *
     */
    List<DemoTblRow> lstAllColRows;

    /**
     * Creates a new instance of DemographyExcelManager
     */
    public DemographyExcelManager() {
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String importToExcel() {
        GNArea gn;
        PHMArea phm;
        PHIArea phi;
        MOHArea moh;
        PHMArea phmTem = null;
        PHIArea phiTem = null;
        MOHArea mohTem = null;
        Population pop;
        String temStr;
        double temArea;
        long temPop;

        File inputWorkbook;
        Workbook w;
        Cell cell;
        InputStream in;
        JsfUtil.addSuccessMessage(file.getFileName());
        try {
            JsfUtil.addSuccessMessage(file.getFileName());
            in = file.getInputstream();
            File f = new File("D:\\Tem", Calendar.getInstance().getTimeInMillis() + file.getFileName());
            FileOutputStream out = new FileOutputStream(f);
            //            OutputStream out = new FileOutputStream(new File(fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            inputWorkbook = new File(f.getAbsolutePath());
            JsfUtil.addSuccessMessage("Excel File Opened");
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
            for (int i = startRow; i < sheet.getRows(); i++) {
                cell = sheet.getCell(mohCol, i);
                temStr = cell.getContents();
                if (!temStr.equals("")) {
                    mohTem = mohFacade.findByField("name", temStr, true);
                    if (mohTem == null) {
                        moh = new MOHArea();
                        moh.setName(temStr);
                        mohFacade.create(moh);
                        mohTem = moh;
                    } else {
                        moh = mohTem;
                    }
                } else {
                    moh = mohTem;
                }


                cell = sheet.getCell(phiCol, i);
                temStr = cell.getContents();

                if (!temStr.equals("")) {
                    phiTem = phiFacade.findByField("name", temStr, true);
                    if (phiTem == null) {
                        phi = new PHIArea();
                        phi.setName(temStr);
                        phiFacade.create(phi);
                        phiTem = phi;
                    } else {
                        phi = phiTem;
                    }
                } else {
                    phi = phiTem;
                }

                cell = sheet.getCell(phmCol, i);
                temStr = cell.getContents();

                if (!temStr.equals("")) {
                    phmTem = phmFacade.findByField("name", temStr, true);
                    if (phmTem == null) {
                        phm = new PHMArea();
                        phm.setName(temStr);
                        phmFacade.create(phm);
                        phmTem = phm;
                    } else {
                        phm = phmTem;
                    }
                } else {
                    phm = phmTem;
                }

                cell = sheet.getCell(gnPopCol, i);
                if (cell.getType() == CellType.NUMBER) {
                    temStr = cell.getContents();
                    try {
                        temPop = Long.valueOf(temStr);
                    } catch (Exception e) {
                        temPop = 0;
                    }
                } else {
                    temPop = 0;
                }

                cell = sheet.getCell(gnAreaCol, i);
                if (cell.getType() == CellType.NUMBER) {
                    temStr = cell.getContents();
                    try {
                        temArea = Double.valueOf(temStr);
                    } catch (Exception e) {
                        temArea = 0;
                    }
                } else {
                    temArea = 0;
                }



                cell = sheet.getCell(gnCol, i);
                temStr = cell.getContents();

                if (!temStr.equals("")) {
                    gn = gnFacade.findByField("name", temStr, true);
                    if (gn == null) {
                        gn = new GNArea();
                        gn.setName(temStr);
                        gnFacade.create(gn);

                    } else {
                    }
                    gn.setpHMArea(phm);
                    gnFacade.edit(gn);

                    temStr = "SELECT i FROM Population i WHERE i.retired = false AND i.gNArea.id = " + gn.getId();
                    List<Population> lstPop = poFacade.findBySQL(temStr);
                    if (lstPop.isEmpty()) {
                        pop = new Population();
                    } else {
                        pop = lstPop.get(0);
                    }
                    pop.setArea(temArea);
                    pop.setPopulationNumber(temPop);
                    pop.setgNArea(gn);
                    poFacade.create(pop);

                    if (phm != null) {
                        phm.setpHIArea(phi);
                        phmFacade.edit(phm);
                    }

                    if (phi != null) {
                        phi.setmOHArea(moh);
                        phiFacade.edit(phi);
                    }

                    if (moh != null) {
                        moh.setdPDHSArea(selectedArea);
                        mohFacade.edit(moh);
                    }

                } else {
                    gn = null;
                }
            }
            FacesMessage msg = new FacesMessage("Succesful", "All the data in Excel File Impoted to the database");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "display_areas";
        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
            return "";
        } catch (BiffException e) {
            JsfUtil.addErrorMessage(e.getMessage());
            return "";
        }
    }

    public String testingURL() {
        return "faces/display_areas.xhtml";
    }

    public DPDHSAreaFacade getdPDHSFacade() {
        return dPDHSFacade;
    }

    public void setdPDHSFacade(DPDHSAreaFacade dPDHSFacade) {
        this.dPDHSFacade = dPDHSFacade;
    }

    public DataModel<DPDHSArea> getDmDPDHS() {
        return new ListDataModel(dPDHSFacade.findBySQL("Select d From DPDHSArea d"));
    }

    public void setDmDPDHS(DataModel<DPDHSArea> dmDPDHS) {
        this.dmDPDHS = dmDPDHS;
    }

    public int getGnAreaCol() {
        return gnAreaCol;
    }

    public void setGnAreaCol(int gnAreaCol) {
        this.gnAreaCol = gnAreaCol;
    }

    public int getGnCol() {
        return gnCol;
    }

    public void setGnCol(int gnCol) {
        this.gnCol = gnCol;
    }

    public GNAreaFacade getGnFacade() {
        return gnFacade;
    }

    public void setGnFacade(GNAreaFacade gnFacade) {
        this.gnFacade = gnFacade;
    }

    public int getGnPopCol() {
        return gnPopCol;
    }

    public void setGnPopCol(int gnPopCol) {
        this.gnPopCol = gnPopCol;
    }

    public int getMohCol() {
        return mohCol;
    }

    public void setMohCol(int mohCol) {
        this.mohCol = mohCol;
    }

    public MOHAreaFacade getMohFacade() {
        return mohFacade;
    }

    public void setMohFacade(MOHAreaFacade mohFacade) {
        this.mohFacade = mohFacade;
    }

    public int getPhiCol() {
        return phiCol;
    }

    public void setPhiCol(int phiCol) {
        this.phiCol = phiCol;
    }

    public PHIAreaFacade getPhiFacade() {
        return phiFacade;
    }

    public void setPhiFacade(PHIAreaFacade phiFacade) {
        this.phiFacade = phiFacade;
    }

    public int getPhmCol() {
        return phmCol;
    }

    public void setPhmCol(int phmCol) {
        this.phmCol = phmCol;
    }

    public PHMAreaFacade getPhmFacade() {
        return phmFacade;
    }

    public void setPhmFacade(PHMAreaFacade phmFacade) {
        this.phmFacade = phmFacade;
    }

    public PopulationFacade getPoFacade() {
        return poFacade;
    }

    public void setPoFacade(PopulationFacade poFacade) {
        this.poFacade = poFacade;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public DPDHSArea getSelectedArea() {
        JsfUtil.addSuccessMessage("Got Selected Area" + selectedArea);
        return selectedArea;
    }

    public void setSelectedArea(DPDHSArea selectedArea) {
        JsfUtil.addSuccessMessage("Set Selected Area" + selectedArea);
        this.selectedArea = selectedArea;
    }

    public List<DemoTblRow> getLstAllColRows() {
        return listGnPopOfDPDHS();
    }

    public void setLstAllColRows(List<DemoTblRow> lstAllColRows) {
        this.lstAllColRows = lstAllColRows;
    }

    public List<DemoTblRow> listGnPopOfDPDHS() {
        String temSQL;
        double area;
        long pop;
        Population p;
        List<DemoTblRow> lst = new ArrayList<DemoTblRow>();
        if (selectedArea == null) {
            temSQL = "SELECT i FROM MOHArea i WHERE i.retired = false ";
            return null;
        } else {
            temSQL = "SELECT i FROM MOHArea i WHERE i.retired = false AND i.dPDHSArea.id = " + selectedArea.getId();
        }
        List<MOHArea> lstMOH = mohFacade.findBySQL(temSQL);

        for (MOHArea temMOH : lstMOH) {
            temSQL = "SELECT i FROM PHIArea i WHERE i.retired = false AND i.mOHArea.id = " + temMOH.getId();
            List<PHIArea> lstPHI = phiFacade.findBySQL(temSQL);

            for (PHIArea temPHI : lstPHI) {
                temSQL = "SELECT i FROM PHMArea i WHERE i.retired = false AND i.pHIArea.id = " + temPHI.getId();
                List<PHMArea> lstPHM = phmFacade.findBySQL(temSQL);

                for (PHMArea temPHM : lstPHM) {
                    temSQL = "SELECT i FROM GNArea i WHERE i.retired = false AND i.pHMArea.id = " + temPHM.getId();
                    List<GNArea> lstGN = gnFacade.findBySQL(temSQL);

                    for (GNArea temGN : lstGN) {
                        temSQL = "SELECT i FROM Population i WHERE i.retired = false AND i.gNArea.id= " + temGN.getId();
                        List<Population> lstPop = poFacade.findBySQL(temSQL);
                        if (lstPop.isEmpty()) {
                            p = new Population();
                            p.setPopulationNumber(0);
                            p.setArea(0);
                        } else {
                            p = lstPop.get(0);
                        }
                        DemoTblRow row = new DemoTblRow();
                        row.setDpdhs(selectedArea);
                        row.setMoh(temMOH);
                        row.setPhi(temPHI);
                        row.setPhm(temPHM);
                        row.setGn(temGN);
                        row.setGnPop(p);
                        lst.add(row);
                    }

                }




            }
        }


        return lst;
    }

    public List<DemoTblRow> listGnPopOfMOH() {
        String temSQL;
        double area;
        long pop;
        Population p;
        List<DemoTblRow> lst = new ArrayList<DemoTblRow>();
        if (selectedArea == null) {
            temSQL = "SELECT i FROM MOHArea i WHERE i.retired = false ";
        } else {
            temSQL = "SELECT i FROM MOHArea i WHERE i.retired = false AND i.dPDHSArea.id = " + selectedArea.getId();
        }
        List<MOHArea> lstMOH = mohFacade.findBySQL(temSQL);

        for (MOHArea temMOH : lstMOH) {
            temSQL = "SELECT i FROM PHIArea i WHERE i.retired = false AND i.mOHArea.id = " + temMOH.getId();
            List<PHIArea> lstPHI = phiFacade.findBySQL(temSQL);

            for (PHIArea temPHI : lstPHI) {
                temSQL = "SELECT i FROM PHMArea i WHERE i.retired = false AND i.pHIArea.id = " + temPHI.getId();
                List<PHMArea> lstPHM = phmFacade.findBySQL(temSQL);

                for (PHMArea temPHM : lstPHM) {
                    temSQL = "SELECT i FROM GNArea i WHERE i.retired = false AND i.pHMArea.id = " + temPHM.getId();
                    List<GNArea> lstGN = gnFacade.findBySQL(temSQL);

                    for (GNArea temGN : lstGN) {
                        temSQL = "SELECT i FROM Population i WHERE i.retired = false AND i.gNArea.id= " + temGN.getId();
                        List<Population> lstPop = poFacade.findBySQL(temSQL);
                        if (lstPop.isEmpty()) {
                            p = new Population();
                            p.setPopulationNumber(0);
                            p.setArea(0);
                        } else {
                            p = lstPop.get(0);
                        }
                        DemoTblRow row = new DemoTblRow();
                        row.setDpdhs(selectedArea);
                        row.setMoh(temMOH);
                        row.setPhi(temPHI);
                        row.setPhm(temPHM);
                        row.setGn(temGN);
                        row.setGnPop(p);
                        lst.add(row);
                    }

                }




            }
        }


        return lst;
    }

    public DataModel<MOHArea> getDmMOH() {
        String temSQL;
        if (selectedArea == null) {
            return null;
        } else {
            temSQL = "SELECT i FROM MOHArea i WHERE i.retired = false AND i.dPDHSArea.id = " + selectedArea.getId();
            return new ListDataModel<MOHArea>(mohFacade.findBySQL(temSQL));
        }
    }

    public void setDmMOH(DataModel<MOHArea> dmMOH) {
        this.dmMOH = dmMOH;
    }

    public DataModel<PHIArea> getDmPHI() {
        String temSQL;
        if (selectedMOHId == 0) {
            return null;
        } else {
            temSQL = "SELECT i FROM PHIArea i WHERE i.retired = false WHERE i.mOHArea.id = " + selectedMOHId;
            JsfUtil.addSuccessMessage(temSQL);
            return new ListDataModel<PHIArea>(phiFacade.findBySQL(temSQL));
        }
    }

    public void setDmPHI(DataModel<PHIArea> dmPHI) {
        this.dmPHI = dmPHI;
    }

    public void deselectDPDHS() {
        selectedArea = null;
    }

    public void deselectMOH() {
        selectedMOHId = 0;
        selectedMOH = null;
    }

    public void deselectPHI() {
        selectedPHIId = 0;
        selectedPHI = null;
    }

    public long getSelectedMOHId() {
        if (selectedMOH == null) {
            return 0l;
        } else {
            return selectedMOH.getId();
        }
    }

    public void setSelectedMOHId(long selectedMOHId) {
        this.selectedMOH = mohFacade.find(selectedMOHId);
        this.selectedMOHId = selectedMOHId;
    }

    public long getSelectedPHIId() {
        if (selectedPHI == null) {
            return 0l;
        } else {
            return selectedPHI.getId();
        }
    }

    public void setSelectedPHIId(long selectedPHIId) {
        this.selectedPHI = phiFacade.find(selectedPHIId);
        this.selectedPHIId = selectedPHIId;
    }

    public PHIArea getSelectedPHI() {
        return selectedPHI;
    }

    public void setSelectedPHI(PHIArea selectedPHI) {
        if (selectedPHI == null) {
            selectedPHIId = 0;
        } else {
            selectedPHIId = selectedPHI.getId();
        }
        this.selectedPHI = selectedPHI;
    }

    public MOHArea getSelectedMOH() {
        return selectedMOH;
    }

    public void setSelectedMOH(MOHArea selectedMOH) {
        if (selectedMOH == null) {
            selectedMOHId = 0;
        } else {
            selectedMOHId = selectedMOH.getId();
        }
        this.selectedMOH = selectedMOH;
    }
}
