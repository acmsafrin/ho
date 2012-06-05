/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.bean;

import gov.sp.health.entity.DPDHSArea;
import gov.sp.health.entity.GNArea;
import gov.sp.health.entity.MOHArea;
import gov.sp.health.entity.PHIArea;
import gov.sp.health.entity.PHMArea;
import gov.sp.health.entity.Population;
import gov.sp.health.entity.Province;

/**
 *
 * @author Buddhika
 */


public class DemoTblRow {
    Province province;
    DPDHSArea dpdhs;
    MOHArea moh;
    PHIArea phi;
    PHMArea phm;
    GNArea gn;
    Population gnPop;
    Population phmPop;
    Population phiPop;
    Population mohPop;
    Population dpdhsPop;
    Population provincePop;

    public DPDHSArea getDpdhs() {
        return dpdhs;
    }

    public void setDpdhs(DPDHSArea dpdhs) {
        this.dpdhs = dpdhs;
    }

    public Population getDpdhsPop() {
        return dpdhsPop;
    }

    public void setDpdhsPop(Population dpdhsPop) {
        this.dpdhsPop = dpdhsPop;
    }

    public GNArea getGn() {
        return gn;
    }

    public void setGn(GNArea gn) {
        this.gn = gn;
    }

    public Population getGnPop() {
        return gnPop;
    }

    public void setGnPop(Population gnPop) {
        this.gnPop = gnPop;
    }

    public MOHArea getMoh() {
        return moh;
    }

    public void setMoh(MOHArea moh) {
        this.moh = moh;
    }

    public Population getMohPop() {
        return mohPop;
    }

    public void setMohPop(Population mohPop) {
        this.mohPop = mohPop;
    }

    public PHIArea getPhi() {
        return phi;
    }

    public void setPhi(PHIArea phi) {
        this.phi = phi;
    }

    public Population getPhiPop() {
        return phiPop;
    }

    public void setPhiPop(Population phiPop) {
        this.phiPop = phiPop;
    }

    public PHMArea getPhm() {
        return phm;
    }

    public void setPhm(PHMArea phm) {
        this.phm = phm;
    }

    public Population getPhmPop() {
        return phmPop;
    }

    public void setPhmPop(Population phmPop) {
        this.phmPop = phmPop;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Population getProvincePop() {
        return provincePop;
    }

    public void setProvincePop(Population provincePop) {
        this.provincePop = provincePop;
    }
    
    
    
    
}
