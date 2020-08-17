package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Repository
@Transactional
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final EntityManagerFactory entityManagerFactory;

    public RoleDaoImp(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
//        проверить по сравнению ссылок, как инжектится entityManager
//        entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public Role getOne(Long id) throws SQLException {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction trans = entityManager.getTransaction();
        Role role = null;
        try {
            trans.begin();
            role = entityManager.find(Role.class, id);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            return role;
        }
    }
}
