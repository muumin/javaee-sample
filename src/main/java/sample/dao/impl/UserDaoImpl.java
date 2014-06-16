package sample.dao.impl;

import sample.dao.UserDao;
import sample.domain.user.User;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserDaoImpl implements UserDao {

    @Inject
    transient Logger logger;

    @Inject
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setEmail(user.getEmail().toLowerCase());

        long count = em.createNamedQuery("User.countEmail", Long.class)
                .setParameter("email", user.getEmail())
                .getSingleResult();
        logger.log(Level.INFO, "user:" + count);
        if (count != 0) {
            return null;
        }

        user.setCrateDate(new Date());
        em.persist(user);

        return user;
    }
}
