package balanceoflife;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        Label heightLbl = new Label("Lenght");
        Label weightLbl = new Label("Weight");
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
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            double height = Double.parseDouble(heightInput.getText());
            double weight = Double.parseDouble(weightInput.getText());
            double currentFormulaBmi = 0.0;
            double newFormulaBmi = 0.0;

            //Set height and weight to personal data to Calculate BMI values
            PersonalData personalData = new PersonalData(height, weight);

            if (height > 0 && weight > 0) {
                currentFormulaBmi = personalData.calculateCurrentFormulaBMI();
                newFormulaBmi = personalData.calculateNewFormulaBMI();
            } else {
                message.setTitle("Information DialogBox");
                message.setContentText("Height is " + height + "cm and weight is " + weight + "kg.\n"
                        + "Height and weight should be greater than zero.\n"
                        + "Give valid height and weight.");
                message.show();
            }

            //Convert BMI double values to String for TextFields 
            if (currentFormulaBmi < 0 || newFormulaBmi < 0) {
                bmiResultNormal.setText("Normal body mass index is lower or equal to 0.0");
                bmiResult.setText("Nick Trefethen body mass index is lower or equal to 0.0");
            } else {
                bmiResultNormal.setText(Double.toString(currentFormulaBmi));
                bmiResult.setText(Double.toString(newFormulaBmi));
            }

            //StringBuilder to get the infromation in one string
            StringBuilder sb = new StringBuilder();
            sb.append("\nHello ").append(nameInput.getText()).append("! Your height is ").append(heightInput.getText()).append("cm and your weight is ").append(weightInput.getText()).append("kg\n");
            sb.append("The body mass index calculated according to the traditional formula is ").append(currentFormulaBmi).append(".\n");
            sb.append("The body mass index calculated according to the Nick Trefethen formula is ").append(newFormulaBmi).append(".\n");;

            if (newFormulaBmi != 0) {
                personalData.printBMIResults(newFormulaBmi, sb);
                resultLbl.setText(sb.toString());
            }
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
}
