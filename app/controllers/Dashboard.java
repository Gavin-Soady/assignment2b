package controllers;

import models.Assessment;
import models.Member;

import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Login.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    //List<Member> memberList = Member.findAll();
    //TODO figure out how to get real current member

    //int currentMember = memberList.size()-1;
    String name = member.name;
    double height = member.height;
    double weight = 0;
    if( assessments.size() > 0) {
       weight = assessments.get(assessments.size()-1).weight;
    }
    else {
       weight = member.weight;
    }

    double bmi = GymUtility.calculateBMI(height, weight);
    String bmiCategory = GymUtility.getBMICategory(bmi);
    String isIdealWeight = GymUtility.isIdealWeight(bmiCategory);
    String isIdealWeightColor="";
    if(isIdealWeight.equals("Not Ideal Weight")){
      isIdealWeightColor= "tachometer alternate icon huge red";
    }
    else{
      isIdealWeightColor= "tachometer alternate icon huge green";
    }

    String bmiString = String.format("%.2f", bmi);

    render("dashboard.html",  member, bmiCategory, bmiString, assessments, isIdealWeight, isIdealWeightColor );

  }

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
    Member member = Login.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding assessment for " + member.name);
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id)
  {
    Assessment assessment = Assessment.findById(id);
    assessment.delete();
    Logger.info("Deleting " + assessment.weight);
    redirect("/dashboard");
  }

}
