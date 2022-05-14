package dev.saadat.datenbanken.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dev.saadat.datenbanken.models.User;
import dev.saadat.datenbanken.utilities.AbstractService;


public class UserService extends AbstractService {


    public void addUser(User user) {
        Transaction tx = session.beginTransaction();
        try {
            this.session.save(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }

    }

    public void addUsers(User...users) {
        Transaction tx = session.beginTransaction();
        try {
            for (User u: users) {
                this.session.save(u);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getUserById(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);

            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            Query<User> userQuery = this.session.createQuery(criteriaQuery);
            List<User> resultList = userQuery.getResultList();
            if (resultList.size() == 1) {
                return Optional.of(resultList.get(0));
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users;
        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);

            criteriaQuery.select(root);

            Query<User> userQuery = this.session.createQuery(criteriaQuery);
            users = userQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void updateUserAddressById(Long id, Long newValue) {
        Transaction tx = session.beginTransaction();
        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);

            criteriaUpdate.set(root.get("address"), newValue).where(criteriaBuilder.equal(root.get("id"), id));

            session.createQuery(criteriaUpdate).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(Long id) {
        Transaction tx = session.beginTransaction();
        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
            Root<User> root = criteriaDelete.from(User.class);

            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));

            session.createQuery(criteriaDelete).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void removeUserIfAddressGreaterThan(Long address) {
        try {
            getAllUsers().stream().filter(u -> (u.address > address)).forEachOrdered(u -> removeUserById(u.id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /* End */
}