package models;

import play.db.jpa.Model;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import models.Assessment;

@Entity
public class Trainer extends Model
{
  public String name;
  public String email;
  public String password;

  public Trainer(String name, String email, String password )
  {
    this.name = name;
    this.email = email;
    this.password = password;

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public static Trainer findByEmail(String email)
  {
    return find("email",email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }


}
