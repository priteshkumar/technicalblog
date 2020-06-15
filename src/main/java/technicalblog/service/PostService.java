package technicalblog.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;

@Service
public class PostService {
  private LinkedList<Post> posts = new LinkedList<Post>();

  public List<Post> getAllPosts(){
    Post post1 = new Post();
    return this.posts;
  }

}
