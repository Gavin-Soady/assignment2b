package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;


import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class TrainerDashboard extends Controller
{
  public static void index(Long id)
  {
    Logger.info("Rendering Trainer Dashboard");
    Member member = Member.findById(id);
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
    String bmiString = String.format("%.2f", bmi);
    String isIdealWeightColor="";
    if(isIdealWeight.equals("Not Ideal Weight")){
      isIdealWeightColor= "tachometer alternate icon huge red";
    }
    else{
      isIdealWeightColor= "tachometer alternate icon huge green";
    }

    render("trainerdashboard.html",  member, bmiCategory, bmiString, assessments, isIdealWeight, isIdealWeightColor);

  }

  public static void showMembers() {
    Logger.info("Rendering Trainer Dashboard");
    //Trainer trainer=TrainerAccounts.getLoggedInTrainer();
    List<Member> members=Member.findAll();
    render ("memberslist.html",members);
  }


  public static void deleteClientMeasurements(Long id)
  {
    Assessment assessment = Assessment.findById(id);
    assessment.delete();
    Logger.info("Deleting " + assessment.weight);
    redirect("/dashboard");
  }

//  public static void memberView(Long id) {
//    Logger.info("Rendering member view. Member id = "+id);
//    Member member=Member.findById(id);
//    List<Assessment> assessments = member.assessments;
//    //double BMI = Math.round((GymUtility.calculateBMI(member))*100.0)/100.0;
//    double BMI = 23;
//    //String isIdealBodyWeight="No";
//    if(!assessments.isEmpty()){
//      Assessment assessment=assessments.get(assessments.size()-1);
//      //BMI=Math.round((GymUtility.calculateBMI(member,assessment))*100.0)/100.0;
//      //GymUtility.isIdealBodyWeight(member,assessment);
//      if(GymUtility.isIdealBodyWeight(member,assessment)==true){
//        isIdealBodyWeight="Yes";
//      }
//    }
//    String BMICategory=GymUtility.determineBMICategory(BMI);
//    render ("member.html",member,assessments,BMI,BMICategory,isIdealBodyWeight);
//  }

}
