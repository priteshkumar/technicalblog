package technicalblog.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;

@Service
public class PostService {

  private LinkedList<Post> posts = new LinkedList<Post>();

  public PostService() {
    System.out
        .println("#####instantiating###### " + this.getClass().getName());
  }

  public List<Post> getAllPosts() {
    Post post1 = new Post();
    post1.setTitle("Post 1");
    post1.setContent("This is post1");
    post1.setDate(new Date());
    posts.add(post1);
    Post post2 = new Post();
    post2.setTitle("Post 2");
    post2.setContent("This is post2");
    post2.setDate(new Date());
    posts.add(post2);
    Post post3 = new Post();
    post3.setTitle("Post 3");
    post3.setContent("This is post3");
    post3.setDate(new Date());
    posts.add(post3);
    return this.posts;
  }

  public List<Post> getUserPosts(){
    this.posts.clear();
    Post post1 = new Post();
    post1.setTitle("User Post 1");
    post1.setContent("This is your post1");
    post1.setDate(new Date());
    posts.add(post1);
    return this.posts;
  }

}
