package balanceoflife;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

//BMI formulas 
/*Nick Trefethen new formula
          http://people.maths.ox.ac.uk/trefethen/bmi.html
          http://people.maths.ox.ac.uk/trefethen/bmi_calc.html*/
public class BalanceOfLife extends Application {

    @Override
    public void start(Stage stage) {

        //Textarea for the application
        TextArea textArea = new TextArea();
        Label presentationLbl = new Label("Hey and welcome to Balance of life\n\n"
                + "Give your personal information.\n"
                + "Your name, height and weight.\n\n"
                + "Application calculates BMI (Body Mass Index) from given parameters\n"
                + "The result of body mass index is calculated via normal formula and the new Nick Trefeth formula\n"
                + "Based on BMI formulas application counts your current body mass index. Gives a description from your body mass index.\n\n");

        //Labels ans TextFields
        Label nameLbl = new Label("Name");
        Label heightLbl = new Label("Length ");
        Label weightLbl = new Label("Weight ");
        TextField nameInput = new TextField();
        TextField heightInput = new TextField();
        TextField weightInput = new TextField();

        //Calculation button fro BMI index, labels and textfields for outputs 
        Button bmiButton = new Button("Calculate BMI");
        Button clearButton = new Button("Clear");
        TextField bmiResultNormal = new TextField();
        TextField bmiResult = new TextField();
        Label calculatedBMInormal = new Label("\nCalculated body mass index Current BMI formula => weight(kg)/height(m)^2");
        Label calculatedBMI = new Label("\nCalculated body mass index Nick Trefethen BMI formula => 1.3*weight(kg)/height(m)^2.5");
        Label resultLbl = new Label();

        BorderPane componentGroup = new BorderPane();
        componentGroup.setCenter(textArea);
        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 30));

        layout.getChildren().addAll(presentationLbl, nameLbl, nameInput, heightLbl, heightInput,
                weightLbl, weightInput, bmiButton, clearButton, calculatedBMInormal, bmiResultNormal, calculatedBMI, bmiResult, resultLbl);

        bmiButton.setOnAction((event) -> {
            //Convert height and weigth to double and calculate the Body mass index from formula
            //Normal BMI formula = weight(kg)/height(m)^2
            //Nick Trefethen BMI formula = 1.3*weight(kg)/height(m)^2.5
            double heigth = Double.parseDouble(heightInput.getText());
            double weigth = Double.parseDouble(weightInput.getText());
            double normalBmiIndex = weigth / (Math.pow(heigth * 0.01, 2));
            double nickThrefenBmiIndex = 1.3 * weigth / (Math.pow(heigth * 0.01, 2.5));

            //Convert BMI double values to String for TextFields 
            if (normalBmiIndex <= 0 || nickThrefenBmiIndex <= 0) {
                bmiResultNormal.setText("Normal body mass index is lower or equal to 0.0");
                bmiResult.setText("Nick Trefethen body mass index is lower or equal to 0.0");
            } else {
                bmiResultNormal.setText(Double.toString(normalBmiIndex));
                bmiResult.setText(Double.toString(nickThrefenBmiIndex));
            }

            //StringBuilder to get the infromation in one string
            StringBuilder sb = new StringBuilder();
            sb.append("\nHello ").append(nameInput.getText()).append("! Your height is ").append(heightInput.getText()).append(" cm and your weight is ").append(weightInput.getText()).append(" kg\n");
            sb.append("The body mass index calculated according to the traditional formula is ").append(normalBmiIndex).append(".\n");
            sb.append("The body mass index calculated according to the Nick Trefethen formula is ").append(nickThrefenBmiIndex).append(".\n");;

            printBMIResults(nickThrefenBmiIndex, sb);
            resultLbl.setText(sb.toString());

        });

        //Clear all text from TextFields, labels and calculation label 
        clearButton.setOnAction((event) -> {
            nameInput.setText("");
            heightInput.setText("");
            weightInput.setText("");
            bmiResultNormal.setText("");
            bmiResult.setText("");
            resultLbl.setText("");
        });

        stage.setTitle("Balance of life");
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(BalanceOfLife.class);
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