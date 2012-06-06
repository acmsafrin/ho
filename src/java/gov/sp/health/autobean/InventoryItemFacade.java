/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.autobean;

import gov.sp.health.bean.AbstractFacade;
import gov.sp.health.entity.InventoryItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IT
 */
@Stateless
public class InventoryItemFacade extends AbstractFacade<InventoryItem> {
    @PersistenceContext(unitName = "HOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventoryItemFacade() {
        super(InventoryItem.class);
    }
    
}
