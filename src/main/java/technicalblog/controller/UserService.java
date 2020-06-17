package technicalblog.controller;

import java.util.HashMap;
import org.springframework.stereotype.Service;
import technicalblog.model.User;

@Service
public class UserService {

  private HashMap<String, User> userMap = new HashMap<>();

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
    userMap.put(user.getUsername(), new User(user.getUsername(),
        user.getPassword(), user.getFullname()));
    return true;
  }

  public boolean verifyUser(User user) {
    User userCreds = userMap.get(user.getUsername());
    if (userCreds == null || !userCreds.getPassword()
        .equals(user.getPassword())) {
      return false;
    }
    return true;
  }

}
