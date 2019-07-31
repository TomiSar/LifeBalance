package balanceoflife;

import javafx.scene.control.Alert;

public class PersonalData {

    private double height;
    private double weight;

    public PersonalData(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    //Nick Trefethen new formula
    //http://people.maths.ox.ac.uk/trefethen/bmi.html
    //http://people.maths.ox.ac.uk/trefethen/bmi_calc.html
    //Current formula: BMI = weight(kg)/height(m)^2 = 703*weight(lb)/height(in)^2.
    //New formula: BMI = 1.3*weight(kg)/height(m)^2.5 = 5734*weight(lb)/height(in)^2.5
    public double calculateCurrentFormulaBMI() {
        return weight / (Math.pow(height * 0.01, 2));
    }

    public double calculateNewFormulaBMI() {
        return 1.3 * weight / (Math.pow(height * 0.01, 2.5));
    }

    //Print message Information DialogBox if height or weight is lower than 0.0
    public void printMessageInformation(Alert message, double height, double weight) {
        message.setTitle("Information DialogBox");
        message.setContentText("Give valid height and weight!!\n\n"
                + "Height and weight should be greater than 0.0.\n"
                + "Normal body mass index is lower or equal to 0.0.\n\n"
                + "Height is " + height + "cm and weight is " + weight + "kg.\n"
                + "Ńormal body mass index is lower or equal to 0.0.\n"
                + "Nick Trefethen body mass index is lower or equal to 0.0.\n");

        message.show();
    }

    //Print results from the result of your BMI calculation Nick Trefethen formula
    //Descriptions for Body mass indexes
    /*sickly underweight 0 – 14,9
    signifigant overweight	15 – 17,9
    slightly owerweight	18 – 18,9
    normal weight	19 – 24,9
    slightly weight	25,0 – 29,9
    significantly overweight	30,0 – 34,9
    seriously overweight	35,0 – 39,9
    sickly overweight	40,0 or greater*/
    public void printBMIResults(double bmiIndex, StringBuilder sb) {
        if (bmiIndex <= 0) {
            sb.append("\n").append("According Nick Trefethen BMI formula your BMI is less or equal to 0.0").append("\n")
                    .append("Check your input parameters. Height should be more than 0.0 and weight should be more than 0.0");
        } else if (bmiIndex > 0 && bmiIndex <= 14.9) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are sickly underweight.");
        } else if (bmiIndex >= 15 && bmiIndex <= 17.9) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are significantly underweight.");
        } else if (bmiIndex >= 18 && bmiIndex <= 18.9) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are slighty underweight.");
        } else if (bmiIndex >= 19 && bmiIndex <= 24.9) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are normalweight.");
        } else if (bmiIndex <= 30) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are slighty overweight.");
        } else if (bmiIndex <= 34.9) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are significantly overweight.");
        } else if (bmiIndex <= 39.9) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are seriously overweight.");
        } else if (bmiIndex >= 40) {
            sb.append("\n").append("According Nick Trefethen BMI formula you are sickly overweight.");
        }
    }
}
