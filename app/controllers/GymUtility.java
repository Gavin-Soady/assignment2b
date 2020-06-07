package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;


import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class GymUtility extends Controller
{

  public static double calculateBMI(double height, double weight)
  {

    double BMI =  weight/((height/100)*(height/100));

    return BMI;
  }

  public static String getBMICategory(double bmi)
  {
    String bmiCategory = "";

    if(bmi< 18.5){
      bmiCategory = "Under Weight";
    }
    else if(bmi >= 18.5 && bmi <= 24.9){
      bmiCategory = "Normal Weight";
    }
    else if(bmi >= 25 && bmi <= 29.9){
      bmiCategory = "Over Weight";
    }
    else if(bmi >= 30 && bmi <= 39.9){
      bmiCategory = "Obese";
    }
    else {
      bmiCategory = "Very Severely Obese";
    }
    return bmiCategory;
  }

  public static String isIdealWeight(String bmiCategory ) {
    String isIdealWeight = "";
    String category = bmiCategory;
    if (category.equals("Normal Weight")) {
      isIdealWeight = "Ideal Weight ";
    } else {
      isIdealWeight = "Not Ideal Weight";
    }
    return isIdealWeight;
  }

}
