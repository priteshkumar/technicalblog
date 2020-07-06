package technicalblog.repository;

import java.util.List;
import technicalblog.model.Post;

public interface PostRepository {

  List<Post> getAllPosts();

  List<Post> getUserPost();

  Post savePost(Post post);

  Post getSinglePost(Integer postId);

  Post updatePost(Post post);

  void deletePost(Integer postId);
}
