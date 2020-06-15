package technicalblog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.service.PostService;

@Controller
public class LandingController {

  @Autowired
  private PostService postService;

  public LandingController() {
    System.out
        .println("######Instantiating#####" + this.getClass().getName());
    //System.out.println(this);
    //System.out.println(this.postService);
  }

  @GetMapping("/")
  public String getHomeView(Model model) {
    List<Post> posts = postService.getAllPosts();
    model.addAttribute("posts", posts);
    return "homepage";
  }

  @GetMapping("/users/registration")
  public String getRegistrationView() {
    return "users/register";
  }

  @GetMapping("/users/login")
  public String getLoginView(Model model) {
    model.addAttribute("user", new User());
    return "users/login";
  }

  /**
   * requested url : "<serverip:port>/users/login" request method: POST
   *
   * @param : user , contains form input data Action : - verifies
   *          username/password - on success redirects user to
   *          "<serverip:port>/posts" url - on failure redirects to user
   *          registration url
   * @return : redirect url String
   */
  @PostMapping("/users/login")
  public String getLoggedinView(@ModelAttribute User user,
      RedirectAttributes ra) {
    //System.out.println("inside post login data");
    if (user.getUsername().length() < 2 || user.getUsername().length() > 30
        || user.getPassword()
        .length() < 4) {
      return "redirect:/users/registration";
    }

    //ra.addAttribute("username", user.getUsername());
    ra.addFlashAttribute("username", user.getUsername());
    return "redirect:/posts";
  }

  @PostMapping("/users/logout")
  public String loginUser(@RequestParam("username") String username,
      @RequestParam("password") String password) {
    if (!username.equals("mavixk") || !password.equals("kasper")) {
      return "users/register";
    }
    return "users/techblog";
  }
}
