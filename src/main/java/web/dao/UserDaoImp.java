package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final EntityManagerFactory entityManagerFactory;

    public UserDaoImp(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public User getUser(String userName) throws SQLException {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        User user = null;
        List<User> userList = new ArrayList<>();
        try {
            transaction.begin();
            String str = "SELECT u FROM User u WHERE u.name = :userName";
            Query query = entityManager.createQuery(str);
            query.setParameter("userName", userName);
            userList = query.getResultList();
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            return user;
        }
    }


    @Override
    public List<?> getAllUsers() throws SQLException {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        User user = null;
        List<User> userList = new ArrayList<>();
        try {
            transaction.begin();
            String str = "SELECT u FROM User u";
            Query query = entityManager.createQuery(str);
            userList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            return userList;
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query query = entityManager.createQuery("DELETE FROM User u WHERE u.name = :userName");
            query.setParameter("userName", user.getName());
            query.executeUpdate();
//            entityManager.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(User user) throws SQLException {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
