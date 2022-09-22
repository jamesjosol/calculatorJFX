package sample;

import javafx.animation.PauseTransition;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.text.DecimalFormat;

/**
 * @author James Paul Josol
 */

public class Controller {

    @FXML
    BorderPane mainPane;

    @FXML
    TextField input;

    @FXML
    Label topDisplay;

    @FXML
    Button one;


    double firstNum = Double.NaN;
    double secondNum = Double.NaN;
    String operator;
    boolean operatorClicked = false;
    DecimalFormat df = new DecimalFormat("#.####");

    public void zeroClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "0");
    }

    public void oneClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "1");
    }

    public void twoClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "2");
    }

    public void threeClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "3");
    }

    public void fourClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "4");
    }

    public void fiveClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "5");
    }

    public void sixClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "6");
    }
    public void sevenClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "7");
    }

    public void eightClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "8");
    }

    public void nineClicked (ActionEvent ev) {
        String prevValue = input.getText();
        input.setText(prevValue + "9");
    }

    public void clearClicked (ActionEvent ev) {
        input.setText("");
        topDisplay.setText("");
        operatorClicked = false;
        firstNum = Double.NaN;
        secondNum = Double.NaN;
    }

    public void deleteClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(!prevValue.isBlank()) {
            String newVal = prevValue.substring(0, prevValue.length()-1);
            input.setText(newVal);
        }else {
            topDisplay.setText("");
            operatorClicked = false;
            firstNum = Double.NaN;
            secondNum = Double.NaN;
        }
    }

    public void percentClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(!prevValue.isBlank()) {
            float val = Float.parseFloat(prevValue) / 100;
            input.setText(isWhole(val) ? Long.toString((long)val) : df.format(val));
        }
    }

    public void divideClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(operatorClicked) {
            String prevTop = topDisplay.getText();
            topDisplay.setText(prevTop.substring(0,prevTop.length()-1) + "÷");
        }else {
            if(!prevValue.isBlank()) {
                float val = Float.parseFloat(prevValue);
                firstNum = val;
                operatorClicked = true;
                input.setText("");
                topDisplay.setText(prevValue + "÷");
            }
        }
        operator = "/";

    }

    public void timesClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(operatorClicked) {
            String prevTop = topDisplay.getText();
            topDisplay.setText(prevTop.substring(0,prevTop.length()-1) + "×");
        }else{
            if(!prevValue.isBlank()) {
                float val = Float.parseFloat(prevValue);
                firstNum = val;
                operatorClicked = true;
                input.setText("");
                topDisplay.setText(prevValue + "×");
            }
        }
        operator = "*";
    }

    public void minusClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(operatorClicked) {
            String prevTop = topDisplay.getText();
            topDisplay.setText(prevTop.substring(0,prevTop.length()-1) + "-");
        }else {
            if(!prevValue.isBlank()) {
                float val = Float.parseFloat(prevValue);
                firstNum = val;

                operatorClicked = true;
                input.setText("");
                topDisplay.setText(prevValue+"-");
            }
        }
        operator = "-";
    }

    public void addClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(operatorClicked) {
            String prevTop = topDisplay.getText();
            topDisplay.setText(prevTop.substring(0,prevTop.length()-1) + "+");
        }else {
            if(!prevValue.isBlank()) {
                float val = Float.parseFloat(prevValue);
                firstNum = val;
                operatorClicked = true;
                input.setText("");
                topDisplay.setText(prevValue+"+");
            }
        }
        operator = "+";
    }

    public void signClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(!prevValue.isBlank()) {
            String newValue = prevValue.charAt(0) == '-' ? prevValue.substring(1) : "-"+prevValue;
            input.setText(newValue);
        }
    }

    public void dotClicked (ActionEvent ev) {
        String prevValue = input.getText();
        if(prevValue.indexOf('.') == -1) {
            input.setText(!prevValue.isBlank() ? prevValue+'.' : "0.");
        }
    }

    public void equalsClicked (ActionEvent ev) {
        try {
            if(!Double.isNaN(firstNum)) {
                String val = input.getText();
                secondNum = Float.parseFloat(val);
                switch (operator) {
                    case "+" :
                        double sum = firstNum + secondNum;
                        input.setText(isWhole(sum) ? Long.toString((long)sum) : df.format(sum));
                        break;

                    case "-" :
                        double diff = firstNum - secondNum;
                        input.setText(String.valueOf(diff));
                        input.setText(isWhole(diff) ? Long.toString((long)diff) : df.format(diff));
                        break;

                    case "*" :
                        double prod = firstNum * secondNum;
                        input.setText(isWhole(prod) ? Long.toString((long)prod) : df.format(prod));
                        break;

                    case "/" :
                        double qou = firstNum / secondNum;
                        input.setText(isWhole(qou) ? Long.toString((long)qou) : df.format(qou));
                        break;
                }
                String prevTopDis = topDisplay.getText();
                topDisplay.setText(prevTopDis + val + " = ");
                operatorClicked = false;
            }
        }catch(NumberFormatException ex) {

        }
    }

    private boolean isWhole(double value) {
        return Math.floor(value) == value;
    }

    public void onKeyTyped(KeyEvent ke) {
//        String c = ke.getCharacter();
//        System.out.println(c);
//        if(c.equals("1")) {
//            one.arm();
//            one.fire();
//            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
//            pause.setOnFinished(e -> one.disarm());
//            pause.play();
//
//        }
        if(ke.getCode() == KeyCode.DIGIT1 || ke.getCode() == KeyCode.NUMPAD1) {
            one.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
            pause.setOnFinished(evt -> {
                one.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
                one.fire();
            });
            pause.play();

        }
    }
}
