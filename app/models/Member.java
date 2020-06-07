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
public class Member extends Model
{
  public String name;
  public String address;
  public String email;
  public String password;
  public String gender;
  public Double height;
  public Double weight;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Assessment> assessments = new ArrayList<Assessment>();

  public Member(String name, String address, String email, String password, String gender, Double height, Double weight)
  {
    this.name = name;
    this.address = address;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.height = height;
    this.weight = weight;

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public List<Assessment> getAssessments() {
    return assessments;
  }

  public void setAssessments(List<Assessment> assessments) {
    this.assessments = assessments;
  }



  public static Member findByEmail(String email)
  {
    return find("email",email).first();
  }


  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }


}
