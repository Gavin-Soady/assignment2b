package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.Assessment;
import models.Member;
import models.Trainer;

public class Login extends Controller
{

  public static void index()
  {
    Logger.info("Rendering login");
    render("login.html");
  }
  public static void trainerlogin()
  {
    Logger.info("Rendering  trainer login");
    render("trainerlogin.html");
  }

  public static void checkLogin(String email, String password )
  {
    Logger.info("Attempting Login with " + email + ":" + password);
    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Login successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      Logger.info("Login failed");
      redirect("/login");
    }

  }

  public static Member getLoggedInMember(){
    Member member = null;
    if (session.contains("logged_in_Memberid")){
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    }
    else{
      index();
    }
    return member;
  }

  public static void checkTrainerLogin(String email, String password ){
    Logger.info("Attempting Login with " + email + ":" + password);
    Trainer trainer = Trainer.findByEmail(email);
    if ((trainer != null) && (trainer.checkPassword(password) == true)) {
      Logger.info("Login successful");
      session.put("logged_in_Trainerid", trainer.id);
      redirect("/memberslist");
    } else {
      Logger.info("Login failed");
      redirect("/trainerlogin");
    }

  }

  public static Trainer getLoggedInTrainer(){
    Trainer trainer = null;
    if (session.contains("logged_in_Memberid")){
      String trainerId = session.get("logged_in_Trainerid");
      trainer = trainer.findById(Long.parseLong(trainerId));
    }
    else{
      index();
    }
    return trainer;
  }

  public static void logOut(){
    session.clear();
    redirect ("/");
  }
}


