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

//Current formula: BMI = weight(kg)/height(m)^2
/*Nick Trefethen new formula
          http://people.maths.ox.ac.uk/trefethen/bmi.html
          http://people.maths.ox.ac.uk/trefethen/bmi_calc.html*/

 /*//        Scanner lukija = new Scanner(System.in);
//        System.out.print("Nimi: ");
//        String nimi = lukija.nextLine();
//        System.out.print("Pituus: ");
//        int pituus = Integer.parseInt(lukija.nextLine());
//        System.out.print("Paino: ");
//        int paino = Integer.parseInt(lukija.nextLine());*/
public class BalanceOfLife extends Application {

    @Override
    public void start(Stage stage) {

        //Luodaan tekstikenttä ja lisätään tekstikomponentit
        TextArea textArea = new TextArea();
        Label presentationLbl = new Label("Hey and welcome to use Balance of life application.\n\n"
                + "Give starting parameters tour name, height and weight. Application calculates BMI (Body Mass Index) from given parameters.\n"
                + "The application calculates the result of body mass index in both the normal formula and the new formula of Nick Trefeth.\n"
                + "Based on the calculated body mass index the application tells you the result of your weight.\n\n");

        Label nameLbl = new Label("Name: ");
        Label heightLbl = new Label("Length ");
        Label weightLbl = new Label("Weight ");
        TextField nameInput = new TextField();
        TextField heightInput = new TextField();
        TextField weightInput = new TextField();

        Button bmiButton = new Button("Calculate BMI");
        Label calculatedBMInormal = new Label("\nCalculated body mass index Current BMI formula => weight(kg)/height(m)^2");
        TextField bmiResultNormal = new TextField();
        Label calculatedBMI = new Label("\nCalculated body mass index Nick Trefethen BMI formula => 1.3*weight(kg)/height(m)^2.5");
        TextField bmiResult = new TextField();

        Label resultLbl = new Label();

        BorderPane componentGroup = new BorderPane();
        componentGroup.setCenter(textArea);
        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 30));

        layout.getChildren().addAll(presentationLbl, nameLbl, nameInput, heightLbl, heightInput,
                weightLbl, weightInput, bmiButton, calculatedBMInormal, bmiResultNormal, calculatedBMI, bmiResult, resultLbl);

        bmiButton.setOnAction((event) -> {
            //Convert height and weigth to double and calculate the Body mass index from formula
            //Normal BMI formula = weight(kg)/height(m)^2
            //Nick Trefethen BMI formula = 1.3*weight(kg)/height(m)^2.5
            double heigth = Double.parseDouble(heightInput.getText());
            double weigth = Double.parseDouble(weightInput.getText());
            double normalBmiIndex = weigth / (Math.pow(heigth * 0.01, 2));
            double nickThrefenBmiIndex = 1.3 * weigth / (Math.pow(heigth * 0.01, 2.5));

            //Convert to String 
            bmiResultNormal.setText(Double.toString(normalBmiIndex));
            bmiResult.setText(Double.toString(nickThrefenBmiIndex));

            StringBuilder sb = new StringBuilder();
            sb.append("\nHello ").append(nameInput.getText()).append(" your height is ").append(heightInput.getText()).append("cm and your weight is ").append(weightInput.getText()).append("kg\n");
            sb.append("The body mass index calculated according to the traditional formula is ").append(normalBmiIndex).append(".\n");
            sb.append("The body mass index calculated according to the Nick Trefethen formula is ").append(nickThrefenBmiIndex).append(".\n");;

            printBMIResults(nickThrefenBmiIndex, sb);
            resultLbl.setText(sb.toString());

        });

        stage.setTitle("Balance of life");
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(BalanceOfLife.class);
    }

    //Painoindeksin mukaiset määritelmät
    /*Sairaalloinen alipaino	0 – 14,9
    Merkittävä alipaino	15 – 17,9
    Lievä alipaino	18 – 18,9
    Normaali paino	19 – 24,9
    Lievä ylipaino	25,0 – 29,9
    Merkittävä ylipaino	30,0 – 34,9
    Vaikea ylipaino	35,0 – 39,9
    Sairaalloinen ylipaino	40,0 tai enemmän*/
    //Print results from the result of your BMI calculation Nick Trefethen formula
    public void printBMIResults(double bmiIndex, StringBuilder sb) {
        if (bmiIndex >= 0 && bmiIndex <= 14.9) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan olet sairaalloisen alipainoinen.");
        } else if (bmiIndex >= 15 && bmiIndex <= 17.9) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan olet merkittävän alipainoinen.");
        } else if (bmiIndex >= 18 && bmiIndex < 18.5) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan olet lievästi alipainoinen.");
        } else if (bmiIndex >= 18.5 && bmiIndex < 25) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan olet normaalipainoinen.");
        } else if (bmiIndex < 30) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan sinulla on lievää ylipainoa.");
        } else if (bmiIndex < 35) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan sinulla on merkittävää ylipainoa.");
        } else if (bmiIndex < 40) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan sinulla on vaikeaa ylipainoa.");
        } else if (bmiIndex > 40) {
            sb.append("\n").append("Nick Trefethen painoindeksin laskukaavan mukaan sinulla on sairaalloista ylipainoa.");
        }
    }
}