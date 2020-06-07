package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.Assessment;
import models.Member;

public class Signup extends Controller
{

  public static void index()
  {
    Logger.info("Rendering signup");
    render("signup.html");
  }

  public static void addMember( String name, String address, String email, String password, String gender, double height, double weight) {

    Member member = new Member(name, address, email, password, gender, height, weight);
    member.save();
    redirect("/");
  }



}
