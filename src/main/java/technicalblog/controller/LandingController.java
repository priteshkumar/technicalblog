package technicalblog.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.service.PostService;

@Controller
public class LandingController {

  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  public LandingController() {
    System.out
        .println("######Instantiating#####" + this.getClass().getName());
    //System.out.println(this);
    //System.out.println(this.postService);
  }

  @GetMapping("/")
  public String getHomeView(Model model) {
    List<Post> posts = null;
    try {
      posts = postService.getAllPosts();
      model.addAttribute("posts", posts);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return "homepage";
  }

  @GetMapping("/users/registration")
  public String getRegistrationView() {
    return "users/register";
  }

  @GetMapping("/users/login")
  public String getLoginView(
      @ModelAttribute("invalidAuth") String invalidAuth
      , Model model) {
    //   System.out.println(invalidAuth.length() + " " + invalidAuth);
    //System.out.println(invalidAuth.equals(""));
    if (!invalidAuth.equals("")) {
      model.addAttribute("invalidAuth", invalidAuth);
    } else {
      model.addAttribute("invalidAuth", "false");
    }
    model.addAttribute("user", new User());
    return "users/login";
  }

  @RequestMapping(value = "/users/registration", method = RequestMethod.POST)
  public String registerUser(User user) {
    System.out.println(user.getFullname() + " " + user.getUsername());
    if (!userService.registerUser(user)) {
      return "redirect:/users/registration";
    }
    postService.addUserKey(user);
    return "redirect:/users/login";
  }

  /**
   * requested url : "<serverip:port>/users/login" request method: POST
   *
   * @param : user , contains form input data Action : - verifies username/password - on success
   *          redirects user to "<serverip:port>/posts" url - on failure redirects to user
   *          registration url
   * @return : redirect url String
   */
  @PostMapping("/users/login")
  public String getLoggedinView(@ModelAttribute User user,
      RedirectAttributes ra) {
    if (!userService.verifyUser(user)) {
      //  System.out.println("add invalidauth");
      ra.addFlashAttribute("invalidAuth", Boolean.valueOf(true));
      return "redirect:/users/login";
    }

    //ra.addAttribute("username", user.getUsername());
    ra.addFlashAttribute("username", user.getUsername());
    return "redirect:/posts";
  }

  @GetMapping("/users/logout")
  public String logoutUser() {
    return "redirect:/";
  }
}