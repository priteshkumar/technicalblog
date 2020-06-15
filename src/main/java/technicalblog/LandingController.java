package technicalblog;

import java.util.Date;
import java.util.LinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Post;

@Controller
@RequestMapping("/")
public class LandingController {

  @GetMapping("/")
  public String getHomeView(Model model) {
    LinkedList<Post> posts = new LinkedList<>();
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
    model.addAttribute("posts",posts);
    return "homepage";
  }

  @GetMapping("/users/registration")
  public String getRegistrationView() {
    return "register";
  }

  @GetMapping("/users/login")
  public String getLoginView() {
    return "login";
  }

  @PostMapping("/users/home")
  public String loginUser(@RequestParam("username") String username,
      @RequestParam("password") String password,Model model) {
    if(!username.equals("mavixk") || !password.equals("kasper"))
      return "register";
    return "techblog";
  }
}
