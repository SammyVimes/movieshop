package ru.danilov.movieshop.core.entity.user;

import ru.danilov.movieshop.core.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Semyon on 07.09.2014.
 */
public class UserSettingsDAO {

    @PersistenceContext
    private EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();


    public void persist(final UserSettings userSettings) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(userSettings);
        transaction.commit();
    }

    public UserSettings update(final UserSettings userSetting) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        UserSettings _userSetting = entityManager.merge(userSetting);
        transaction.commit();
        return _userSetting;
    }

    public UserSettings getUserSettings(final User user) {
        List<UserSettings> list = entityManager.createQuery("SELECT a FROM UserSettings a WHERE user = :user", UserSettings.class).setParameter("user", user).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

}
