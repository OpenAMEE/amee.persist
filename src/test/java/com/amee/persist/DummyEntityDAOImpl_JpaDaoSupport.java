package com.amee.persist;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public class DummyEntityDAOImpl_JpaDaoSupport extends JpaDaoSupport implements DummyEntityDAO {

    public List<DummyEntity> getDummyEntities() {
        return getJpaTemplate().find("from DummyEntity");
    }

    public DummyEntity getDummyEntityByUid(String uid) {
        try {
            return getJpaTemplate()
                    .getEntityManagerFactory()
                    .createEntityManager()
                    .createQuery(
                            "FROM DummyEntity " +
                                    "WHERE uid = :uid", DummyEntity.class)
                    .setParameter("uid", uid)
                    .getSingleResult();
        } catch (NonUniqueResultException e) {
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void persist(DummyEntity dummyEntity) {
        getJpaTemplate().persist(dummyEntity);
    }

    public void remove(DummyEntity dummyEntity) {
        getJpaTemplate().remove(dummyEntity);
    }
}