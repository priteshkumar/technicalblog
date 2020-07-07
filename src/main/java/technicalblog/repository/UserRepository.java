package technicalblog.repository;

import technicalblog.model.User;

public interface UserRepository {

  void registerUser(User user);
  public User verifyUser(User user);
}
