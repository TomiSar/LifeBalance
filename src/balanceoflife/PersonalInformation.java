package balanceoflife;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class PersonalInformation {

    private Label nameLbl;
    private Label heightLbl;
    private Label weightLbl;
    private Label normalBMIFormulaText;
    private Label nickTrefethenBMIFormulaText;
    private Button bmiButton;
    private Button clearButton;

    public PersonalInformation() {
        this.nameLbl = new Label("Enter your name: ");
        this.heightLbl = new Label("Enter your height: ");
        this.weightLbl = new Label("Enter your weight: ");
        this.normalBMIFormulaText = new Label("\nCalculated body mass index Current BMI formula => weight(kg)/height(m)^2");
        this.nickTrefethenBMIFormulaText = new Label("\nCalculated body mass index Nick Trefethen BMI formula => 1.3*weight(kg)/height(m)^2.5");
        this.bmiButton = new Button("Calculate BMI");
        this.clearButton = new Button("Clear");
    }

    public Label getNameLbl() {
        return nameLbl;
    }

    public Label getHeightLbl() {
        return heightLbl;
    }

    public Label getWeightLbl() {
        return weightLbl;
    }

    public Button getBmiButton() {
        return bmiButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Label getNormalBMIFormulaText() {
        return normalBMIFormulaText;
    }

    public Label getNickTrefethenBMIFormulaText() {
        return nickTrefethenBMIFormulaText;
    }
}