package controllers;

import models.Assessment;
import models.Member;

import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class MemberController extends Controller
{

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
    Member member = Login.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding clientMeasurements for " + member.name);
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
