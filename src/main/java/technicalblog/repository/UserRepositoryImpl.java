package technicalblog.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import technicalblog.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @PersistenceUnit(unitName = "techblog")
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void registerUser(User user) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    entityManager.persist(user);
    entityTransaction.commit();
  }

  public User verifyUser(User user) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<User> query = entityManager.createQuery(
        "SELECT u FROM User u WHERE u.username = :username", User.class);
    try {
      return query.setParameter("username", user.getUsername()).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
