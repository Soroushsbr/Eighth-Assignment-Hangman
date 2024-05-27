package hangman;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.util.Duration;

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
        this.HP = 10;
        word = "water";
        word = word.toUpperCase();
        String[] temp = word.split("\\s+");
        words = new ArrayList<>(Arrays.asList(temp));
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
                case 10:
                    animeYwood();
                    HP--;
                    break;
                case 9:
                   animeXwood();
                   HP--;
                   break;
                case 8:
                    animeSideWood();
                    HP--;
                    break;
                case 7:
                    animeRope();
                    HP--;
                    break;
                case 6:
                    animeHead();
                    HP--;
                    break;
                case 5:
                    animeBody();
                    HP--;
                    break;
                case 4:
                    animeRightH();
                    HP--;
                    break;
                case 3:
                    animeLeftH();
                    HP--;
                    break;
                case 2:
                    animeRightL();
                    HP--;
                    break;
                case 1:
                    animeLeftL();
                    HP--;
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    Line Ywood;
    public void animeYwood(){
        Ywood.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Ywood.endYProperty(), 415.0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(Ywood.endYProperty(), -10.0 ))
        );
        timeline.play();
    }
    @FXML
    Line Xwood;
    public void animeXwood(){
        Xwood.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Xwood.endXProperty(), -55.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(Xwood.endXProperty(), 100.0 ))
        );
        timeline.play();
    }

    @FXML
    Line sideWood;
    public void animeSideWood(){
        sideWood.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(sideWood.endXProperty(), -55.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(sideWood.endXProperty(), -14.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(sideWood.endYProperty(), 60.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(sideWood.endYProperty(), -9.0 ))
        );
        timelineY.play();
    }
    @FXML
    Line rope;
    public void animeRope(){
        rope.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rope.endYProperty(), -53.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(rope.endYProperty(), 83.0 ))
        );
        timeline.play();
    }

    @FXML
    Circle head;
    public void animeHead(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(head.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(head.opacityProperty(), 1.0 ))
        );
        timeline.play();
    }

    @FXML
    Line body;

    public void animeBody(){
        body.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(body.endYProperty(), -10.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(body.endYProperty(), 120.0 ))
        );
        timeline.play();
    }

    @FXML
    Line rightH;
    public void animeRightH(){
        rightH.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightH.endXProperty(), -100.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightH.endXProperty(), -60 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightH.endYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightH.endYProperty(), 45 ))
        );
        timelineY.play();
    }

    @FXML
    Line leftH;
    public void animeLeftH(){
        leftH.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftH.endXProperty(), 100)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftH.endXProperty(), 60.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftH.endYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftH.endYProperty(), 50 ))
        );
        timelineY.play();
    }

    @FXML
    Line rightL;
    public void animeRightL(){
        rightL.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightL.endXProperty(), -100.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightL.endXProperty(), -60.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightL.endYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightL.endYProperty(), 50 ))
        );
        timelineY.play();
    }
    @FXML
    Line leftL;
    public void animeLeftL(){
        leftL.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftL.endXProperty(), 100)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftL.endXProperty(), 60.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftL.endYProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftL.endYProperty(), 50 ))
        );
        timelineY.play();
    }
}