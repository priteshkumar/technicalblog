package technicalblog.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.model.User;

@Service
public class PostService {

  private HashMap<String,List<Post>> userPosts =
      new HashMap<String,List<Post>>();

  public PostService() {
    System.out
        .println("#####instantiating###### " + this.getClass().getName());
  }

  public void addUserKey(User user){
    this.userPosts.put(user.getUsername(),new LinkedList<Post>());
  }


  public List<Post> getAllPosts() {
    List<Post> allPosts = new LinkedList<Post>();
    for(List<Post> list:this.userPosts.values()){
      allPosts.addAll(list);
    }
    return allPosts;
  }

  public List<Post> getUserPosts(String username){
    return this.userPosts.get(username);
  }

  public void savePost(Post post){
    Post userPost = new Post();
    userPost.setTitle(post.getTitle());
    userPost.setContent(post.getContent());
    userPost.setDate(new Date());
    userPost.setAuthor("mavixk");
    userPosts.get(userPost.getAuthor()).add(userPost);
  }
}
