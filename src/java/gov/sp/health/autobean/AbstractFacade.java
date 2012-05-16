/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.autobean;

import java.util.List;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author IT
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll(boolean withoutRetired) {
        return findAll(null, null, withoutRetired);
    }

    public List<T> findAll() {
        return findAll(null, null, false);
    }

    public List<T> findAll(String fieldName) {
        return findAll(fieldName, "", false);
    }

    public List<T> findAll(String fieldName, boolean withoutRetired) {
        return findAll(fieldName, "", withoutRetired);
    }

    public List<T> findAll(String fieldName, String fieldValue) {
        return findAll(fieldName, fieldValue, false);
    }

    private void test(Class myClass, Object ob) {
    }

    public List<T> findAllByJPQL(String jPQL) {
        TypedQuery<T> q = getEntityManager().createQuery("SELECT c FROM Country c", entityClass);
        return q.getResultList();
    }

    public List<T> findAll(String fieldName, String fieldValue, boolean withoutRetired) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(entityClass);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        ParameterExpression<String> p = cb.parameter(String.class);
        Predicate predicateField = cb.like(rt.<String>get(fieldName), fieldValue);
        Predicate predicateRetired = cb.equal(rt.<Boolean>get("retired"), false);
        Predicate predicateFieldRetired = cb.and(predicateField, predicateRetired);

        if (withoutRetired && !fieldValue.equals("")) {
            cq.where(predicateFieldRetired);
        } else if (withoutRetired) {
            cq.where(predicateRetired);
        } else if (!fieldValue.equals("")) {
            cq.where(predicateField);
        }

        if (!fieldName.equals("")) {
            cq.orderBy(cb.asc(rt.get(fieldName)));
        }

        return getEntityManager().createQuery(cq).getResultList();
    }

    public <U> List<T> testMethod(U[] a, Collection<U> all) {
        List<T> myList = new ArrayList<T>();
        return myList;
    }

    public <U> List<T> findAll(String fieldName, int searchID, boolean withoutRetired) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(entityClass);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);

        if (withoutRetired) {
            cq.where(cb.and(cb.equal(rt.get("retired"), false)),
                    (cb.equal(rt.get(fieldName).get("id"), searchID)));
        } else {
            cq.where(cb.equal(rt.get("retired"), false));
        }

        return getEntityManager().createQuery(cq).getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
}
