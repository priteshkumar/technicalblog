package technicalblog.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.model.User;

@Service
public class PostService {

  private HashMap<String, List<Post>> userPosts =
      new HashMap<String, List<Post>>();

  //private Connection connection;

  @PersistenceUnit(unitName = "techblog")
  private EntityManagerFactory entityManagerFactory;

  public PostService() {
    System.out.println("#####instantiating###### " + this.getClass().getName());
  }

  @PostConstruct
  private void initDb() {
  /*  try {
      Class.forName("org.postgresql.Driver");

      this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432"
          + "/technicalblog", "mavixk", "kasper");
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }*/
  }

  public void addUserKey(User user) {
    this.userPosts.put(user.getUsername(), new LinkedList<Post>());
  }


  public List<Post> getAllPosts() throws SQLException {
    EntityManager em = entityManagerFactory.createEntityManager();
    TypedQuery<Post> query = em.createQuery("SELECT p from Post p",Post.class);
    List<Post> allPosts = query.getResultList();

    /*Statement statement = null;
    try {
      statement = connection.createStatement();
      ResultSet posts = statement.executeQuery("SELECT * from posts");
      while (posts.next()) {
        Post post = new Post();
        post.setTitle(posts.getString("title"));
        post.setContent(posts.getString("content"));
        allPosts.add(post);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally { //close statement release resources
      if (statement != null) {
        statement.close();
      }
    }*/
    return allPosts;
  }

  public List<Post> getUserPosts(String username) {
    return this.userPosts.get(username);
  }

  public void savePost(Post post) {
    Post userPost = new Post();
    userPost.setTitle(post.getTitle());
    userPost.setContent(post.getContent());
    userPost.setDate(LocalDate.now());
    userPost.setAuthor("mavixk");
    userPosts.get(userPost.getAuthor()).add(userPost);
  }
}
