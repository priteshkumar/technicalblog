package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import technicalblog.model.Post;
import technicalblog.service.PostService;

/**
 * @RequestMapping base url can be defined at Controller level
 * e.g. "/posts" on PostController
 * then @RequestMapping "/user" can be defined at method level
 * this trickles down to full url = "/posts/user"
 * "/posts" is intercepted by PostController ,
 * then the left portion of requested url "/user" is
 * intercepted by controller method.
 */
@Controller
//@RequestMapping("/posts")
public class PostController {

  @Autowired
  private PostService postService;

  @GetMapping("/posts")
  public String getUserPosts( @ModelAttribute("username") String username,
      Model model){
    model.addAttribute("username",username);
    model.addAttribute("posts",postService.getUserPosts(username));
    return "users/posts";
  }

  @GetMapping("/posts/create")
  public String createUserPost(Model model){
      //model.addAttribute("username")
      return "users/createpost";
  }

  @PostMapping("/posts/create")
  public String savePost(Post post, RedirectAttributes ra){
    postService.savePost(post);
    ra.addFlashAttribute("username","mavixk");
    return "redirect:/posts";
  }

}
