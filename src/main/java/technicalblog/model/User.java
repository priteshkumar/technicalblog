package technicalblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
    name = "users",
    uniqueConstraints =  @UniqueConstraint(
        name = "user_credentials",
        columnNames = {
            "username",
            "password"
        }
    )
)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "username")
  @NotNull
  private String username;

  @Column(name = "password")
  @NotNull
  private String password;

  @Column(name = "fullname")
  @NotNull
  private String fullname;

  public User() {
  }

  public User(String username, String password, String fullname) {
    this.username = username;
    this.password = password;
    this.fullname = fullname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }
}
