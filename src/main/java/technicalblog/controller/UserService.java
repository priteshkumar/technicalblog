package technicalblog.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.User;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

  //private HashMap<String, User> userMap = new HashMap<>();
  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository){
      this.userRepository = userRepository;
  }

  public boolean checkUserCredsFormat(User user) {
    if (user.getUsername().length() < 4 || user.getUsername().length() > 30
        || user.getPassword().length() < 6) {
      return false;
    }
    return true;
  }

  public boolean registerUser(User user) {
    if (!checkUserCredsFormat(user)) {
      return false;
    }
    userRepository.registerUser(user);
    return true;
  }

  public User verifyUser(User user) {
    //return true;
    User verifedUser = userRepository.verifyUser(user);
    if(verifedUser != null){
      if(verifedUser.getPassword().equals(user.getPassword()))
        return verifedUser;
    }
    return null;
  }

}
