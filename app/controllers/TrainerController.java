package controllers;

import models.Assessment;
import models.Member;

import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class TrainerController extends Controller
{

  public static void addAssessment(Long id, double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
    Member member = Member.findById(id);
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding asesessment for " + member.name);

    redirect("/showmember/"+id);
  }

  public static void deleteAssessment(Long id)
  {
    Assessment assessment = Assessment.findById(id);
    assessment.delete();
    Logger.info("Deleting " + assessment.weight);
    redirect("/dashboard");
  }

  public static void addComment(Long id, Long assessmentid, String comment){
    Logger.info("Adding comment");
    Assessment assessment = Assessment.findById(assessmentid);
    assessment.setComment(comment);
    assessment.save();
    redirect("/showmember/" + id);
  }

}
