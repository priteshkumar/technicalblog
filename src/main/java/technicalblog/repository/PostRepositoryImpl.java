package technicalblog.repository;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

@Repository
public class PostRepositoryImpl implements PostRepository {

  @PersistenceUnit(unitName = "techblog")
  private EntityManagerFactory entityManagerFactory;

  @Override
  public List<Post> getAllPosts() {
    EntityManager em = entityManagerFactory.createEntityManager();
    TypedQuery<Post> query = em.createQuery("SELECT p from Post p", Post.class);
    List<Post> allPosts = query.getResultList();
    /*for (Post p : allPosts) {
      System.out.println(p.getDate());
    }*/
    return allPosts;
  }

  @Override
  public List<Post> getUserPost() {
    EntityManager em = entityManagerFactory.createEntityManager();
   // Post post = em.find(Post.class, 1); // demo em.find() method

    //use query parameters for security
    /*TypedQuery<Post> query = em.createQuery(
        "SELECT p FROM Post p WHERE p.author = :author", Post.class);
    String author = "mavixk";
    return query.setParameter("author", author).getResultList();*/
    TypedQuery<Post> query = em.createQuery("SELECT p from Post p", Post.class);
    List<Post> allPosts = query.getResultList();
    return allPosts;

  }

  @Override
  public Post getSinglePost(Integer postId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    Post post = em.find(Post.class, postId); // demo em.find() method
    return post;
  }

  public Post savePost(Post post) {
    Post userPost = new Post();
    userPost.setTitle(post.getTitle());
    userPost.setContent(post.getContent());
    userPost.setLocalDate(LocalDate.now());
    userPost.setUser(post.getUser());
    //begin transaction to update db
    //use transaction as a rule of thumb
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = em.getTransaction();
    entityTransaction.begin();
    em.persist(userPost);
    entityTransaction.commit();
    return userPost;
  }

  public Post updatePost(Post post) {
    //begin transaction to update db
    //use transaction as a rule of thumb
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = em.getTransaction();
    entityTransaction.begin();
    em.merge(post);
    entityTransaction.commit();
    return post;
  }

  public void deletePost(Integer postId) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    Post post = entityManager.find(Post.class, postId);
    entityManager.remove(post);
    entityTransaction.commit();
  }
}
