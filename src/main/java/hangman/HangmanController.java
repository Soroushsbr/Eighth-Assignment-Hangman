package hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HangmanController {
    private String diff;
    private String name;
    private ArrayList<String> words;
    private ArrayList<String> guessed = new ArrayList<>();
    private String word;
    private int HP;
    public void set(String diff , String name){
        this.diff =diff;
        this.name =name;
        this.HP = 5;
        word = "water";
        word = word.toUpperCase();
        String[] temp = word.split("\\s+");
        words = new ArrayList<>(Arrays.asList(temp));
        System.out.println(words);
        setWords();
    }
    @FXML
    VBox vBox;
    @FXML
    HBox hBox;
    public void setWords(){
        try {
            vBox.getChildren().clear();
            hBox.getChildren().clear();
            for (int i = 0; i < word.length(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WordBox.fxml"));
                AnchorPane wordPane = loader.load();
                if(guessed.contains(String.valueOf(word.charAt(i)))) {
                    ((Label) wordPane.getChildren().get(0)).setText(String.valueOf(word.charAt(i)));
                }
                hBox.getChildren().add(wordPane);
            }
            vBox.getChildren().add(hBox);
        }catch (IOException ignored){
        }
    }
    @FXML
    TextField wordField;
    @FXML
    CubicCurve leftHand;
    @FXML
    CubicCurve rightHand;
    @FXML
    CubicCurve leftLeg;
    @FXML
    CubicCurve rightLeg;
    @FXML
    CubicCurve body;
    public void selectAWord(){
        String guess = wordField.getText().toUpperCase();
        if(word.contains(guess)){
            vBox.getChildren().clear();
            hBox.getChildren().clear();
            if(!guessed.contains(guess)) {
                guessed.add(guess);
            }
            wordField.clear();
            setWords();
        }else {
            wordField.clear();
            switch (HP){
                case 5:
                    leftLeg.setVisible(false);
                    HP--;
                    break;
                case 4:
                   rightLeg.setVisible(false);
                   HP--;
                   break;
                case 3:
                    rightHand.setVisible(false);
                    HP--;
                    break;
                case 2:
                    leftHand.setVisible(false);
                    HP--;
                    break;
                case 1:
                    body.setVisible(false);
                    HP--;
                    break;
                default:
                    break;
            }
        }
    }
}