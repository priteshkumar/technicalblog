package technicalblog.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.repository.PostRepository;

@Service
public class PostService {

  private PostRepository postRepository;

  @Autowired
  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
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

  public List<Post> getAllPosts() {
    return postRepository.getAllPosts();
  }

  public List<Post> getUserPosts(String username) {
    return postRepository.getUserPost();
  }

  public Post getSinglePost(Integer postId){
    return postRepository.getSinglePost(postId);
  }

  public void savePost(Post post) {
    post.setLocalDate(LocalDate.now());
    Post createdPost = postRepository.savePost(post);
    System.out.println(createdPost);
  }

  public void updatePost(Integer postId,Post post){
    post.setId(postId);
    post.setLocalDate(LocalDate.now());
    postRepository.updatePost(post);
  }

  public void deletePost(Integer postId){
    postRepository.deletePost(postId);
  }
}
