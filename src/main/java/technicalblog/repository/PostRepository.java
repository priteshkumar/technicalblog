package technicalblog.repository;

import java.util.List;
import technicalblog.model.Post;

public interface PostRepository {

  List<Post> getAllPosts();

  List<Post> getUserPost();

  Post savePost(Post post);
}
