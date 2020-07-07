package technicalblog.controller;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpSession;
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
import technicalblog.model.UserProfile;
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
    posts = postService.getAllPosts();
    model.addAttribute("posts", posts);
    return "homepage";
  }

  @GetMapping("/users/registration")
  public String getRegistrationView(Model model) {
    User user = new User();
    UserProfile userProfile = new UserProfile();
    user.setUserProfile(userProfile);
    model.addAttribute("user", user);
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
    System.out.println(user.getUsername());
    if (!userService.registerUser(user)) {
      return "redirect:/users/registration";
    }
    // postService.addUserKey(user);
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
      RedirectAttributes ra, HttpSession httpSession) {

    User verifiedUser = userService.verifyUser(user);
    if (verifiedUser == null) {
      //  System.out.println("add invalidauth");
      ra.addFlashAttribute("invalidAuth", Boolean.valueOf(true));
      return "redirect:/users/login";
    }
    //ra.addFlashAttribute("username", user.getUsername());
    httpSession.setAttribute("loggedUser", verifiedUser);
    return "redirect:/posts";
  }

  @GetMapping("/users/logout")
  public String logoutUser(HttpSession httpSession) {
    httpSession.invalidate();//invalidate httpsession
    return "redirect:/";
  }
}